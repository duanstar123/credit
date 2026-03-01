package org.example.service.impl;

import org.example.entity.ClassInfo;
import org.example.mapper.ClassMapper;
import org.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public void addClass(ClassInfo classInfo) {
        classMapper.insert(classInfo);
    }

    @Override
    public void updateClass(ClassInfo classInfo) {
        classMapper.update(classInfo);
    }

    @Override
    public ClassInfo getClassById(String classId) {
        return classMapper.selectByClassId(classId);
    }

    @Override
    public List<ClassInfo> getClassesByCondition(Map<String, Object> condition) {
        return classMapper.selectByCondition(condition);
    }

    @Override
    public List<ClassInfo> getAllClasses() {
        return classMapper.selectAll();
    }

    @Override
    public List<ClassInfo> getClassesByMajorId(String majorId) {
        return classMapper.selectByMajorId(majorId);
    }

    @Override
    public List<ClassInfo> getClassesByCollegeId(String collegeId) {
        return classMapper.selectByCollegeId(collegeId);
    }
}