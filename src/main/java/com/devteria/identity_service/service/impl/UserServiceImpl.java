package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.UserCreateRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.UserEntity;
import com.devteria.identity_service.enums.Role;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceConflictException(ErrorCode.EMAIL_EXISTS);
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceConflictException(ErrorCode.USER_NAME_IN_USE);
        }

        UserEntity userEntity = userMapper.toEntity(request);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        userEntity.setRoles(roles);

        userRepository.save(userEntity);
        return userMapper.toDTO(userEntity);
    }


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        List<UserResponse> result = userMapper.toDTOs(userRepository.findAll());
        return result;
    }

    @Override
    @PostAuthorize("hasRole('ADMIN') or returnObject.username == authentication.principal.username")
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
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByIdOrThrow(userId);
        userRepository.delete(userEntity);
    }

    @Override
    public UserResponse getMyInfo() {
        SecurityContext context = SecurityContextHolder.getContext();

        String username = context.getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsernameOrThrow(username);

        return userMapper.toDTO(userEntity);
    }
}
