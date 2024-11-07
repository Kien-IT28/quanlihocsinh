package com.thpt.quanlyhocsinh.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    int mahocsinh;

    @Size(min = 6, message = "USERNAME_INVALID")
    String username;
    @Size(min = 10, message = "EMAIL_INVALID")
    String email;
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
}
