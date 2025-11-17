package com.example.ordermanagement.repository;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InMemoryRepository<T> implements GenericRepository<T> {

    protected Map<String, T> storage = new HashMap<>();

    @Override
    public void save(String id, T entity) {
        storage.put(id, entity);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }
}
