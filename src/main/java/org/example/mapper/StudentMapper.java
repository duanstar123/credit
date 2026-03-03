package org.example.mapper;

import org.example.entity.Student;

import java.util.List;

public interface StudentMapper {
    Student selectByStudentId(String studentId);
    List<Student> selectAll();
    void insert(Student student);
    void update(Student student);
    void delete(String studentId);
}