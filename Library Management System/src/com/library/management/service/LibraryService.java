package com.library.management.service;
import com.library.management.model.*;
import com.library.management.repository.*;
import com.library.management.util.LoggerUtil;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
public class LibraryService {
    private final BookRepository bookRepo;
    private final Map<String, BookBranch> inventory = new HashMap<>(); // id->bookbranch
    private final Map<String, Loan> activeLoans = new HashMap<>(); // loanId->loan
    private final Map<String, Reservation> reservations = new HashMap<>(); // id->reservation
    private final ReservationService reservationService = new
            ReservationService();

    public LibraryService(BookRepository bookRepo, UserRepository userRepo,
                          InMemoryBranchRepository branchRepo) {
        this.bookRepo = bookRepo;
    }
    public void setRecommendationStrategy(RecommendationStrategy s) {
    }
    // Book management
    public void addBook(Book b) { bookRepo.add(b);
        LoggerUtil.info("Book added: " + b); }
        public boolean removeBook(String bookId) { boolean r =
                bookRepo.findById(bookId).isPresent() && bookRepo.remove(bookId);
            LoggerUtil.info("Book removed: " + bookId); return r; }
        public Optional<Book> findByIsbn(String isbn) { return
                bookRepo.findByIsbn(isbn); }
        public List<Book> searchByTitle(String term) { return
                bookRepo.findByTitle(term); }
    public List<Book> searchByAuthor(String term) { return
            bookRepo.findByAuthor(term); }
    // User
    public void addUser(User p) { userRepo.add(p);
        LoggerUtil.info("User added: " + p); }
    // Branch & inventory
    public void addBranch(Branch b) { branchRepo.add(b);
        LoggerUtil.info("Branch added: " + b); }
    public void addBookToBranch(Book book, Branch branch, int copies) {
        String id = UUID.randomUUID().toString();
        BookBranch bb = new BookBranch(id, book.getBookId(),
                branch.getBranchId(), copies);
        inventory.put(id, bb);
        LoggerUtil.info("Added to branch inventory: " + bb);
    }
// Checkout
public Optional<Loan> checkout(String bookId, String userId, String
        branchId, int days) {
// find BookBranch with bookId and branchId that has available copy
    Optional<BookBranch> bb = inventory.values().stream()
            .filter(b -> b.getBookId().equals(bookId) &&
                    b.getBranchId().equals(branchId) && b.getCopiesAvailable() > 0)
            .findFirst();
    if (!bb.isPresent()) {
        LoggerUtil.info("No available copy for book " + bookId +
                " at branch " + branchId + ". Creating reservation.");
// create reservation
        String rid = UUID.randomUUID().toString();
        Reservation r = new Reservation(rid, bookId, userId, branchId,LocalDate.now());
        reservations.put(rid, r);
        reservationService.reserve(r);
        return Optional.empty();
    }
// reduce available and create loan
    BookBranch bookBranch = bb.get();
    boolean ok = bookBranch.checkoutCopy();
    if (!ok) return Optional.empty();
    String loanId = UUID.randomUUID().toString();
    LocalDate now = LocalDate.now();
    Loan loan = new Loan(loanId, bookId, userId, branchId, now,
            now.plusDays(days));
    activeLoans.put(loanId, loan);
    userRepo.findById(userId).ifPresent(p -> p.addToHistory(loanId));
    LoggerUtil.info("Loan created: " + loan);
    return Optional.of(loan);
}
    // Return
    public boolean returnLoan(String loanId) {
        Loan loan = activeLoans.get(loanId);
        if (loan == null) return false;
        if (loan.isReturned()) return false;
        loan.setReturnDate(LocalDate.now());
// find bookBranch holding this book in the branch and return copy
        inventory.values().stream().filter(bb ->
                        bb.getBookId().equals(loan.getBookId()) &&
                                bb.getBranchId().equals(loan.getBranchId()))
                .findFirst().ifPresent(BookBranch::returnCopy);
        LoggerUtil.info("Loan returned: " + loan);
// notify reservation
        reservationService.popNext(loan.getBookId(),
                loan.getBranchId()).ifPresent(r -> {
            r.setStatus("FULFILLED");
// In this in-memory example, we just log; in real app we'd notify
            user
            LoggerUtil.info("Reservation fulfilled: " + r);
        });
        return true;
    }
    public List<Loan> listActiveLoans() { return new
            ArrayList<>(activeLoans.values()); }
    public List<Book> listAllBooks() { return bookRepo.findAll(); }
    public List<Reservation> listReservations() { return new
            ArrayList<>(reservations.values()); }

    public List<Book> recommend(User p, int max) {
        if (recommendationStrategy == null) return Collections.emptyList();
        return recommendationStrategy.recommend(p, bookRepo.findAll(), max);
    }
}



