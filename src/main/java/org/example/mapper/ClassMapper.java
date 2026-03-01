package org.example.mapper;

import org.example.entity.ClassInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClassMapper {
    // 查询所有班级
    List<ClassInfo> selectAll();

    // 根据班级ID查询
    ClassInfo selectByClassId(String classId);

    // 根据条件查询班级
    List<ClassInfo> selectByCondition(Map<String, Object> condition);

    // 根据专业ID查询班级
    List<ClassInfo> selectByMajorId(String majorId);

    // 根据院系ID查询班级
    List<ClassInfo> selectByCollegeId(String collegeId);

    // 插入班级
    void insert(ClassInfo classInfo);

    // 更新班级信息
    void update(ClassInfo classInfo);
}