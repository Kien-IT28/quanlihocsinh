package com.thpt.quanlyhocsinh.controller;

import com.nimbusds.jose.JOSEException;
import com.thpt.quanlyhocsinh.dto.request.ApiResponse;
import com.thpt.quanlyhocsinh.dto.request.AuthenticationRequest;
import com.thpt.quanlyhocsinh.dto.request.IntrospectRequest;
import com.thpt.quanlyhocsinh.dto.request.UserUpdateRequest;
import com.thpt.quanlyhocsinh.dto.response.AuthenticationResponse;
import com.thpt.quanlyhocsinh.dto.response.IntrospectResponse;
import com.thpt.quanlyhocsinh.dto.response.UserResponse;
import com.thpt.quanlyhocsinh.service.AuthenticationService;
import com.thpt.quanlyhocsinh.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    UserService userService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .message("Xác thực thành công!")
                .success(true)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspectToken(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .message("Introspection successful")
                .success(true)
                .build();
    }
    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable int accCode, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(accCode, request))
                .build();
    }
}
