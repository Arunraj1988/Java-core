package com.library.management.repository;
import com.library.management.model.Book;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
public class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> byId = new ConcurrentHashMap<>();
    @Override
    public void add(Book book) { byId.put(book.getBookId(), book); }
    @Override
    public boolean remove(String bookId) { return byId.remove(bookId) != null; }
    @Override
    public Optional<Book> findById(String bookId) { return
            Optional.ofNullable(byId.get(bookId)); }
    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return byId.values().stream().filter(b ->
                b.getIsbn().equals(isbn)).findFirst();
    }
    @Override
    public List<Book> findByTitle(String term) {
        String t = term.toLowerCase();
        return byId.values().stream().filter(b ->
                b.getTitle().toLowerCase().contains(t)).collect(Collectors.toList());
    }
    @Override
    public List<Book> findByAuthor(String author) {
        String t = author.toLowerCase();
        return byId.values().stream().filter(b ->
                b.getAuthor().toLowerCase().contains(t)).collect(Collectors.toList());
    }
    @Override
    public List<Book> findAll() { return new ArrayList<>(byId.values()); }
}

