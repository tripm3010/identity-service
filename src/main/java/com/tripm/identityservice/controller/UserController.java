package com.tripm.identityservice.controller;

import com.tripm.identityservice.dto.request.ApiResponse;
import com.tripm.identityservice.dto.request.UserCreationRequest;
import com.tripm.identityservice.dto.request.UserUpdateRequest;
import com.tripm.identityservice.dto.response.UserResponse;
import com.tripm.identityservice.entity.User;
import com.tripm.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));
       return response;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.getUser(userId));
        return response;
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.updateUser(userId, request));
        return response;
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable String userId) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("User deleted");
        userService.deleteUser(userId);
        return response;
    }
}
