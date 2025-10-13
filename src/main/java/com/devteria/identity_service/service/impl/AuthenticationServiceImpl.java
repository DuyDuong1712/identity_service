package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.AuthenticationRequest;
import com.devteria.identity_service.dto.response.AuthenticationResponse;
import com.devteria.identity_service.entity.UserEntity;
import com.devteria.identity_service.enums.ErrorCode;
import com.devteria.identity_service.exception.ResourceNotFoundException;
import com.devteria.identity_service.repository.UserRepository;
import com.devteria.identity_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        UserEntity user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean result = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        return AuthenticationResponse.builder()
                .isAuthenticated(result)
                .build();
    }
}
