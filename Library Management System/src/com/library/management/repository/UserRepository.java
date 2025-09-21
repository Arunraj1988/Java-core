package com.library.management.repository;
import com.library.management.model.User;
import java.util.Optional;
import java.util.Set;
public interface UserRepository {
    void add(User p);
    Optional<User> findById(String id);
    Set<User> findAll();
}