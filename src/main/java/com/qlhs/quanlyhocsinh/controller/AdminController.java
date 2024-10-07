package com.qlhs.quanlyhocsinh.controller;

import com.qlhs.quanlyhocsinh.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // Thêm tài khoản mới
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        // Logic để thêm tài khoản
        return ResponseEntity.ok("Tài khoản đã được thêm.");
    }

    // Sửa tài khoản
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editAccount(@PathVariable int id, @RequestBody Account account) {
        // Logic để sửa tài khoản
        return ResponseEntity.ok("Tài khoản đã được sửa.");
    }

    // Xóa tài khoản
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
        // Logic để xóa tài khoản
        return ResponseEntity.ok("Tài khoản đã được xóa.");
    }
}

