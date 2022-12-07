package com.example.servercomputer.service;

import com.example.servercomputer.dto.UserDTO;
import com.example.servercomputer.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserDTO> retrieveUsers();

    public Optional<UserDTO> getUser(Long userId) throws ResourceNotFoundException;

    public UserDTO saveUser(UserDTO userDTO);

    public Boolean deleteUser(Long userId) throws ResourceNotFoundException;

    UserDTO updateUser(Long userId, UserDTO userDTO) throws ResourceNotFoundException;
}
