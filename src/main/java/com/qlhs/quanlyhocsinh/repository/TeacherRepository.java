package com.qlhs.quanlyhocsinh.repository;

import com.qlhs.quanlyhocsinh.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    // Bạn có thể định nghĩa các phương thức truy vấn tùy chỉnh tại đây, nếu cần.
}

