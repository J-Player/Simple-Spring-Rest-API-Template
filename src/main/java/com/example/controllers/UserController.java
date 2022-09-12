package com.example.controllers;

import com.example.domains.User;
import com.example.requests.UserPostRequestBody;
import com.example.requests.UserPutRequestBody;
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
public class UserController implements AbstractController<User, UserPostRequestBody, UserPutRequestBody, UUID> {

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

    @PostMapping
    @Override
    public ResponseEntity<User> save(@RequestBody UserPostRequestBody userPostRequestBody) {
        return new ResponseEntity<>(userService.save(userPostRequestBody), HttpStatus.CREATED);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UserPutRequestBody userPutRequestBody) {
        userService.update(userPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        userService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
