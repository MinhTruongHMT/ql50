package com.truong.QL50.repository;

import com.truong.QL50.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class InMemoryUserRepository implements UserRepository{
    private List<User> users;

    public InMemoryUserRepository() {
        users = new ArrayList<>();
        User u1 = new User("Truong","tRuOnG@gmail.com","");
        User u2 = new User("Truong","tRuOnG@gmail.com","");
        User u3 = new User("Truong","tRuOnG@gmail.com","");
        User u4 = new User("Truong","tRuOnG@gmail.com","");
        User u5 = new User("Truong","tRuOnG@gmail.com","");
        User u6 = new User("Truong","tRuOnG@gmail.com","");
        User u7 = new User("Truong","tRuOnG@gmail.com","");
        User u8 = new User("Truong","tRuOnG@gmail.com","");
        User u9 = new User("Truong","tRuOnG@gmail.com","");
        User u10 = new User("Truong","tRuOnG@gmail.com","");
        users.addAll(List.of(u1,u2,u3,u4,u5,u6,u7,u8,u9,u10));
    }


    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
