package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.AuthenticationRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.AuthenticationResponse;
import com.devteria.identity_service.enums.ErrorCode;
import com.devteria.identity_service.enums.SuccessCode;
import com.devteria.identity_service.service.AuthenticationService;
import com.devteria.identity_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        if (!response.isAuthenticated()) {
            return ApiResponse.error(ErrorCode.UNAUTHENTICATED);
        }

        return ApiResponse.success(SuccessCode.SUCCESS, response);
    }
}
