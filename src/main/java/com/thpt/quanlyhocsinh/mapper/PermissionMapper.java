package com.thpt.quanlyhocsinh.mapper;

import com.thpt.quanlyhocsinh.dto.request.PermissionRequest;
import com.thpt.quanlyhocsinh.dto.response.PermissionResponse;
import com.thpt.quanlyhocsinh.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

        Permission toPermission(PermissionRequest request);

        PermissionResponse toPermissionResponse(Permission permission);


}
