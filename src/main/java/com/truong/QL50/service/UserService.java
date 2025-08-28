package com.truong.QL50.service;

import com.truong.QL50.dto.UserRequestDTO;
import com.truong.QL50.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserRequestDTO dto);
}
