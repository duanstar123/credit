package org.example.controller;

import org.example.entity.CreditCategory;
import org.example.service.CategoryService;
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
@RequestMapping("/category")  // 修改：移除 /credit 前缀
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 创建学分类别页面
    @GetMapping("/create")
    public ModelAndView createCategory(HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/category/create");
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }
        return mav;
    }

    // 提交创建学分类别
    @PostMapping("/create")
    public ModelAndView submitCreateCategory(
            @RequestParam Map<String, Object> params,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/teacher/categories");  // 修改：移除 /credit 前缀
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }

        try {
            // 创建学分类别对象
            CreditCategory category = new CreditCategory();
            category.setCategoryId((String) params.get("categoryId"));
            category.setCategoryName((String) params.get("categoryName"));
            category.setCreditValue(Double.parseDouble((String) params.get("creditValue")));
            category.setRemark((String) params.get("remark"));
            category.setStatus(1); // 默认为启用状态

            // 调用服务层创建学分类别
            categoryService.addCategory(category);
            mav.addObject("success", "学分类别创建成功");
        } catch (Exception e) {
            mav.addObject("error", "创建失败：" + e.getMessage());
        }

        return mav;
    }

    // 编辑学分类别页面
    @GetMapping("/edit")
    public ModelAndView editCategory(
            @RequestParam String categoryId,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher/category/edit");
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }

        try {
            // 获取学分类别信息
            CreditCategory category = categoryService.getCategoryById(categoryId);
            mav.addObject("category", category);
        } catch (Exception e) {
            mav.setViewName("redirect:/teacher/categories");  // 修改：移除 /credit 前缀
            mav.addObject("error", "获取学分类别信息失败：" + e.getMessage());
        }

        return mav;
    }

    // 提交编辑学分类别
    @PostMapping("/edit")
    public ModelAndView submitEditCategory(
            @RequestParam Map<String, Object> params,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/teacher/categories");  // 修改：移除 /credit 前缀
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }

        try {
            // 创建学分类别对象
            CreditCategory category = new CreditCategory();
            category.setCategoryId((String) params.get("categoryId"));
            category.setCategoryName((String) params.get("categoryName"));
            category.setCreditValue(Double.parseDouble((String) params.get("creditValue")));
            category.setRemark((String) params.get("remark"));

            // 调用服务层更新学分类别
            categoryService.updateCategory(category);
            mav.addObject("success", "学分类别更新成功");
        } catch (Exception e) {
            mav.addObject("error", "更新失败：" + e.getMessage());
        }

        return mav;
    }

    // 更新学分类别状态
    @GetMapping("/status")
    public ModelAndView updateCategoryStatus(
            @RequestParam String categoryId,
            @RequestParam int status,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/teacher/categories");  // 修改：移除 /credit 前缀
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            mav.setViewName("redirect:/login");  // 修改：移除 /credit 前缀
            return mav;
        }

        try {
            // 调用服务层更新状态
            categoryService.updateCategoryStatus(categoryId, status);
            mav.addObject("success", "状态更新成功");
        } catch (Exception e) {
            mav.addObject("error", "状态更新失败：" + e.getMessage());
        }

        return mav;
    }
}