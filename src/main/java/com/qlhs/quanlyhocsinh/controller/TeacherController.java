package com.qlhs.quanlyhocsinh.controller;

import com.qlhs.quanlyhocsinh.entity.Teacher;
import com.qlhs.quanlyhocsinh.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/teachers")
public class TeacherController {
    private TeacherService teacherService;

    // Hiển thị form giáo viên chung
    @GetMapping("/form")
    public String showTeacherForm(@RequestParam(required = false) String action,
                                  @RequestParam(required = false) Integer teacherId,
                                  Model model) {
        // Nếu teacherId không null, tìm kiếm thông tin giáo viên
        Teacher teacher = (teacherId != null) ? teacherService.getTeacherById(teacherId) : new Teacher();

        // Đặt thông tin cần thiết vào model
        model.addAttribute("teacher", teacher);
        model.addAttribute("action", action);
        return "teacherForm";  // Trả về template form
    }

    // Xử lý submit form cho tất cả các hành động
    @PostMapping("/submit")
    public String submitTeacherForm(@RequestParam String action,
                                    @ModelAttribute Teacher teacher) {
        switch (action) {
            case "add":
                teacherService.addTeacher(teacher);
                break;
            case "edit":
                teacherService.updateTeacher(teacher);
                break;
            case "delete":
                teacherService.deleteTeacherById(teacher.getId());
                break;
        }
        return "redirect:/admin/teachers/form";  // Quay lại form sau khi xử lý
    }
}


