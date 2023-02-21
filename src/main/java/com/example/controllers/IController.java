package com.example.controllers;

import org.springframework.http.ResponseEntity;

public interface IController<T, DTO> {

    ResponseEntity<T> findById(Long id);

    ResponseEntity<Iterable<T>> findAll();

    ResponseEntity<T> save(DTO dto);

    ResponseEntity<Void> update(DTO dto, Long id);

    ResponseEntity<Void> delete(Long id);

}
