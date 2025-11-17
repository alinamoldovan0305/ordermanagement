package com.example.ordermanagement.service;

import com.example.ordermanagement.repository.GenericRepository;
import java.util.List;

public abstract class GenericService<T> {

    protected final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public void add(String id, T entity) {
        if (repository.findById(id) == null) {
            repository.save(id, entity);
        }
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T getById(String id) {
        return repository.findById(id);
    }

//    public void update(String id, T entity) {
//        if (repository.findById(id) != null) {
//            repository.save(id, entity);
//        }
//    }
    public void update(String id, T entity) {
        if (repository.findById(id) != null) {
            repository.update(id, entity);
        }
    }


    public void delete(String id) {
        if (repository.findById(id) != null) {
            repository.delete(id);
        }
    }
}


