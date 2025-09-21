package com.library.management.repository;
import com.library.management.model.Branch;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class InMemoryBranchRepository {
    private final Map<String, Branch> byId = new ConcurrentHashMap<>();
    public void add(Branch b) { byId.put(b.getBranchId(), b); }
    public Optional<Branch> findById(String id) { return
            Optional.ofNullable(byId.get(id)); }
    public List<Branch> findAll() { return new ArrayList<>(byId.values()); }
}