package com.example.services;

import com.example.domains.User;
import com.example.domains.dto.UserDTO;
import com.example.repositories.UserRepository;
import com.example.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService implements IService<User, UserDTO>, UserDetailsService {

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
    public User save(UserDTO userDTO) {
        User user = MapperUtil.MAPPER.map(userDTO, User.class);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO, Long id) {
        User user = findById(id);
        MapperUtil.MAPPER.map(userDTO, user);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private ResponseStatusException getBadRequestException() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST,  "User not found");
    }

}
