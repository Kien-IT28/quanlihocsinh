package com.qlhs.quanlyhocsinh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  // Nhớ import Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Tên người dùng hoặc mật khẩu không chính xác.");
        }
        return "login"; // Phải trả về tên file login.html
    }
}
