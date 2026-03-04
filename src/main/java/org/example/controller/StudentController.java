package org.example.controller;

import org.example.entity.CreditApplication;
import org.example.entity.CreditCategory;
import org.example.entity.Student;
import org.example.service.CreditCategoryService;
import org.example.service.CreditService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private CreditCategoryService creditCategoryService;

    @GetMapping("/login")
    public ModelAndView studentLogin(
            @RequestParam String studentId,
            @RequestParam String password,
            HttpSession session) {
        ModelAndView mav = new ModelAndView();
        try {
            // 调用服务层进行登录验证
            Student student = studentService.login(studentId, password);
            if (student != null) {
                // 登录成功，将学生信息存入会话
                session.setAttribute("student", student);
                // 重定向到学生首页
                mav.setViewName("redirect:/student/index");
            } else {
                // 登录失败，返回登录页面并显示错误信息
                mav.setViewName("login");
                mav.addObject("error", "学生ID或密码错误");
            }
        } catch (Exception e) {
            // 处理异常
            mav.setViewName("login");
            mav.addObject("error", "登录失败：" + e.getMessage());
        }
        return mav;
    }

    // 学生首页
    @GetMapping("/index")
    public ModelAndView studentIndex(HttpSession session) {
        ModelAndView mav = new ModelAndView("student/index");
        // 检查登录状态
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            mav.setViewName("redirect:/student/login");
            return mav;
        }
        // 将学生信息传递给视图
        mav.addObject("student", student);

        // 获取学生的创新学分总数
        double totalCredit = creditService.getTotalCreditByStudentId(student.getStudentId());
        mav.addObject("totalCredit", totalCredit);

        // 获取学生的待审核申请数量
        int pendingCount = creditService.getPendingApplicationCountByStudentId(student.getStudentId());
        mav.addObject("pendingCount", pendingCount);

        // 获取学生的总申请数量
        int totalCount = creditService.getTotalApplicationCountByStudentId(student.getStudentId());
        mav.addObject("totalCount", totalCount);

        return mav;
    }

    @GetMapping("/apply")
    public ModelAndView applyCredit(HttpSession session) {
        ModelAndView mav = new ModelAndView("student/apply");
        try {
            Student student = (Student) session.getAttribute("student");
            if (student != null) {
                // 获取学分类别
                List<CreditCategory> categories = creditCategoryService.getAllCategories();
                System.out.println("=== StudentController.applyCredit ===");
                System.out.println("获取到的学分类别数量: " + categories.size());
                for (CreditCategory category : categories) {
                    System.out.println("  - 类别ID: " + category.getCategoryId() + ", 名称: " + category.getCategoryName() + ", 学分值: " + category.getCreditValue());
                }
                mav.addObject("categories", categories);
                mav.addObject("student", student);
            } else {
                mav.setViewName("redirect:/student/login");
            }
        } catch (Exception e) {
            System.out.println("=== 访问申请页面异常 ===");
            e.printStackTrace();
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }

    @PostMapping("/apply")
    public ModelAndView submitApplication(
            @RequestParam("categoryId") String categoryId,
            @RequestParam("applicationReason") String applicationReason,
            @RequestParam(value = "creditValue", required = false) Double creditValue,
            HttpSession session) {
        ModelAndView mav = new ModelAndView();
        try {
            Student student = (Student) session.getAttribute("student");
            if (student != null) {
                // 检查学分值是否为 null
                if (creditValue == null) {
                    // 学分值为 null，尝试从学分类别中获取
                    CreditCategory category = creditCategoryService.getCategoryById(categoryId);
                    if (category != null) {
                        creditValue = category.getCreditValue();
                        System.out.println("从学分类别中获取学分值：" + creditValue);
                    } else {
                        // 学分类别不存在，返回错误
                        mav.setViewName("student/apply");
                        List<CreditCategory> categories = creditCategoryService.getAllCategories();
                        mav.addObject("categories", categories);
                        mav.addObject("student", student);
                        mav.addObject("error", "学分类别不存在，请重新选择");
                        return mav;
                    }
                }

                // 学分值不为 null，继续处理申请
                CreditApplication application = new CreditApplication();
                // 不设置applicationId，由数据库自动生成
                application.setStudentId(student.getStudentId());
                application.setCategoryId(categoryId);
                application.setApplicationReason(applicationReason);
                application.setCreditValue(creditValue);
                application.setApplyDate(new Date());
                application.setStatus("未审核");

                creditService.submitApplication(application);
                System.out.println("申请提交成功！");

                // 申请提交成功，重定向到申请记录页面
                mav.setViewName("redirect:/student/applications");
            } else {
                // 未登录，重定向到登录页面
                mav.setViewName("redirect:/student/login");
            }
        } catch (Exception e) {
            // 处理异常
            System.out.println("=== 提交申请异常 ===");
            e.printStackTrace();
            mav.setViewName("student/apply");
            Student student = (Student) session.getAttribute("student");
            if (student != null) {
                List<CreditCategory> categories = creditCategoryService.getAllCategories();
                mav.addObject("categories", categories);
                mav.addObject("student", student);
            }
            mav.addObject("error", "提交申请失败：" + e.getMessage());
        }
        return mav;
    }
    // 申请记录页面
    @GetMapping("/applications")
    public ModelAndView listApplications(HttpSession session) {
        ModelAndView mav = new ModelAndView("student/applications");
        try {
            Student student = (Student) session.getAttribute("student");
            if (student != null) {
                System.out.println("=== 学生查看申请记录 ===");
                System.out.println("当前登录学生: " + student.getName() + " (" + student.getStudentId() + ")");

                // 获取学生的申请记录
                List<CreditApplication> applications = creditService.getApplicationsByStudentId(student.getStudentId());
                System.out.println("查询到的申请记录数: " + applications.size());
                for (CreditApplication app : applications) {
                    System.out.println("申请编号: " + app.getApplicationId() + ", 状态: " + app.getStatus() + ", 学分值: " + app.getCreditValue());
                }

                mav.addObject("applications", applications);
            } else {
                mav.setViewName("redirect:/student/login");
            }
        } catch (Exception e) {
            System.out.println("=== 查看申请记录异常 ===");
            e.printStackTrace();
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }
}