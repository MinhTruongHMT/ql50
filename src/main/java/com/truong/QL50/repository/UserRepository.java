package com.truong.QL50.repository;

import com.truong.QL50.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
