package org.example.mapper;

import org.example.entity.CreditApplication;

import java.util.List;

public interface CreditMapper {
    List<CreditApplication> selectByStudentId(String studentId);
    List<CreditApplication> selectAll();
    List<CreditApplication> selectPending();
    List<CreditApplication> selectReviewed();
    CreditApplication selectByApplicationId(String applicationId);
    void insert(CreditApplication application);
    void update(CreditApplication application);
    void delete(String applicationId);
    List<CreditApplication> selectApprovedByStudentId(String studentId);
    int selectPendingCountByStudentId(String studentId);
    int selectTotalCountByStudentId(String studentId);
}