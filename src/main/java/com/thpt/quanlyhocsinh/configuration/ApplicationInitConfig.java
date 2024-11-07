package com.thpt.quanlyhocsinh.configuration;

import com.thpt.quanlyhocsinh.enums.Role;
import com.thpt.quanlyhocsinh.entity.User;
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

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

                User user = User.builder()
                        .username("admin")
                        .email("trungkienlegend.vn@gmail.com")
                        .password(passwordEncoder.encode("admin123"))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("Admin user has been created with default password: Admin, please change it!");
            }
        };
    }
}
