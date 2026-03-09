/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.service.impl;

import org.example.entity.Major;
import org.example.mapper.MajorMapper;
import org.example.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专业数据访问服务实现类
 * @author 段璎芮
 * @version 1.0
 */
@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public List<Major> getAllMajors() {
        return majorMapper.selectAll();
    }

    @Override
    public Major getMajorById(String majorId) {
        return majorMapper.selectByMajorId(majorId);
    }

    @Override
    public List<Major> getMajorsByCollegeId(String collegeId) {
        return majorMapper.selectByCollegeId(collegeId);
    }
}