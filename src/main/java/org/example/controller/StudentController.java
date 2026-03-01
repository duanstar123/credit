package org.example.controller;

import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")  // 修改：移除 /credit 前缀
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 学生登录
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
                mav.setViewName("login"); // 直接返回登录页面，而不是重定向
                mav.addObject("error", "学号或密码错误");
            }
        } catch (Exception e) {
            // 处理异常
            mav.setViewName("login"); // 直接返回登录页面，而不是重定向
            mav.addObject("error", "登录失败：" + e.getMessage());
        }
        return mav;
    }

    @GetMapping("/index")
    public ModelAndView studentIndex(HttpSession session) {
        ModelAndView mav = new ModelAndView("student/index");
        try {
            // 检查登录状态
            Student student = (Student) session.getAttribute("student");
            if (student == null) {
                mav.setViewName("redirect:/login");
                return mav;
            }
            // 设置缺失的属性
            student.setMajorName("计算机科学与技术"); // 实际项目中应该从数据库查询
            student.setClassName("计科1班"); // 实际项目中应该从数据库查询
            // 将学生信息传递给视图
            mav.addObject("student", student);
            // 模拟学分统计数据
            mav.addObject("totalCredit", 4.5);
            mav.addObject("pendingCount", 2);
            mav.addObject("totalApplications", 5);
        } catch (Exception e) {
            // 处理异常
            mav.setViewName("login");
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }

    // 学分申请页面
    @GetMapping("/apply")
    public ModelAndView applyCredit(HttpSession session) {
        ModelAndView mav = new ModelAndView("student/apply");
        // 检查登录状态
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }
        // 将学生信息传递给视图
        mav.addObject("student", student);
        // 模拟学分类别数据
        // 实际项目中应该从服务层获取
        return mav;
    }

    // 申请记录页面
    @GetMapping("/applications")
    public ModelAndView viewApplications(HttpSession session) {
        ModelAndView mav = new ModelAndView("student/applications");
        // 检查登录状态
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }
        // 将学生信息传递给视图
        mav.addObject("student", student);
        // 模拟申请记录数据
        // 实际项目中应该从服务层获取
        return mav;
    }
}
