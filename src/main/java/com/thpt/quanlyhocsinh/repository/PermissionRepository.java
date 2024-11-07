package com.thpt.quanlyhocsinh.repository;

import com.thpt.quanlyhocsinh.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository <Permission, String>{
    Optional<Permission> findByName(String name);
    void deleteByName(String name);  // Thêm phương thức này để xóa theo tên
}
