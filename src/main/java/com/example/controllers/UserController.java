package com.example.controllers;

import com.example.domains.User;
import com.example.domains.dto.UserDTO;
import com.example.mappers.UserMapper;
import com.example.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController implements IController<User, UserDTO> {

    private final UserService userService;

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Returns a user by ID.")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Returns a user by ID.")
    public ResponseEntity<User> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @Override
    @GetMapping("/all")
    @Operation(summary = "Returns all users.")
    public ResponseEntity<Iterable<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    @PostMapping
    @Operation(summary = "Saves a user in the database.")
    public ResponseEntity<User> save(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.save(UserMapper.INSTANCE.toUser(userDTO)), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Updates a user in the database.")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        userService.update(UserMapper.INSTANCE.toUser(userDTO).withId(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a user in the database.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
