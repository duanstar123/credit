package org.example.service;

import org.example.entity.ClassInfo;

import java.util.List;
import java.util.Map;

public interface ClassService {
    // 添加班级
    void addClass(ClassInfo classInfo);

    // 更新班级信息
    void updateClass(ClassInfo classInfo);

    // 根据班级ID查询
    ClassInfo getClassById(String classId);

    // 根据条件查询班级
    List<ClassInfo> getClassesByCondition(Map<String, Object> condition);

    // 查询所有班级
    List<ClassInfo> getAllClasses();

    // 根据专业ID查询班级
    List<ClassInfo> getClassesByMajorId(String majorId);

    // 根据院系ID查询班级
    List<ClassInfo> getClassesByCollegeId(String collegeId);
}