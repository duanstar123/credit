package org.example.mapper;

import org.example.entity.Student;

import java.util.List;

public interface StudentMapper {
    Student selectByStudentId(String studentId);
    List<Student> selectAll(); // 确保这个方法存在
    void insert(Student student);
    void update(Student student);
    void delete(String studentId);
}