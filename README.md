## 📚<u> Library Management System (LMS) – Java
### Overview

This is a simple Library Management System (LMS) built in Java using Object-Oriented Programming (OOP) principles and SOLID design.
It is an in-memory application (no database required) and demonstrates Repository Pattern, Strategy Pattern, and clean architecture.

The system manages:

- Books (add, update, search, delete)
- Patrons/Users (registration, update, borrowing history)
- Branches (multi-branch support, inventory per branch)
- Loans (checkout, return, overdue detection, fine calculation)
- Reservations (book reservations with start/end dates)
- Recommendations (based on borrowing history – Strategy Pattern)

# <u> ER - Diagram</u>
<img width="520" height="548" alt="library_management_ERDiagram" src="https://github.com/user-attachments/assets/cb566733-0903-4fa1-8051-9d93b2e34317" />

# Project Structure
src/
 └── com/
      └── library/
           └── management/
                ├── Main.java
                ├── model/
                │     ├── Book.java
                │     ├── User.java
                │     ├── Branch.java
                │     ├── Loan.java
                │     └── Reservation.java
                ├── repository/
                │     ├── BookRepository.java
                │     ├── UserRepository.java
                │     ├── BranchRepository.java
                │     ├── InMemoryBookRepository.java
                │     ├── InMemoryUserRepository.java
                │     └── InMemoryBranchRepository.java
                ├── service/
                │     ├── LibraryService.java
                │     ├── ReservationService.java
                │     ├── RecommendationStrategy.java
                │     └── MostBorrowedRecommendationStrategy.java
                └── util/
                      └── LoggerUtil.java
