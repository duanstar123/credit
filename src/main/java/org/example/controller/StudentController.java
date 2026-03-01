package org.example.controller;

import org.example.entity.CreditApplication;
import org.example.entity.CreditCategory;
import org.example.entity.Student;
import org.example.service.CreditCategoryService;
import org.example.service.CreditService;
import org.example.service.StudentService;
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
    private final StudentService studentService;
    private final CreditCategoryService creditCategoryService;
    private final CreditService creditService;

    public StudentController(StudentService studentService, CreditCategoryService creditCategoryService, CreditService creditService) {
        this.studentService = studentService;
        this.creditCategoryService = creditCategoryService;
        this.creditService = creditService;
    }

    @GetMapping("/login")
    public ModelAndView studentLogin(
            @RequestParam String studentId,
            @RequestParam String password,
            HttpSession session) {
        ModelAndView mav = new ModelAndView();
        try {
            System.out.println("=== 学生登录 ===");
            System.out.println("登录学号: " + studentId);
            System.out.println("登录密码: " + password);

            Student student = studentService.login(studentId, password);
            if (student != null) {
                System.out.println("登录成功！学生姓名: " + student.getName());
                session.setAttribute("student", student);
                mav.setViewName("redirect:/student/index");
            } else {
                System.out.println("登录失败：学号或密码错误");
                mav.setViewName("login");
                mav.addObject("error", "学号或密码错误");
            }
        } catch (Exception e) {
            System.out.println("=== 登录异常 ===");
            e.printStackTrace();
            mav.setViewName("login");
            mav.addObject("error", "登录失败：" + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/index")
    public ModelAndView studentIndex(HttpSession session) {
        ModelAndView mav = new ModelAndView("student/index");
        try {
            System.out.println("=== 学生首页 ===");
            // 检查登录状态
            Student student = (Student) session.getAttribute("student");
            if (student == null) {
                System.out.println("未登录，跳转到登录页面");
                mav.setViewName("redirect:/login");
                return mav;
            }
            System.out.println("当前登录学生: " + student.getName() + " (" + student.getStudentId() + ")");

            // 从数据库获取学生详细信息
            student = studentService.getStudentById(student.getStudentId());
            if (student != null) {
                System.out.println("从数据库获取学生信息成功");
                System.out.println("学生姓名: " + student.getName());
                System.out.println("性别: " + student.getGender());
                System.out.println("年级: " + student.getGrade());
                System.out.println("专业: " + student.getMajorName());
                System.out.println("班级: " + student.getClassName());
            } else {
                System.out.println("从数据库获取学生信息失败");
            }

            // 将学生信息传递给视图
            mav.addObject("student", student);

            // 从数据库获取学分统计数据
            double totalCredit = creditService.getTotalCreditByStudentId(student.getStudentId());
            int pendingCount = creditService.getPendingApplicationCountByStudentId(student.getStudentId());
            int totalApplications = creditService.getTotalApplicationCountByStudentId(student.getStudentId());

            System.out.println("当前总学分: " + totalCredit);
            System.out.println("待审核申请: " + pendingCount);
            System.out.println("总申请次数: " + totalApplications);

            // 将统计数据传递给视图
            mav.addObject("totalCredit", totalCredit);
            mav.addObject("pendingCount", pendingCount);
            mav.addObject("totalApplications", totalApplications);
        } catch (Exception e) {
            // 处理异常
            System.out.println("=== 学生首页异常 ===");
            e.printStackTrace();
            mav.setViewName("login");
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }
    @GetMapping("/apply")
    public ModelAndView applyCredit(HttpSession session) {
        ModelAndView mav = new ModelAndView("student/apply");
        try {
            System.out.println("=== 申请创新学分 ===");
            // 检查登录状态
            Student student = (Student) session.getAttribute("student");
            if (student == null) {
                System.out.println("未登录，跳转到登录页面");
                mav.setViewName("redirect:/login");
                return mav;
            }
            System.out.println("当前登录学生: " + student.getName() + " (" + student.getStudentId() + ")");
            // 将学生信息传递给视图
            mav.addObject("student", student);
            // 从数据库中获取学分类别数据
            List<CreditCategory> categories = creditCategoryService.getAllCategories();
            System.out.println("获取到的学分类别数量: " + categories.size());
            mav.addObject("categories", categories);
        } catch (Exception e) {
            // 处理异常
            System.out.println("=== 申请创新学分异常 ===");
            e.printStackTrace();
            mav.setViewName("login");
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }

    @PostMapping("/apply")
    public ModelAndView submitApplication(
            @RequestParam String studentId,
            @RequestParam String categoryId,
            @RequestParam String applicationReason,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("student/apply");
        try {
            System.out.println("=== 提交学分申请 ===");
            // 检查登录状态
            Student student = (Student) session.getAttribute("student");
            if (student == null) {
                System.out.println("未登录，跳转到登录页面");
                mav.setViewName("redirect:/login");
                return mav;
            }
            System.out.println("当前登录学生: " + student.getName() + " (" + student.getStudentId() + ")");
            System.out.println("申请学号: " + studentId);
            System.out.println("学分类别ID: " + categoryId);
            System.out.println("申请理由: " + applicationReason);

            // 创建学分申请对象
            CreditApplication application = new CreditApplication();
            application.setApplicationId("APP" + System.currentTimeMillis());
            application.setStudentId(studentId);
            application.setCategoryId(categoryId);
            application.setApplicationReason(applicationReason);
            application.setApplyDate(new Date());
            application.setStatus("未审核");

            // 获取学分类别信息，设置学分值
            CreditCategory category = creditCategoryService.getCategoryById(categoryId);
            if (category != null) {
                System.out.println("学分类别: " + category.getCategoryName() + " (" + category.getCreditValue() + "学分)");
                application.setCreditValue(category.getCreditValue());
            }

            // 保存申请记录
            System.out.println("准备提交申请，申请编号: " + application.getApplicationId());
            creditService.submitApplication(application);
            System.out.println("申请提交成功！");

            // 设置成功信息
            mav.addObject("student", student);
            mav.addObject("categories", creditCategoryService.getAllCategories());
            mav.addObject("success", "学分申请提交成功！");
        } catch (Exception e) {
            // 处理异常
            System.out.println("=== 提交申请异常 ===");
            e.printStackTrace();
            mav.addObject("error", "提交失败：" + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/applications")
    public ModelAndView viewApplications(HttpSession session) {
        ModelAndView mav = new ModelAndView("student/applications");
        try {
            System.out.println("=== 查看申请记录 ===");
            // 检查登录状态
            Student student = (Student) session.getAttribute("student");
            if (student == null) {
                System.out.println("未登录，跳转到登录页面");
                mav.setViewName("redirect:/login");
                return mav;
            }
            System.out.println("当前登录学生: " + student.getName() + " (" + student.getStudentId() + ")");
            // 将学生信息传递给视图
            mav.addObject("student", student);
            // 从服务层获取申请记录
            System.out.println("查询学生ID: " + student.getStudentId() + " 的申请记录");
            List<CreditApplication> applications = creditService.getApplicationsByStudentId(student.getStudentId());
            System.out.println("查询到的申请记录数: " + applications.size());
            for (CreditApplication app : applications) {
                System.out.println("申请编号: " + app.getApplicationId() + ", 状态: " + app.getStatus() + ", 学分值: " + app.getCreditValue());
            }
            mav.addObject("applications", applications);
        } catch (Exception e) {
            // 处理异常
            System.out.println("=== 查看申请记录异常 ===");
            e.printStackTrace();
            mav.setViewName("login");
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }
}