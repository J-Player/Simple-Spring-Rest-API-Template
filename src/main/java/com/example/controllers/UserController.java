package com.example.controllers;

import com.example.domains.User;
import com.example.domains.dto.UserDTO;
import com.example.mappers.UserMapper;
import com.example.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController implements AbstractController<User, UserDTO, UUID> {

    private final UserService userService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));
    }

    @GetMapping
    public ResponseEntity<User> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @Override
    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.save(UserMapper.INSTANCE.toUser(userDTO)), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable UUID id) {
        userService.update(UserMapper.INSTANCE.toUser(userDTO));
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        userService.delete(uuid);
        return ResponseEntity.noContent().build();
    }

}
