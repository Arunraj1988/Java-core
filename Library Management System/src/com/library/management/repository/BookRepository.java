package com.library.management.repository;
import com.library.management.model.Book;
import java.util.List;
import java.util.Optional;
public interface BookRepository {
    void add(Book book);
    boolean remove(String bookId);
    Optional<Book> findById(String bookId);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitle(String term);
    List<Book> findByAuthor(String author);
    List<Book> findAll();
}
