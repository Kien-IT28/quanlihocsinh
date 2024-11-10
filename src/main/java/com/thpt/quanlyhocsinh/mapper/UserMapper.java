package com.thpt.quanlyhocsinh.mapper;

import com.thpt.quanlyhocsinh.dto.request.UserCreationRequest;
import com.thpt.quanlyhocsinh.dto.request.UserUpdateRequest;
import com.thpt.quanlyhocsinh.dto.response.UserResponse;
import com.thpt.quanlyhocsinh.entity.Role;
import com.thpt.quanlyhocsinh.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "accCode", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "email", source = "email")

    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
