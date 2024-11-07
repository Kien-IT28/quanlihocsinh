package com.thpt.quanlyhocsinh.mapper;

import com.thpt.quanlyhocsinh.dto.request.RoleRequest;
import com.thpt.quanlyhocsinh.dto.response.RoleResponse;
import com.thpt.quanlyhocsinh.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
        @Mapping(target = "permissions", ignore = true)
        Role toRole(RoleRequest request);

        RoleResponse toRoleResponse(Role role);
}
