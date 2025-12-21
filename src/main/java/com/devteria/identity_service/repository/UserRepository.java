package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.UserEntity;
import com.devteria.identity_service.enums.ErrorCode;
import com.devteria.identity_service.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    default UserEntity findByIdOrThrow(String userId) {
        return findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    default UserEntity findByUsernameOrThrow(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
