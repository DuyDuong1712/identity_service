package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.UserCreateRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreateRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(String userId);

    UserResponse updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);

    UserResponse getMyInfo();
}
