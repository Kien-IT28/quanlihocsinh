package com.thpt.quanlyhocsinh.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    int accCode;
    String email;
    String username;
    Set<RoleResponse> roles;
}
