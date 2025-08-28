package com.truong.QL50.service;

import com.truong.QL50.dto.UserRequestDTO;
import com.truong.QL50.dto.UserResponseDTO;
import com.truong.QL50.model.User;
import com.truong.QL50.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> res = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User u : users) {
            UserResponseDTO dto = new UserResponseDTO(u.getId(), u.getUsername(), u.getEmail());
            res.add(dto);
        }
        return res;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        return null;
    }
}
