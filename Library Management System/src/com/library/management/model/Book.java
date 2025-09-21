package com.library.management.model;
public class Book {
    private final String bookId; // unique id (could be uuid)
    private final String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private String status; // AVAILABLE, BORROWED, RESERVED
    public Book(String bookId, String isbn, String title, String author, int
            publicationYear) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = "AVAILABLE";
    }
    public String getBookId() { return bookId; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public String getStatus() { return status; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(int publicationYear) { this.publicationYear
            = publicationYear; }
    public void setStatus(String status) { this.status = status; }
    @Override
    public String toString() {
        return String.format("%s | %s | %s | %d | %s", bookId, title, author,
                publicationYear, status);
    }
}
