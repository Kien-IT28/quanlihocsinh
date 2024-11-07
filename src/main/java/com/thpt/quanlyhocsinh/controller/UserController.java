package com.thpt.quanlyhocsinh.controller;

import com.thpt.quanlyhocsinh.dto.request.ApiResponse;
import com.thpt.quanlyhocsinh.dto.request.UserCreationRequest;
import com.thpt.quanlyhocsinh.dto.response.UserResponse;
import com.thpt.quanlyhocsinh.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j // add log
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .message("User created successfully")
                .success(true)
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        List<UserResponse> users = userService.getUsers();
        return ApiResponse.<List<UserResponse>>builder()
                .result(users)
                .message("Fetched all users successfully")
                .success(true)
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable("userId") Integer id) {
        UserResponse user = userService.getUser(id);
        return ApiResponse.<UserResponse>builder()
                .result(user)
                .message("Fetched user successfully")
                .success(true)
                .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        // Lấy thông tin từ UserService
        UserResponse myInfo = userService.getMyInfo();

        // Trả về thông tin trong ApiResponse
        return ApiResponse.<UserResponse>builder()
                .result(myInfo)
                .message("Lấy thông tin thành công!")
                .success(true)
                .build();
    }
}
