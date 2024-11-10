package com.thpt.quanlyhocsinh.configuration;

import com.thpt.quanlyhocsinh.entity.Role;
import com.thpt.quanlyhocsinh.entity.User;
import com.thpt.quanlyhocsinh.repository.RoleRepository;
import com.thpt.quanlyhocsinh.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            log.info("Initializing application.....");

            // Kiểm tra nếu user admin chưa có trong database
            if (userRepository.findByUsername("admin").isEmpty()) {
                // Tạo vai trò ADMIN nếu chưa có
                Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> {
                    return roleRepository.save(Role.builder()
                            .name("ADMIN")
                            .description("Role quản trị viên")
                            .build());
                });

                // Tạo vai trò USER nếu chưa có
                Role teacherRole = roleRepository.findByName("TEACHER").orElseGet(() -> {
                    return roleRepository.save(Role.builder()
                            .name("TEACHER")
                            .description("Role giáo viên")
                            .build());
                });

                // Tạo vai trò USER nếu chưa có
                Role studentRole = roleRepository.findByName("STUDENT").orElseGet(() -> {
                    return roleRepository.save(Role.builder()
                            .name("STUDENT")
                            .description("Role học sinh")
                            .build());
                });

                // Gán các vai trò cho người dùng admin
                var roles = new HashSet<Role>();
                roles.add(adminRole);
                roles.add(teacherRole);  // Thêm vai trò TEACHER cho admin
                roles.add(studentRole); // Thêm vai trò STUDENT cho admin

                // Tạo người dùng admin và lưu vào cơ sở dữ liệu
                User user = User.builder()
                        .username("admin")
                        .email("trungkienlegend.vn@gmail.com")
                        .password(passwordEncoder.encode("admin123"))
                        .roles(roles)
                        .build();

                userRepository.save(user);

                log.warn("Admin user has been created with default password: admin123, please change it!");
            }

            log.info("Application initialization completed .....");

        };
    }
}
