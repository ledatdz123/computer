package com.example.servercomputer.service.impl;

import com.example.servercomputer.dto.UserDTO;
import com.example.servercomputer.entity.User;
import com.example.servercomputer.exception.ResourceNotFoundException;
import com.example.servercomputer.repository.RoleRepository;
import com.example.servercomputer.repository.UserRepository;
import com.example.servercomputer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<UserDTO> retrieveUsers() {
        List<User> users = userRepository.findAll();
        return new UserDTO().toListDto(users);
    }

    @Override
    public Optional<UserDTO> getUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found for this id: "+userId));
        return Optional.of(new UserDTO().convertToDto(user));
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public Boolean deleteUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found for this id: " + userId));
        this.userRepository.delete(user);
        return true;
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) throws ResourceNotFoundException {
        User userExist = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("user not found for this id: "+userId));

        userExist.setFirstName(userDTO.getFirstName());
        userExist.setLastName(userDTO.getLastName());
        userExist.setAddress(userDTO.getAddress());
        User user = new User();
        user = userRepository.save(userExist);
        return new UserDTO().convertToDto(user);
    }
}
