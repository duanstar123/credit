package org.example.mapper;

import org.example.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    // 根据学号查询学生
    Student selectByStudentId(String studentId);

    // 插入学生
    void insert(Student student);

    // 更新学生信息
    void update(Student student);

    // 根据条件查询学生列表
    List<Student> selectByCondition(Map<String, Object> condition);

    // 根据班级ID查询学生
    List<Student> selectByClassId(String classId);

    // 根据专业ID查询学生
    List<Student> selectByMajorId(String majorId);

    // 根据院系ID查询学生
    List<Student> selectByCollegeId(String collegeId);
}