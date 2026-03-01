package org.example.service;

import org.example.entity.CreditCategory;

import java.util.List;

public interface CategoryService {
    // 添加学分类别
    void addCategory(CreditCategory category);

    // 更新学分类别
    void updateCategory(CreditCategory category);

    // 更新学分类别状态
    void updateCategoryStatus(String categoryId, int status);

    // 根据类别ID查询
    CreditCategory getCategoryById(String categoryId);

    // 查询所有学分类别
    List<CreditCategory> getAllCategories();

    // 查询启用的学分类别
    List<CreditCategory> getActiveCategories();
}