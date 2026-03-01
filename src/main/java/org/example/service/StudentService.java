package org.example.service;

import org.example.entity.Student;

public interface StudentService {
    Student login(String studentId, String password);
    Student getStudentById(String studentId);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String studentId);
}