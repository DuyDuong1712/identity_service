package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.UserCreateRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserCreateRequest request);

    List<UserEntity> getAllUsers();

    UserEntity getUserById(String userId);

    UserEntity updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);
}
