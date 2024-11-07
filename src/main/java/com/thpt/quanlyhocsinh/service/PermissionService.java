package com.thpt.quanlyhocsinh.service;

import com.thpt.quanlyhocsinh.dto.request.PermissionRequest;
import com.thpt.quanlyhocsinh.dto.response.PermissionResponse;  // Sửa lỗi chính tả ở đây
import com.thpt.quanlyhocsinh.entity.Permission;
import com.thpt.quanlyhocsinh.mapper.PermissionMapper;
import com.thpt.quanlyhocsinh.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);

        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    public void delete(String permissionName) {
        var permission = permissionRepository.findByName(permissionName)
                .orElseThrow(() -> new RuntimeException("Permission with name " + permissionName + " not found"));
        permissionRepository.delete(permission);
        log.info("Permission with name '{}' has been deleted", permissionName);
    }
}
