/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.mapper;

import org.example.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 教师数据访问接口
 * @author 段璎芮
 * @version 1.0
 */
@Mapper
public interface TeacherMapper {
    Teacher selectByTeacherId(String teacherId);
    List<Teacher> selectAll();
    void insert(Teacher teacher);
    void update(Teacher teacher);
    void delete(String teacherId);
    void updatePassword(Teacher teacher);
}