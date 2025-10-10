package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.UserCreateRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.entity.UserEntity;
import com.devteria.identity_service.repository.UserRepository;
import com.devteria.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserCreateRequest request) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(request.getPassword());
        userEntity.setEmail(request.getEmail());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setDayOfBirth(request.getDayOfBirth());

        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserEntity updateUser(String userId, UserUpdateRequest request) {
        UserEntity userEntity = getUserById(userId);

        userEntity.setPassword(request.getPassword());
        userEntity.setEmail(request.getEmail());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setDayOfBirth(request.getDayOfBirth());

        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = getUserById(userId);
        userRepository.delete(userEntity);
    }
}
