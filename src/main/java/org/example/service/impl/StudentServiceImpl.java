package org.example.service.impl;

import org.example.entity.Student;
import org.example.mapper.StudentMapper;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student login(String studentId, String password) {
        // 根据学号查询学生
        Student student = studentMapper.selectByStudentId(studentId);
        // 验证密码
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null;
    }
    @Override
    public void addStudent(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public List<Student> getStudentsByCondition(Map<String, Object> condition) {
        return studentMapper.selectByCondition(condition);
    }

    @Override
    public void importStudents(MultipartFile file) {
        // 实现 Excel 导入逻辑
    }

}