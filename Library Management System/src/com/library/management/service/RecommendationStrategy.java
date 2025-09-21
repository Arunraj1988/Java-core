package com.library.management.service;
import com.library.management.model.Book;
import com.library.management.model.User;
import java.util.List;
public interface RecommendationStrategy {
    List<Book> recommend(User user, List<Book> catalog, int max);
}
