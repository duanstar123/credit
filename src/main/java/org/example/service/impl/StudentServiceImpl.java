package org.example.service.impl;

import org.example.entity.Student;
import org.example.mapper.StudentMapper;
import org.example.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public Student login(String studentId, String password) {
        try {
            Student student = studentMapper.selectByStudentId(studentId);
            if (student != null && student.getPassword().equals(password)) {
                return student;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student getStudentById(String studentId) {
        try {
            return studentMapper.selectByStudentId(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addStudent(Student student) {
        try {
            studentMapper.insert(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            studentMapper.update(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String studentId) {
        try {
            studentMapper.delete(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}