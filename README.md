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
<img width="466" height="620" alt="Project Structure" src="https://github.com/user-attachments/assets/cb0738d9-0b92-40a6-afe2-6b613b5709ac" />



## ⚙️ Features

### 1. ✅ Book Management
- Add, update, delete books
- Search by title, author, or ISBN
- Track book availability

### 2.✅ User/Patron Management
- Register patrons with name, email, phone
- Update patron info
- Borrowing history tracked

### 3.✅ Branch & Inventory
- Manage multiple library branches
- Assign books to branches with available copies

### 4.✅ Loan Management
- Checkout & return books
- Track checkout date, due date, return date
- Overdue detection
- Fine calculation (per day)

### 5.✅ Reservation System
- Reserve books with start & end dates
- Cancel or fulfill reservations

### 6.✅ Recommendation System
- Recommend books using Strategy Pattern
  `Example: most borrowed books`

### 7.✅ Logging
- Logging of important actions using LoggerUtil

##  🛠️ How to Run
### 1.Clone the repo:
git clone https://github.com/your-username/library-management-system.git
cd library-management-system
### 2.Compile:
javac -d out $(find src -name "*.java")
### 3.Run:
java -cp out com.library.management.Main
