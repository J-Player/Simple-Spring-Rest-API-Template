package com.example.services;

public interface IService<T1, T2> {

    T1 findById(Long id);

    Iterable<T1> findAll();

    T1 save(T2 t2);

    void update(T2 t2, Long id);

    void delete(Long id);

}
