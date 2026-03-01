package org.example.service;

import org.example.entity.CreditApplication;

import java.util.List;

public interface CreditService {
    List<CreditApplication> getApplicationsByStudentId(String studentId);
    List<CreditApplication> getAllApplications();
    CreditApplication getApplicationById(String applicationId);
    void submitApplication(CreditApplication application);
    void updateApplication(CreditApplication application);
    void deleteApplication(String applicationId);
    void reviewApplication(CreditApplication application);
    double getTotalCreditByStudentId(String studentId);
    int getPendingApplicationCountByStudentId(String studentId);
    int getTotalApplicationCountByStudentId(String studentId);
}