package com.library.management;
import com.library.management.model.*;
import com.library.management.repository.*;
import com.library.management.service.*;
import java.time.LocalDate;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        InMemoryBookRepository bookRepo = new InMemoryBookRepository();
        InMemoryUserRepository userRepo = new InMemoryUserRepository();
        InMemoryBranchRepository branchRepo = new InMemoryBranchRepository();
        LibraryService library = new LibraryService(bookRepo, userRepo,
                branchRepo);
        library.setRecommendationStrategy(new
                MostBorrowedRecommendationStrategy());
// create branches
        Branch central = new Branch("b1", "Central", "Downtown");
        Branch east = new Branch("b2", "East", "Eastside");
        library.addBranch(central);
        library.addBranch(east);
        // add books
        Book book1 = new Book("bk1", "978-0001", "Java Basics", "Alice", 2015);
        Book book2 = new Book("bk2", "978-0002", "Advanced Java", "Bob", 2018);
        library.addBook(book1);
        library.addBook(book2);
// add book copies to branches
        library.addBookToBranch(book1, central, 2);
        library.addBookToBranch(book2, central, 1);
        library.addBookToBranch(book2, east, 1);
// users
        User p1 = new User("p1", "Ravi", "ravi@example.com", "9999999999",
                LocalDate.now());
        User p2 = new User("p2", "Priya", "priya@example.com",
                "8888888888", LocalDate.now());
        library.addUser(p1);
        library.addUser(p2);
// checkout
        library.checkout("bk1", "p1", "b1", 14);
        library.checkout("bk1", "p2", "b1", 14); // second copy
// third user -> should reserve
        library.checkout("bk1", "p2", "b1", 14);

        // list active loans
        List<Loan> loans = library.listActiveLoans();
        System.out.println("Active loans:");
        loans.forEach(System.out::println);
// return a loan
        if (!loans.isEmpty()) {
            library.returnLoan(loans.get(0).getLoanId());
        }
        System.out.println("Reservations:");
        library.listReservations().forEach(System.out::println);
    }
}
