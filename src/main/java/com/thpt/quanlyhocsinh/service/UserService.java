package com.thpt.quanlyhocsinh.service;

import com.thpt.quanlyhocsinh.dto.request.UserCreationRequest;
import com.thpt.quanlyhocsinh.dto.response.UserResponse;
import com.thpt.quanlyhocsinh.entity.User;
import com.thpt.quanlyhocsinh.enums.Role;
import com.thpt.quanlyhocsinh.exception.AppException;
import com.thpt.quanlyhocsinh.exception.ErrorCode;
import com.thpt.quanlyhocsinh.mapper.UserMapper;
import com.thpt.quanlyhocsinh.repository.RoleRepository;
import com.thpt.quanlyhocsinh.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.STUDENT.name());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        // Ghi nhật ký khi tạo người dùng thành công
        log.info("Người dùng đã được tạo thành công với tên đăng nhập: {}", savedUser.getUsername());

        return userMapper.toUserResponse(savedUser);
    }

    // Kiem tra truoc khi vao duoc Method
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    // Kiem tra sau khi Method duoc thuc hien xong
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(Integer id) {
        log.info("In method get User by Id");

        // Lấy thông tin của người dùng đã xác thực
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();  // Username của người dùng hiện tại

        // Lấy thông tin người dùng theo id
        UserResponse userResponse = userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));

        // Kiểm tra xem người dùng hiện tại có phải là chủ sở hữu của tài khoản hay không
        if (!userResponse.getUsername().equals(currentUsername)) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }

        return userResponse;
    }
    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name)
                .orElseThrow(()-> new AppException(ErrorCode.UNAUTHENTICATED));

        return userMapper.toUserResponse(user);
    }
}
