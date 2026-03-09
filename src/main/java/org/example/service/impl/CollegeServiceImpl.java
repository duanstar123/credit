/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.service.impl;

import org.example.entity.College;
import org.example.mapper.CollegeMapper;
import org.example.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学院数据访问服务实现类
 * @author 段璎芮
 * @version 1.0
 */
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<College> getAllColleges() {
        return collegeMapper.selectAll();
    }

    @Override
    public College getCollegeById(String collegeId) {
        return collegeMapper.selectByCollegeId(collegeId);
    }
}