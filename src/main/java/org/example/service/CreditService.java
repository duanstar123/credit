package org.example.service;

import org.example.entity.CreditApplication;
import org.example.entity.CreditCategory;

import java.util.List;
import java.util.Map;

public interface CreditService {
    // 提交学分申请
    void submitApplication(CreditApplication application);

    // 根据申请ID查询
    CreditApplication getApplicationById(String applicationId);

    // 根据学生ID查询申请
    List<CreditApplication> getApplicationsByStudentId(String studentId);

    // 查询待审核申请
    List<CreditApplication> getPendingApplications();

    // 根据条件查询申请
    List<CreditApplication> getApplicationsByCondition(Map<String, Object> condition);

    // 审核申请
    void reviewApplication(CreditApplication application);

    // 计算学生总学分
    double calculateTotalCredit(String studentId);

    // 获取启用的学分类别
    List<CreditCategory> getActiveCategories();
}