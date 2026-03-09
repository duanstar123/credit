/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.service;

import org.example.entity.Teacher;

import java.util.List;

/**
 * 教师数据访问服务接口
 * @author 段璎芮
 * @version 1.0
 */
public interface TeacherService {
    // 教师登录
    Teacher login(String teacherId, String password);

    // 添加教师
    void addTeacher(Teacher teacher);

    // 修改教师信息
    void updateTeacher(Teacher teacher);

    // 修改教师密码
    void updatePassword(String teacherId, String newPassword);

    // 根据教师ID查询
    Teacher getTeacherById(String teacherId);
    void deleteTeacher(String teacherId);
    List<Teacher> getAllTeachers();
}