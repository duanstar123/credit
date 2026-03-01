package org.example.service.impl;

import org.example.entity.CreditCategory;
import org.example.mapper.CategoryMapper;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(CreditCategory category) {
        categoryMapper.insert(category);
    }

    @Override
    public void updateCategory(CreditCategory category) {
        categoryMapper.update(category);
    }

    @Override
    public void updateCategoryStatus(String categoryId, int status) {
        CreditCategory category = new CreditCategory();
        category.setCategoryId(categoryId);
        category.setStatus(status);
        categoryMapper.updateStatus(category);
    }

    @Override
    public CreditCategory getCategoryById(String categoryId) {
        return categoryMapper.selectByCategoryId(categoryId);
    }

    @Override
    public List<CreditCategory> getAllCategories() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<CreditCategory> getActiveCategories() {
        return categoryMapper.selectActive();
    }
}