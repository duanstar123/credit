package org.example.service.impl;

import org.example.entity.Teacher;
import org.example.mapper.TeacherMapper;
import org.example.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;

    // 使用构造函数注入
    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Teacher login(String teacherId, String password) {
        Teacher teacher = teacherMapper.selectByTeacherId(teacherId);
        return (teacher != null && teacher.getPassword().equals(password)) ? teacher : null;
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        return teacherMapper.selectByTeacherId(teacherId);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.update(teacher);
    }

    @Override
    public void deleteTeacher(String teacherId) {
        teacherMapper.delete(teacherId);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherMapper.selectAll();
    }

    @Override
    public void updatePassword(String teacherId, String newPassword) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setPassword(newPassword);
        teacherMapper.updatePassword(teacher);
    }
}