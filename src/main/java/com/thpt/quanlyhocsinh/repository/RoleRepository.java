package com.thpt.quanlyhocsinh.repository;

import com.thpt.quanlyhocsinh.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
}
