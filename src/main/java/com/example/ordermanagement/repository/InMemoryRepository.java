package com.example.ordermanagement.repository;

import java.util.List;

public interface InMemoryRepository<T> {
    void save(String id, T entity);
    List<T> findAll();
    T findById(String id);
    void delete(String id);
}
