package com.example.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AbstractController<T, Post, Put, Id> {

    ResponseEntity<T> findById(Id id);

    ResponseEntity<Page<T>> findAll(Pageable pageable);

    ResponseEntity<T> save(Post post);

    ResponseEntity<Void> update(Put put);

    ResponseEntity<Void> delete(Id id);

}
