package com.qlhs.quanlyhocsinh.service;

import com.qlhs.quanlyhocsinh.entity.Teacher;
import com.qlhs.quanlyhocsinh.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;

    // Thêm giáo viên mới
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    // Cập nhật thông tin giáo viên
    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    // Xóa giáo viên theo ID
    public void deleteTeacherById(Integer id) {
        teacherRepository.deleteById(id);
    }

    // Lấy thông tin giáo viên theo ID
    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id).orElse(new Teacher());
    }
}

