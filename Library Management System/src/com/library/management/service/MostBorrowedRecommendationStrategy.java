package com.library.management.service;
import com.library.management.model.Book;
import com.library.management.model.User;
import com.library.management.model.Loan;
import java.util.*;
import java.util.stream.Collectors;
public class MostBorrowedRecommendationStrategy implements
        RecommendationStrategy {
    @Override
    public List<Book> recommend(User user, List<Book> catalog, int max) {
// naive: recommend books by the most-frequent authors in user's
        history
        Map<String, Long> authorCount = user.getBorrowingHistory().stream()
                .map(id -> id) // loan ids stored; in this in-memory simple
        version we lack loan->book mapping here
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
// fallback: return first `max` from catalog
        List<Book> out = new ArrayList<>();
        for (Book b : catalog) {
            if (out.size() >= max) break;
            out.add(b);
        }
        return out;
    }
}