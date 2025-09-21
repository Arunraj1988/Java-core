package com.library.management.service;
import com.library.management.model.Reservation;
import com.library.management.model.User;
import com.library.management.model.Book;
import com.library.management.model.Branch;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
public class ReservationService {
    private static final Logger LOG =
            Logger.getLogger(ReservationService.class.getName());
    private final Map<String, Deque<Reservation>> queue = new
            ConcurrentHashMap<>();
    private String key(String bookId, String branchId) { return branchId + "|"
            + bookId; }
    public void reserve(Reservation r) {
        queue.computeIfAbsent(key(r.getBookId(), r.getBranchId()), k -> new
                ArrayDeque<>()).addLast(r);
        LOG.info("Reserved: " + r);
    }

    public Optional<Reservation> popNext(String bookId, String branchId) {
        Deque<Reservation> q = queue.get(key(bookId, branchId));
        if (q == null || q.isEmpty()) return Optional.empty();
        return Optional.ofNullable(q.pollFirst());
    }
    public List<Reservation> list(String bookId, String branchId) {
        Deque<Reservation> q = queue.get(key(bookId, branchId));
        if (q == null) return Collections.emptyList();
        return new ArrayList<>(q);
    }
}

