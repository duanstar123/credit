package org.example.controller;

import org.example.entity.CreditApplication;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.service.CreditService;
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
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/credit")  // 修改：移除 /credit 前缀
public class CreditController {

    @Autowired
    private CreditService creditService;


    // 查看学分申请详情
    @GetMapping("/detail")
    public ModelAndView viewApplicationDetail(
            @RequestParam String applicationId,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/credit/detail");
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }

        try {
            // 获取申请详情
            CreditApplication application = creditService.getApplicationById(applicationId);
            mav.addObject("application", application);
        } catch (Exception e) {
            mav.setViewName("redirect:/teacher/applications");  // 修改：移除 /credit 前缀
            mav.addObject("error", "获取申请详情失败：" + e.getMessage());
        }

        return mav;
    }

    // 学生提交学分申请
    @PostMapping("/submit")
    public ModelAndView submitApplication(
            @RequestParam Map<String, Object> params,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/student/applications");  // 修改：移除 /credit 前缀
        // 检查登录状态
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }

        try {
            // 创建学分申请对象
            CreditApplication application = new CreditApplication();
            application.setApplicationId(UUID.randomUUID().toString().replace("-", ""));
            application.setStudentId(student.getStudentId()); // 使用当前登录学生ID
            application.setCategoryId((String) params.get("categoryId"));
            application.setApplicationReason((String) params.get("applicationReason"));
            application.setApplyDate(new Date());
            application.setStatus("未审核");
            // 处理文件上传路径（实际项目中需要处理文件上传）
            application.setScreenshotUrl((String) params.get("screenshotUrl"));
            application.setCertificateUrl((String) params.get("certificateUrl"));
            application.setSourceCodeUrl((String) params.get("sourceCodeUrl"));

            // 调用服务层提交申请
            creditService.submitApplication(application);
            mav.addObject("success", "申请提交成功");
        } catch (Exception e) {
            mav.setViewName("redirect:/student/apply");  // 修改：移除 /credit 前缀
            mav.addObject("error", "提交失败：" + e.getMessage());
        }

        return mav;
    }
    @GetMapping("/applications")
    public ModelAndView viewApplications(HttpSession session) {
        ModelAndView mav = new ModelAndView("credit/applications");
        try {
            // 检查登录状态
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            if (teacher == null) {
                mav.setViewName("redirect:/login");
                return mav;
            }
            // 将教师信息传递给视图
            mav.addObject("teacher", teacher);
            // 从服务层获取所有申请记录
            List<CreditApplication> applications = creditService.getAllApplications();
            mav.addObject("applications", applications);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            mav.setViewName("login");
            mav.addObject("error", "系统错误：" + e.getMessage());
        }
        return mav;
    }

    @PostMapping("/review")
    public ModelAndView reviewApplication(
            @RequestParam String applicationId,
            @RequestParam String status,
            @RequestParam String reviewNote,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("credit/applications");
        try {
            // 检查登录状态
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            if (teacher == null) {
                mav.setViewName("redirect:/login");
                return mav;
            }
            // 获取申请信息
            CreditApplication application = creditService.getApplicationById(applicationId);
            if (application != null) {
                // 更新申请状态
                application.setStatus(status);
                application.setReviewNote(reviewNote);
                application.setReviewerId(teacher.getTeacherId());
                application.setReviewDate(new Date());
                // 保存更新
                creditService.updateApplication(application);
            }
            // 重新获取所有申请记录
            List<CreditApplication> applications = creditService.getAllApplications();
            mav.addObject("applications", applications);
            mav.addObject("teacher", teacher);
            mav.addObject("success", "审核成功！");
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            mav.addObject("error", "审核失败：" + e.getMessage());
        }
        return mav;
    }
}