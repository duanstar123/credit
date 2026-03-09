/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.service;

import org.example.entity.CreditApplication;

import java.util.List;

/**
 * 学分申请数据访问服务接口
 * @author 段璎芮
 * @version 1.0
 */
public interface CreditService {
    List<CreditApplication> getApplicationsByStudentId(String studentId);
    List<CreditApplication> getAllApplications();
    List<CreditApplication> getPendingApplications();
    List<CreditApplication> getReviewedApplications();
    CreditApplication getApplicationById(String applicationId);
    void submitApplication(CreditApplication application);
    void updateApplication(CreditApplication application);
    void deleteApplication(String applicationId);
    void reviewApplication(CreditApplication application);
    double getTotalCreditByStudentId(String studentId);
    int getPendingApplicationCountByStudentId(String studentId);
    int getTotalApplicationCountByStudentId(String studentId);

}