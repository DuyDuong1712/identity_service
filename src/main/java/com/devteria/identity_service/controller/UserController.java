package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.UserCreateRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.entity.UserEntity;
import com.devteria.identity_service.enums.SuccessCode;
import com.devteria.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<UserEntity> createUser(@RequestBody @Valid UserCreateRequest request) {
//         ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Create user successfully");
//        apiResponse.setResult(userService.createUser(request));

        ApiResponse<UserEntity> apiResponse = ApiResponse.success(SuccessCode.CREATED, userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public UserEntity updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
}
