package com.library.management.model;

import java.time.LocalDate;

public class Reservation {
    private final String reservationId;
    private final String bookId;
    private final String userId;
    private final String branchId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private String status; // ACTIVE, FULFILLED, CANCELLED

    public Reservation(String reservationId, String bookId, String userId, String branchId, LocalDate startDate, LocalDate endDate, LocalDate reservationDate) {
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.userId = userId;
        this.branchId = branchId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "ACTIVE";
    }
    public String getReservationId() {return reservationId;}
    public String getBookId() {return bookId;}
    public String getUserId() {return userId;}
    public String getBranchId() {return branchId;}
    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    // Check if reservation is still valid
    public boolean isActive() {
        LocalDate currentDate = LocalDate.now();
        return status.equals("ACTIVE") &&
                (currentDate.isEqual(startDate) || currentDate.isAfter(startDate)) &&
                (currentDate.isBefore(endDate) || currentDate.isEqual(endDate));
    }
    @Override
    public String toString() {
        return String.format("Reservation-Id :%s | UserId :%s | BookId :%s | Start-date :%s | End-date :%s",
                reservationId, userId, bookId, startDate, endDate
        );
    }

}
