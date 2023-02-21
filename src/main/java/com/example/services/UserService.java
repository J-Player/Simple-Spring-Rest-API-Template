package com.example.services;

import com.example.domains.User;
import com.example.exceptions.BadRequestException;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements IService<User>, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(this::getBadRequestException);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(this::getBadRequestException);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        User savedUser = findById(user.getId());
        userRepository.save(user.withId(savedUser.getId()));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private BadRequestException getBadRequestException() {
        return new BadRequestException("User not found");
    }

}
