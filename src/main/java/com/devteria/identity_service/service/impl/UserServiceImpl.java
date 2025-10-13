package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.UserCreateRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.UserEntity;
import com.devteria.identity_service.exception.ApplicationException;
import com.devteria.identity_service.enums.ErrorCode;
import com.devteria.identity_service.exception.ResourceConflictException;
import com.devteria.identity_service.exception.ResourceNotFoundException;
import com.devteria.identity_service.mapper.UserMapper;
import com.devteria.identity_service.repository.UserRepository;
import com.devteria.identity_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceConflictException(ErrorCode.EMAIL_EXISTS);
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceConflictException(ErrorCode.USER_NAME_IN_USE);
        }

        UserEntity userEntity = userMapper.toEntity(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(userEntity);
        return userMapper.toDTO(userEntity);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> result = userMapper.toDTOs(userRepository.findAll());
        return result;
    }

    @Override
    public UserResponse getUserById(String userId) {
        UserEntity userEntity = userRepository.findByIdOrThrow(userId);
        return userMapper.toDTO(userEntity);
    }

    @Override
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        UserEntity userEntity = userRepository.findByIdOrThrow(userId);
        userMapper.updateEntity(userEntity, request);

        return userMapper.toDTO(userEntity);
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByIdOrThrow(userId);
        userRepository.delete(userEntity);
    }
}
