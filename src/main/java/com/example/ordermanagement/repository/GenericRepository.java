package com.example.ordermanagement.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GenericRepository<T> {
    void save(String id, T entity);
    List<T> findAll();
    T findById(String id);
    void delete(String id);
    void update(String id, T entity);

}