/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.service;

import org.example.entity.Student;

import java.util.List;

/**
 * 学生数据访问服务接口
 * @author 段璎芮
 * @version 1.0
 */
public interface StudentService {
    Student login(String studentId, String password);
    Student getStudentById(String studentId);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String studentId);
    List<Student> getAllStudents();
}