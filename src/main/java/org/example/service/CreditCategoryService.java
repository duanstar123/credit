package org.example.service;

import org.example.entity.CreditCategory;

import java.util.List;

public interface CreditCategoryService {
    List<CreditCategory> getAllCategories();
    CreditCategory getCategoryById(String categoryId);
    void addCategory(CreditCategory category);
    void updateCategory(CreditCategory category);
    void deleteCategory(String categoryId);
}