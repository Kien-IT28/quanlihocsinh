package com.thpt.quanlyhocsinh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/adminHome")
    public String showAdminHome(){
        return "adminHome";
    }

    @GetMapping("/teacher/Home")
    public String showTeacherHome(){
        return "teacherHome";
    }
}
