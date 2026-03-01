package org.example.mapper;

import org.example.entity.Student;

public interface StudentMapper {
    Student selectByStudentId(String studentId);
    void insert(Student student);
    void update(Student student);
    void delete(String studentId);
}