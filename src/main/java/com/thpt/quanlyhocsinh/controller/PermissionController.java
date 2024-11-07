package com.thpt.quanlyhocsinh.controller;

import com.thpt.quanlyhocsinh.dto.request.ApiResponse;
import com.thpt.quanlyhocsinh.dto.request.PermissionRequest;
import com.thpt.quanlyhocsinh.dto.response.PermissionResponse;
import com.thpt.quanlyhocsinh.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j // add log
public class PermissionController {
    PermissionService permissionService;

    // Add Permission
    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    // Lay all car Permission
    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission){
        permissionService.delete(permission);

        return ApiResponse.<Void>builder().build();
    }
}
