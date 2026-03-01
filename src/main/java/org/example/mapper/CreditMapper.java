package org.example.mapper;

import org.example.entity.CreditApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CreditMapper {
    // 插入学分申请
    void insert(CreditApplication application);

    // 根据申请ID查询
    CreditApplication selectById(String applicationId);

    // 根据学生ID查询申请
    List<CreditApplication> selectByStudentId(String studentId);

    // 根据状态查询申请
    List<CreditApplication> selectByStatus(String status);

    // 根据条件查询申请
    List<CreditApplication> selectByCondition(Map<String, Object> condition);

    // 更新申请状态
    void update(CreditApplication application);

    // 计算学生总学分
    Double calculateTotalCredit(String studentId);
}