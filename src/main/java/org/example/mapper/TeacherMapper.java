package org.example.mapper;

import org.example.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    Teacher selectByTeacherId(String teacherId);
    List<Teacher> selectAll();
    void insert(Teacher teacher);
    void update(Teacher teacher);
    void delete(String teacherId);
    // 添加 updatePassword 方法
    void updatePassword(Teacher teacher);
}