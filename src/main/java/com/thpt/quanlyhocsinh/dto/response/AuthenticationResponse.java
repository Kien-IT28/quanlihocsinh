package com.thpt.quanlyhocsinh.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;    // Thêm trường token
    boolean authenticated;  // Nếu bạn cần trường này
}