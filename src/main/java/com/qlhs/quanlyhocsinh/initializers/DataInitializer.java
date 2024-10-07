package com.qlhs.quanlyhocsinh.initializers;

import com.qlhs.quanlyhocsinh.entity.Account;
import com.qlhs.quanlyhocsinh.entity.Role;
import com.qlhs.quanlyhocsinh.repository.AccountRepository;
import com.qlhs.quanlyhocsinh.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Tạo vai trò ADMIN và USER
            Role adminRole = Role.builder().name("ADMIN").build();
            Role userRole = Role.builder().name("USER").build();
            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            // Tạo tài khoản admin
            Account adminAccount = Account.builder()
                    .password(passwordEncoder.encode("admin123"))
                    .roles(new HashSet<>(Set.of(adminRole)))
                    .build();
            accountRepository.save(adminAccount);

            // Tạo tài khoản user
            Account userAccount = Account.builder()
                    .password(passwordEncoder.encode("user123"))
                    .roles(new HashSet<>(Set.of(userRole)))
                    .build();
            accountRepository.save(userAccount);
        };
    }
}

