package com.library.management.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private final String loanId;
    private final String bookId;
    private final String userId;
    private final String branchId;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;


/*
1.checkoutDate → The date when the user borrows the book from the library.
2.dueDate → The last date by which the patron must return the book to the library.
3.returnDate → The actual date when the patron returns the book.

*/

    public Loan(String loanId, String bookId,String userId,String branchId ,LocalDate checkoutDate,LocalDate dueDate)
    {
        this.loanId = loanId;
        this.bookId = bookId;
        this.userId = userId;
        this.branchId = branchId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;

    }

    public String getLoanId() {return loanId;}
    public String getBookId() {return bookId;}
    public String getUserId() {return userId;}
    public String getBranchId() {return branchId;}
    public LocalDate getCheckoutDate() {return checkoutDate;}
    public LocalDate getDueDate() {return dueDate;}
    public LocalDate getReturnDate() {return returnDate;}

    public void setReturnDate(LocalDate returnDate) {this.returnDate = LocalDate.now();}

    public boolean isReturned() {return returnDate != null;}
// Check if overdue
    public boolean isOverdue() {
        return !isReturned() && LocalDate.now().isAfter(dueDate);

    }
// Fine Calculation (Rs.10/- per day)
    public long calculateFine(int finePerDay) {
        if (isOverdue()) {
            return 0;
        }
        long overdueDays = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        return overdueDays * finePerDay;

    }
    @Override
    public String toString() {
        return String.format(
            "loanid: %s | BookId: %s | BranchId: %s | Checkout: %s | Due-date :%s | Return-date :%s | Overdue :%s | Fine :%d",
            loanId,
            bookId,
            userId,
            branchId,
            checkoutDate,
            dueDate,
            returnDate == null ? "-" : returnDate.toString(),
            isOverdue() ? "YES" : "NO",
            calculateFine(10)
        );
    }
}

