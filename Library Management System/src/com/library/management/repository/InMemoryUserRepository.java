package com.library.management.repository;
import com.library.management.model.User;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> byId = new ConcurrentHashMap<>();
    @Override
    public void add(User p) { byId.put(p.getUserId(), p); }
    @Override
    public Optional<User> findById(String id) { return
            Optional.ofNullable(byId.get(id)); }
    @Override
    public Set<User> findAll() { return new HashSet<>(byId.values()); }
}