/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.mapper;

import org.example.entity.CreditApplication;

import java.util.List;

/**
 * 学分申请数据访问接口
 * @author 段璎芮
 * @version 1.0
 */
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