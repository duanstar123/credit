package org.example.controller;

import org.example.entity.ClassInfo;
import org.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/class")  // 修改：移除 /credit 前缀
public class ClassController {

    @Autowired
    private ClassService classService;

    // 创建班级页面
    @GetMapping("/create")
    public ModelAndView createClass(HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/class/create");
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }
        return mav;
    }

    // 提交创建班级
    @PostMapping("/create")
    public ModelAndView submitCreateClass(
            @RequestParam Map<String, Object> params,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/teacher/classes");  // 修改：移除 /credit 前缀
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }

        try {
            // 创建班级对象
            ClassInfo classInfo = new ClassInfo();
            classInfo.setClassId((String) params.get("classId"));
            classInfo.setClassName((String) params.get("className"));
            classInfo.setGrade((String) params.get("grade"));
            classInfo.setMajorId((String) params.get("majorId"));
            classInfo.setCollegeId((String) params.get("collegeId"));
            classInfo.setRemark((String) params.get("remark"));

            // 调用服务层创建班级
            classService.addClass(classInfo);
            mav.addObject("success", "班级创建成功");
        } catch (Exception e) {
            mav.addObject("error", "创建失败：" + e.getMessage());
        }

        return mav;
    }

    // 编辑班级页面
    @GetMapping("/edit")
    public ModelAndView editClass(
            @RequestParam String classId,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/class/edit");
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }

        try {
            // 获取班级信息
            ClassInfo classInfo = classService.getClassById(classId);
            mav.addObject("classInfo", classInfo);
        } catch (Exception e) {
            mav.setViewName("redirect:/teacher/classes");  // 修改：移除 /credit 前缀
            mav.addObject("error", "获取班级信息失败：" + e.getMessage());
        }

        return mav;
    }

    // 提交编辑班级
    @PostMapping("/edit")
    public ModelAndView submitEditClass(
            @RequestParam Map<String, Object> params,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/teacher/classes");  // 修改：移除 /credit 前缀
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }

        try {
            // 创建班级对象
            ClassInfo classInfo = new ClassInfo();
            classInfo.setClassId((String) params.get("classId"));
            classInfo.setClassName((String) params.get("className"));
            classInfo.setGrade((String) params.get("grade"));
            classInfo.setMajorId((String) params.get("majorId"));
            classInfo.setCollegeId((String) params.get("collegeId"));
            classInfo.setRemark((String) params.get("remark"));

            // 调用服务层更新班级
            classService.updateClass(classInfo);
            mav.addObject("success", "班级更新成功");
        } catch (Exception e) {
            mav.addObject("error", "更新失败：" + e.getMessage());
        }

        return mav;
    }
}