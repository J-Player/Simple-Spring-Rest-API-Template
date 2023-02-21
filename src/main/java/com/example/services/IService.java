package com.example.services;

public interface IService<T> {

    T findById(Long id);

    Iterable<T> findAll();

    T save(T t);

    void update(T t);

    void delete(Long id);

}
