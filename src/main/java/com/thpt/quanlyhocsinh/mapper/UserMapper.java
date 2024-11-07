package com.thpt.quanlyhocsinh.mapper;

import com.thpt.quanlyhocsinh.dto.request.UserCreationRequest;
import com.thpt.quanlyhocsinh.dto.response.UserResponse;
import com.thpt.quanlyhocsinh.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "mahocsinh", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "email", source = "email")

    User toUser(UserCreationRequest request);

    // Phương thức để ánh xạ Set<Role> thành Set<String>
    @Mapping(target = "roles", expression = "java(mapRolesToStrings(user.getRoles()))")
    UserResponse toUserResponse(User user);

    // Phương thức ánh xạ thủ công để chuyển Set<Role> thành Set<String>
    default Set<String> mapRolesToStrings(Set<String> roles) {
        // Nếu roles là null, trả về null
        if (roles == null) {
            return null;
        }
        return roles;
    }
}
