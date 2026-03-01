package org.example.service;

import org.example.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface StudentService {
    // 学生登录
    Student login(String studentId, String password);

    // 添加学生
    void addStudent(Student student);

    // 根据条件查询学生
    List<Student> getStudentsByCondition(Map<String, Object> condition);

    // Excel 导入学生
    void importStudents(MultipartFile file);
}