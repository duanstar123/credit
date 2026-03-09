/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.service;

import org.example.entity.College;

import java.util.List;

/**
 * 院系数据访问服务接口
 * @author 段璎芮
 * @version 1.0
 */
public interface CollegeService {
    // 查询所有院系
    List<College> getAllColleges();

    // 根据院系ID查询
    College getCollegeById(String collegeId);
}