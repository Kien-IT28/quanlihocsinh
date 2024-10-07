package com.qlhs.quanlyhocsinh.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/home")
    public String home() {
        // Lấy thông tin xác thực từ SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .orElse("");

        // Chuyển hướng đến giao diện tương ứng với vai trò
        if (role.equals("ROLE_ADMIN")) {
            return "admin/home";  // Giao diện cho Admin
        } else if (role.equals("ROLE_USER")) {
            return "user/home";  // Giao diện cho User
        } else {
            return "redirect:/login";  // Nếu không có quyền, chuyển hướng đến trang đăng nhập
        }
    }
}