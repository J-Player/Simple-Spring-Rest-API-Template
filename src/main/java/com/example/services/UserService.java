package com.example.services;

import com.example.domains.User;
import com.example.exceptions.BadRequestException;
import com.example.mappers.UserMapper;
import com.example.repositories.UserRepository;
import com.example.requests.UserPostRequestBody;
import com.example.requests.UserPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements AbstractService<User, UserPostRequestBody, UserPutRequestBody, UUID>, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(this::getBadRequestException);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(this::getBadRequestException);
    }

    @Override
    @Transactional
    public User save(UserPostRequestBody userPostRequestBody) {
        return userRepository.save(UserMapper.INSTANCE.toUser(userPostRequestBody));
    }

    @Override
    public void update(UserPutRequestBody userPutRequestBody) {
        User savedUser = findById(userPutRequestBody.getId());
        User user = UserMapper.INSTANCE.toUser(userPutRequestBody);
        userRepository.save(user.withId(savedUser.getId()));
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    private BadRequestException getBadRequestException() {
        return new BadRequestException("User not found");
    }

}
