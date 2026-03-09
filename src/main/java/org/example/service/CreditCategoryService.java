/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.service;

import org.example.entity.CreditCategory;

import java.util.List;

/**
 * 学分分类数据访问服务接口
 * @author 段璎芮
 * @version 1.0
 */
public interface CreditCategoryService {
    List<CreditCategory> getAllCategories();
    CreditCategory getCategoryById(String categoryId);
    void addCategory(CreditCategory category);
    void updateCategory(CreditCategory category);
    void deleteCategory(String categoryId);
}