package org.example.controller;

import org.example.entity.CreditApplication;
import org.example.entity.CreditCategory;
import org.example.entity.ClassInfo;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.service.ClassService;
import org.example.service.CreditCategoryService;
import org.example.service.CreditService;
import org.example.service.StudentService;
import org.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teacher")  // 修改：移除 /credit 前缀
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private CreditCategoryService creditCategoryService;
    @Autowired
    private ClassService classService;

    @GetMapping("/login")
    public ModelAndView teacherLogin(
            @RequestParam String teacherId,
            @RequestParam String password,
            HttpSession session) {
        ModelAndView mav = new ModelAndView();
        try {
            // 调用服务层进行登录验证
            Teacher teacher = teacherService.login(teacherId, password);
            if (teacher != null) {
                // 登录成功，将教师信息存入会话
                session.setAttribute("teacher", teacher);
                // 重定向到教师首页
                mav.setViewName("redirect:/teacher/index");
            } else {
                // 登录失败，返回登录页面并显示错误信息
                mav.setViewName("login"); // 直接返回登录页面，而不是重定向
                mav.addObject("error", "教师ID或密码错误");
            }
        } catch (Exception e) {
            // 处理异常
            mav.setViewName("login"); // 直接返回登录页面，而不是重定向
            mav.addObject("error", "登录失败：" + e.getMessage());
        }
        return mav;
    }

    // 教师首页
    @GetMapping("/index")
    public ModelAndView teacherIndex(HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/index");
        // 检查登录状态
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }
        // 将教师信息传递给视图
        mav.addObject("teacher", teacher);
        // 模拟统计数据
        mav.addObject("pendingCount", 3);
        mav.addObject("totalStudents", 100);
        mav.addObject("totalClasses", 5);
        try {
            // 获取待审核申请数量
            List<CreditApplication> pendingApplications = creditService.getPendingApplications();
            int pendingCount = pendingApplications.size();
            mav.addObject("pendingCount", pendingCount);

            // 获取学生总数
            List<Student> students = studentService.getAllStudents();
            int totalStudents = students.size();
            mav.addObject("totalStudents", totalStudents);

            // 获取班级总数
            List<ClassInfo> classes = classService.getAllClasses();
            int totalClasses = classes.size();
            mav.addObject("totalClasses", totalClasses);
        } catch (Exception e) {
            System.out.println("=== 获取统计数据异常 ===");
            e.printStackTrace();
            // 如果获取失败，使用默认值
            mav.addObject("pendingCount", 0);
            mav.addObject("totalStudents", 0);
            mav.addObject("totalClasses", 0);
        }
        return mav;
    }

    // 学生管理页面
    @GetMapping("/students")
    public ModelAndView listStudents() {
        ModelAndView mav = new ModelAndView("teacher/students");
        try {
            System.out.println("=== 教师查看学生列表 ===");
            List<Student> students = studentService.getAllStudents();
            System.out.println("查询到的学生数量: " + students.size());
            for (Student student : students) {
                System.out.println("学生: " + student.getName() + " (" + student.getStudentId() + ")");
            }
            mav.addObject("students", students);
        } catch (Exception e) {
            System.out.println("=== 查看学生列表异常 ===");
            e.printStackTrace();
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }

    // 班级管理页面
    @GetMapping("/classes")
    public ModelAndView manageClasses(HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/classes");
        // 检查登录状态
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }
        // 从服务层获取班级数据
        try {
            System.out.println("=== 教师查看班级列表 ===");
            List<ClassInfo> classes = classService.getAllClasses();
            System.out.println("查询到的班级数量: " + classes.size());
            mav.addObject("classes", classes);
        } catch (Exception e) {
            System.out.println("=== 查看班级列表异常 ===");
            e.printStackTrace();
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/categories")
    public ModelAndView listCategories() {
        ModelAndView mav = new ModelAndView("teacher/categories");
        try {
            System.out.println("=== 教师查看学分类别 ===");
            List<CreditCategory> categories = creditCategoryService.getAllCategories();
            System.out.println("查询到的学分类别数量: " + categories.size());
            mav.addObject("categories", categories);
        } catch (Exception e) {
            System.out.println("=== 查看学分类别异常 ===");
            e.printStackTrace();
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/applications")
    public ModelAndView listApplications() {
        ModelAndView mav = new ModelAndView("teacher/applications");
        try {
            System.out.println("=== 教师查看申请列表 ===");
            List<CreditApplication> applications = creditService.getPendingApplications();
            System.out.println("查询到的待审核申请数量: " + applications.size());
            for (CreditApplication app : applications) {
                System.out.println("申请编号: " + app.getApplicationId() + ", 学生ID: " + app.getStudentId());
            }
            mav.addObject("applications", applications);
        } catch (Exception e) {
            System.out.println("=== 查看申请列表异常 ===");
            e.printStackTrace();
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/review/{id}")
    public ModelAndView reviewApplication(
            @PathVariable("id") String applicationId,
            @RequestParam("status") String status) {
        ModelAndView mav = new ModelAndView("redirect:/teacher/applications");
        try {
            System.out.println("=== 教师审核申请 ===");
            System.out.println("申请编号: " + applicationId);
            System.out.println("审核状态: " + status);

            // 获取申请信息
            CreditApplication application = creditService.getApplicationById(applicationId);
            if (application != null) {
                // 更新审核状态
                application.setStatus(status.equals("通过") ? "已通过" : "已拒绝");
                creditService.updateApplication(application);
                System.out.println("审核成功！");
            } else {
                System.out.println("申请不存在！");
            }
        } catch (Exception e) {
            System.out.println("=== 审核申请异常 ===");
            e.printStackTrace();
        }
        return mav;
    }

    @GetMapping("/reviewed")
    public ModelAndView listReviewedApplications() {
        ModelAndView mav = new ModelAndView("teacher/reviewed");
        try {
            System.out.println("=== 教师查看已审核申请 ===");
            List<CreditApplication> applications = creditService.getReviewedApplications();
            System.out.println("查询到的已审核申请数量: " + applications.size());
            mav.addObject("applications", applications);
        } catch (Exception e) {
            System.out.println("=== 查看已审核申请异常 ===");
            e.printStackTrace();
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/categories/create")
    public ModelAndView createCategoryForm() {
        ModelAndView mav = new ModelAndView("teacher/categories/create");
        return mav;
    }

    @PostMapping("/categories/create")
    public ModelAndView createCategory(
            @RequestParam("categoryId") String categoryId,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("creditValue") Double creditValue) {
        ModelAndView mav = new ModelAndView("redirect:/teacher/categories");
        try {
            System.out.println("=== 教师创建学分类别 ===");
            System.out.println("分类ID: " + categoryId);
            System.out.println("分类名称: " + categoryName);
            System.out.println("学分值: " + creditValue);

            // 创建学分类别对象
            CreditCategory category = new CreditCategory();
            category.setCategoryId(categoryId);
            category.setCategoryName(categoryName);
            category.setCreditValue(creditValue);
            category.setCreateDate(new Date()); // 设置创建日期

            // 保存学分类别
            creditCategoryService.addCategory(category);
            System.out.println("学分类别创建成功！");
        } catch (Exception e) {
            System.out.println("=== 创建学分类别异常 ===");
            e.printStackTrace();
        }
        return mav;
    }

    @GetMapping("/credit/detail/{id}")
    public ModelAndView viewApplicationDetail(@PathVariable("id") String applicationId) {
        ModelAndView mav = new ModelAndView("teacher/credit/detail");
        try {
            System.out.println("=== 查看申请详情 ===");
            System.out.println("申请编号: " + applicationId);

            // 获取申请信息
            CreditApplication application = creditService.getApplicationById(applicationId);
            if (application != null) {
                // 获取学生姓名
                Student student = studentService.getStudentById(application.getStudentId());
                if (student != null) {
                    application.setStudentName(student.getName());
                }
                mav.addObject("application", application);
            } else {
                System.out.println("申请不存在！");
                mav.setViewName("redirect:/teacher/applications");
            }
        } catch (Exception e) {
            System.out.println("=== 查看申请详情异常 ===");
            e.printStackTrace();
            mav.setViewName("redirect:/teacher/applications");
        }
        return mav;
    }

    @GetMapping("/class/create")
    public ModelAndView createClassForm() {
        ModelAndView mav = new ModelAndView("teacher/class/create");
        return mav;
    }

    @PostMapping("/class/create")
    public ModelAndView createClass(
            @RequestParam("classId") String classId,
            @RequestParam("className") String className,
            @RequestParam("grade") String grade,
            @RequestParam("majorId") String majorId,
            @RequestParam("collegeId") String collegeId) {
        ModelAndView mav = new ModelAndView("redirect:/teacher/classes");
        try {
            System.out.println("=== 教师创建班级 ===");
            System.out.println("班级ID: " + classId);
            System.out.println("班级名称: " + className);
            System.out.println("年级: " + grade);
            System.out.println("专业ID: " + majorId);
            System.out.println("院系ID: " + collegeId);

            // 创建班级对象
            ClassInfo classInfo = new ClassInfo();
            classInfo.setClassId(classId);
            classInfo.setClassName(className);
            classInfo.setGrade(grade);
            classInfo.setMajorId(majorId);
            classInfo.setCollegeId(collegeId);

            // 保存班级
            classService.addClass(classInfo);
            System.out.println("班级创建成功！");
        } catch (Exception e) {
            System.out.println("=== 创建班级异常 ===");
            e.printStackTrace();
        }
        return mav;
    }
}