package org.example.controller;

import org.example.entity.Teacher;
import org.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")  // 修改：移除 /credit 前缀
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

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
        return mav;
    }

    // 学生管理页面
    @GetMapping("/students")
    public ModelAndView manageStudents(HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/students");
        // 检查登录状态
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }
        // 模拟学生数据
        // 实际项目中应该从服务层获取
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
        // 模拟班级数据
        // 实际项目中应该从服务层获取
        return mav;
    }

    // 学分类别管理页面
    @GetMapping("/categories")
    public ModelAndView manageCategories(HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/categories");
        // 检查登录状态
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }
        // 模拟学分类别数据
        // 实际项目中应该从服务层获取
        return mav;
    }

    // 学分申请审核页面
    @GetMapping("/applications")
    public ModelAndView reviewApplications(HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/applications");
        // 检查登录状态
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }
        // 模拟申请数据
        // 实际项目中应该从服务层获取
        return mav;
    }
}