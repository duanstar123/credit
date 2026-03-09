/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.controller;

import org.example.entity.CreditCategory;
import org.example.service.CreditCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Date;

/**
 * 学分类别控制器
 * @author 段璎芮
 * @version 1.0
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CreditCategoryService creditCategoryService;
    public CategoryController(CreditCategoryService creditCategoryService) {
        this.creditCategoryService = creditCategoryService;
    }

    // 跳转到创建学分类别页面
    @GetMapping("/create")
    public String createCategory(HttpSession session) {
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            return "redirect:/login";
        }
        return "teacher/category/create";
    }

    // 创建学分类别
    @PostMapping("/create")
    public String addCategory(CreditCategory category, HttpSession session) {
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            return "redirect:/login";
        }

        try {
            category.setCreateDate(new Date());
            category.setStatus(1);
            creditCategoryService.addCategory(category);
            return "redirect:/teacher/categories?success=学分类别创建成功";
        } catch (Exception e) {
            return "redirect:/teacher/categories?error=学分类别创建失败：" + e.getMessage();
        }
    }

    // 跳转到编辑学分类别页面
    @GetMapping("/edit")
    public String editCategory(@RequestParam("categoryId") String categoryId, Model model, HttpSession session) {
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            return "redirect:/login";
        }

        try {
            CreditCategory category = creditCategoryService.getCategoryById(categoryId);
            model.addAttribute("category", category);
            return "teacher/category/edit";
        } catch (Exception e) {
            return "redirect:/teacher/categories?error=获取学分类别信息失败：" + e.getMessage();
        }
    }

    // 更新学分类别
    @PostMapping("/edit")
    public String updateCategory(CreditCategory category, HttpSession session) {
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            return "redirect:/login";
        }

        try {
            creditCategoryService.updateCategory(category);
            return "redirect:/teacher/categories?success=学分类别更新成功";
        } catch (Exception e) {
            return "redirect:/teacher/categories?error=学分类别更新失败：" + e.getMessage();
        }
    }

    // 更新学分类别状态
    @GetMapping("/status")
    public String updateCategoryStatus(@RequestParam("categoryId") String categoryId, @RequestParam("status") int status, HttpSession session) {
        // 检查登录状态
        if (session.getAttribute("teacher") == null) {
            return "redirect:/login";
        }

        try {
            CreditCategory category = creditCategoryService.getCategoryById(categoryId);
            category.setStatus(status);
            creditCategoryService.updateCategory(category);
            return "redirect:/teacher/categories?success=学分类别状态更新成功";
        } catch (Exception e) {
            return "redirect:/teacher/categories?error=学分类别状态更新失败：" + e.getMessage();
        }
    }
}