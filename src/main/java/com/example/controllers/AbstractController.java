package com.example.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AbstractController<T, DTO, Id> {

    ResponseEntity<T> findById(Id id);

    ResponseEntity<Page<T>> findAll(Pageable pageable);

    ResponseEntity<T> save(DTO dto);

    ResponseEntity<Void> update(DTO dto, Id id);

    ResponseEntity<Void> delete(Id id);

}
