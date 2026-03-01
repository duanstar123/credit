package org.example.service;

import org.example.entity.College;

import java.util.List;

public interface CollegeService {
    // 查询所有院系
    List<College> getAllColleges();

    // 根据院系ID查询
    College getCollegeById(String collegeId);
}