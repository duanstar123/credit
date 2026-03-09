/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.mapper;

import org.example.entity.Student;

import java.util.List;

/**
 * 学生数据访问接口
 * @author 段璎芮
 * @version 1.0
 */
public interface StudentMapper {
    Student selectByStudentId(String studentId);
    List<Student> selectAll();
    void insert(Student student);
    void update(Student student);
    void delete(String studentId);
}