package org.example.service.impl;

import org.example.entity.CreditApplication;
import org.example.entity.Student;
import org.example.mapper.CreditMapper;
import org.example.service.CreditService;
import org.example.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {
    private final CreditMapper creditMapper;
    private final StudentService studentService;

    public CreditServiceImpl(CreditMapper creditMapper, StudentService studentService) {
        this.creditMapper = creditMapper;
        this.studentService = studentService;
    }

    @Override
    public List<CreditApplication> getApplicationsByStudentId(String studentId) {
        try {
            System.out.println("=== CreditService.getApplicationsByStudentId ===");
            System.out.println("查询学生ID: " + studentId);

            List<CreditApplication> applications = creditMapper.selectByStudentId(studentId);
            System.out.println("查询结果数量: " + applications.size());
            for (CreditApplication app : applications) {
                System.out.println("  - 申请编号: " + app.getApplicationId() + ", 状态: " + app.getStatus());
            }

            return applications;
        } catch (Exception e) {
            System.out.println("=== CreditService.getApplicationsByStudentId 异常 ===");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<CreditApplication> getAllApplications() {
        try {
            System.out.println("=== CreditService.getAllApplications ===");
            List<CreditApplication> applications = creditMapper.selectAll();
            System.out.println("查询结果数量: " + applications.size());
            return applications;
        } catch (Exception e) {
            System.out.println("=== CreditService.getAllApplications 异常 ===");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public CreditApplication getApplicationById(String applicationId) {
        try {
            System.out.println("=== CreditService.getApplicationById ===");
            System.out.println("查询申请编号: " + applicationId);
            CreditApplication application = creditMapper.selectByApplicationId(applicationId);
            System.out.println("查询结果: " + (application != null ? "找到" : "未找到"));
            return application;
        } catch (Exception e) {
            System.out.println("=== CreditService.getApplicationById 异常 ===");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void submitApplication(CreditApplication application) {
        try {
            System.out.println("=== CreditService.submitApplication ===");
            System.out.println("申请编号: " + application.getApplicationId());
            System.out.println("学生ID: " + application.getStudentId());
            System.out.println("学分类别: " + application.getCategoryId());
            System.out.println("申请理由: " + application.getApplicationReason());
            System.out.println("申请日期: " + application.getApplyDate());
            System.out.println("状态: " + application.getStatus());
            System.out.println("学分值: " + application.getCreditValue());

            creditMapper.insert(application);
            System.out.println("申请提交成功！");
        } catch (Exception e) {
            System.out.println("=== CreditService.submitApplication 异常 ===");
            e.printStackTrace();
            throw e; // 重新抛出异常，让控制器捕获
        }
    }

    @Override
    public void updateApplication(CreditApplication application) {
        try {
            System.out.println("=== CreditService.updateApplication ===");
            System.out.println("申请编号: " + application.getApplicationId());
            System.out.println("新状态: " + application.getStatus());
            creditMapper.update(application);
            System.out.println("申请更新成功！");
        } catch (Exception e) {
            System.out.println("=== CreditService.updateApplication 异常 ===");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteApplication(String applicationId) {
        try {
            System.out.println("=== CreditService.deleteApplication ===");
            System.out.println("申请编号: " + applicationId);
            creditMapper.delete(applicationId);
            System.out.println("申请删除成功！");
        } catch (Exception e) {
            System.out.println("=== CreditService.deleteApplication 异常 ===");
            e.printStackTrace();
        }
    }

    @Override
    public void reviewApplication(CreditApplication application) {
        try {
            System.out.println("=== CreditService.reviewApplication ===");
            System.out.println("申请编号: " + application.getApplicationId());
            System.out.println("审核状态: " + application.getStatus());
            System.out.println("审核意见: " + application.getReviewNote());
            creditMapper.update(application);
            System.out.println("申请审核成功！");
        } catch (Exception e) {
            System.out.println("=== CreditService.reviewApplication 异常 ===");
            e.printStackTrace();
        }
    }

    @Override
    public double getTotalCreditByStudentId(String studentId) {
        try {
            System.out.println("=== CreditService.getTotalCreditByStudentId ===");
            System.out.println("查询学生ID: " + studentId);

            // 从数据库获取已审核通过的申请，计算总学分
            List<CreditApplication> applications = creditMapper.selectApprovedByStudentId(studentId);
            double totalCredit = 0.0;
            for (CreditApplication app : applications) {
                if (app.getCreditValue() != null) {
                    totalCredit += app.getCreditValue();
                }
            }
            System.out.println("总学分: " + totalCredit);
            return totalCredit;
        } catch (Exception e) {
            System.out.println("=== CreditService.getTotalCreditByStudentId 异常 ===");
            e.printStackTrace();
            return 0.0;
        }
    }

    @Override
    public int getPendingApplicationCountByStudentId(String studentId) {
        try {
            System.out.println("=== CreditService.getPendingApplicationCountByStudentId ===");
            System.out.println("查询学生ID: " + studentId);

            // 从数据库获取待审核的申请数量
            int count = creditMapper.selectPendingCountByStudentId(studentId);
            System.out.println("待审核申请数: " + count);
            return count;
        } catch (Exception e) {
            System.out.println("=== CreditService.getPendingApplicationCountByStudentId 异常 ===");
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getTotalApplicationCountByStudentId(String studentId) {
        try {
            System.out.println("=== CreditService.getTotalApplicationCountByStudentId ===");
            System.out.println("查询学生ID: " + studentId);

            // 从数据库获取总申请数量
            int count = creditMapper.selectTotalCountByStudentId(studentId);
            System.out.println("总申请数: " + count);
            return count;
        } catch (Exception e) {
            System.out.println("=== CreditService.getTotalApplicationCountByStudentId 异常 ===");
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<CreditApplication> getPendingApplications() {
        try {
            System.out.println("=== CreditService.getPendingApplications ===");
            List<CreditApplication> applications = creditMapper.selectPending();
            System.out.println("查询到的待审核申请数量: " + applications.size());

            // 按申请编号排序（提取数字部分进行排序）
            applications.sort((a1, a2) -> {
                // 提取申请编号中的数字部分
                String num1 = a1.getApplicationId().replaceAll("[^0-9]", "");
                String num2 = a2.getApplicationId().replaceAll("[^0-9]", "");

                // 转换为数字进行比较
                try {
                    long n1 = Long.parseLong(num1);
                    long n2 = Long.parseLong(num2);
                    return Long.compare(n1, n2);
                } catch (NumberFormatException e) {
                    // 如果转换失败，使用字符串比较
                    return a1.getApplicationId().compareTo(a2.getApplicationId());
                }
            });

            return applications;
        } catch (Exception e) {
            System.out.println("=== CreditService.getPendingApplications 异常 ===");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    @Override
    public List<CreditApplication> getReviewedApplications() {
        try {
            System.out.println("=== CreditService.getReviewedApplications ===");
            List<CreditApplication> applications = creditMapper.selectReviewed();
            System.out.println("查询到的已审核申请数量: " + applications.size());

            // 按申请编号排序
            applications.sort((a1, a2) -> {
                String num1 = a1.getApplicationId().replaceAll("[^0-9]", "");
                String num2 = a2.getApplicationId().replaceAll("[^0-9]", "");

                try {
                    long n1 = Long.parseLong(num1);
                    long n2 = Long.parseLong(num2);
                    return Long.compare(n1, n2);
                } catch (NumberFormatException e) {
                    return a1.getApplicationId().compareTo(a2.getApplicationId());
                }
            });

            // 为每个申请添加学生姓名
            for (CreditApplication app : applications) {
                Student student = studentService.getStudentById(app.getStudentId());
                if (student != null) {
                    app.setStudentName(student.getName());
                }
            }

            return applications;
        } catch (Exception e) {
            System.out.println("=== CreditService.getReviewedApplications 异常 ===");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}