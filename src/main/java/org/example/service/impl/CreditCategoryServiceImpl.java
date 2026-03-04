package org.example.service.impl;

import org.example.entity.CreditCategory;
import org.example.mapper.CreditCategoryMapper;
import org.example.service.CreditCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditCategoryServiceImpl implements CreditCategoryService {
    private final CreditCategoryMapper creditCategoryMapper;

    public CreditCategoryServiceImpl(CreditCategoryMapper creditCategoryMapper) {
        this.creditCategoryMapper = creditCategoryMapper;
    }

    @Override
    public List<CreditCategory> getAllCategories() {
        try {
            System.out.println("=== CreditCategoryService.getAllCategories ===");
            List<CreditCategory> categories = creditCategoryMapper.selectAll();
            System.out.println("获取到的学分类别数量: " + categories.size());
            for (CreditCategory category : categories) {
                System.out.println("  - 类别ID: " + category.getCategoryId() + ", 名称: " + category.getCategoryName() + ", 学分值: " + category.getCreditValue());
            }
            return categories;
        } catch (Exception e) {
            System.out.println("=== CreditCategoryService.getAllCategories 异常 ===");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public CreditCategory getCategoryById(String categoryId) {
        try {
            return creditCategoryMapper.selectById(categoryId);
        } catch (Exception e) {
            System.out.println("=== CreditCategoryService.getCategoryById 异常 ===");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCategory(CreditCategory category) {
        try {
            System.out.println("=== CreditCategoryService.addCategory ===");
            System.out.println("分类名称: " + category.getCategoryName());
            creditCategoryMapper.insert(category);
            System.out.println("分类添加成功！");
        } catch (Exception e) {
            System.out.println("=== CreditCategoryService.addCategory 异常 ===");
            e.printStackTrace();
        }
    }
    @Override
    public void updateCategory(CreditCategory category) {
        try {
            System.out.println("=== CreditCategoryService.updateCategory ===");
            System.out.println("分类ID: " + category.getCategoryId());
            creditCategoryMapper.update(category);
            System.out.println("分类更新成功！");
        } catch (Exception e) {
            System.out.println("=== CreditCategoryService.updateCategory 异常 ===");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(String categoryId) {
        try {
            System.out.println("=== CreditCategoryService.deleteCategory ===");
            System.out.println("分类ID: " + categoryId);
            creditCategoryMapper.delete(categoryId);
            System.out.println("分类删除成功！");
        } catch (Exception e) {
            System.out.println("=== CreditCategoryService.deleteCategory 异常 ===");
            e.printStackTrace();
        }
    }
}