package com.example.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractService<T, Post, Put, Id> {

    T findById(Id id);

    Page<T> findAll(Pageable pageable);

    T save(Post post);

    void update(Put put);

    void delete(Id id);

}
