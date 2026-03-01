package org.example.mapper;

import org.example.entity.College;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollegeMapper {
    // 查询所有院系
    List<College> selectAll();

    // 根据院系ID查询
    College selectByCollegeId(String collegeId);

    // 插入院系
    void insert(College college);

    // 更新院系信息
    void update(College college);
}