package com.thpt.quanlyhocsinh.repository;

import com.thpt.quanlyhocsinh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Integer>{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional <User> findByUsername(String username);
    User findByAccCode(int accCode);
}
