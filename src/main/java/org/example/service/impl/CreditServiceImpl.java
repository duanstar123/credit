package org.example.service.impl;

import org.example.entity.CreditApplication;
import org.example.entity.CreditCategory;
import org.example.mapper.CreditMapper;
import org.example.mapper.CategoryMapper;
import org.example.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditMapper creditMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void submitApplication(CreditApplication application) {
        creditMapper.insert(application);
    }

    @Override
    public CreditApplication getApplicationById(String applicationId) {
        return creditMapper.selectById(applicationId);
    }

    @Override
    public List<CreditApplication> getApplicationsByStudentId(String studentId) {
        return creditMapper.selectByStudentId(studentId);
    }

    @Override
    public List<CreditApplication> getPendingApplications() {
        return creditMapper.selectByStatus("未审核");
    }

    @Override
    public List<CreditApplication> getApplicationsByCondition(Map<String, Object> condition) {
        return creditMapper.selectByCondition(condition);
    }

    @Override
    public void reviewApplication(CreditApplication application) {
        creditMapper.update(application);
    }

    @Override
    public double calculateTotalCredit(String studentId) {
        Double totalCredit = creditMapper.calculateTotalCredit(studentId);
        return totalCredit != null ? totalCredit : 0.0;
    }

    @Override
    public List<CreditCategory> getActiveCategories() {
        return categoryMapper.selectActive();
    }
}