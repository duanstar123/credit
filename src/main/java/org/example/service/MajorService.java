package org.example.service;

import org.example.entity.Major;

import java.util.List;

public interface MajorService {
    // 查询所有专业
    List<Major> getAllMajors();

    // 根据专业ID查询
    Major getMajorById(String majorId);

    // 根据院系ID查询专业
    List<Major> getMajorsByCollegeId(String collegeId);
}