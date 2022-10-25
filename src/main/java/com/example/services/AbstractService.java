package com.example.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractService<T, Id> {

    T findById(Id id);

    Page<T> findAll(Pageable pageable);

    T save(T t);

    void update(T t);

    void delete(Id id);

}
