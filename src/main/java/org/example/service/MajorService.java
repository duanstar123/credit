/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.service;

import org.example.entity.Major;

import java.util.List;

/**
 * 专业数据访问服务接口
 * @author 段璎芮
 * @version 1.0
 */
public interface MajorService {
    // 查询所有专业
    List<Major> getAllMajors();

    // 根据专业ID查询
    Major getMajorById(String majorId);

    // 根据院系ID查询专业
    List<Major> getMajorsByCollegeId(String collegeId);
}