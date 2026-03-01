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
            return creditCategoryMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public CreditCategory getCategoryById(String categoryId) {
        try {
            return creditCategoryMapper.selectById(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCategory(CreditCategory category) {
        try {
            creditCategoryMapper.insert(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(CreditCategory category) {
        try {
            creditCategoryMapper.update(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(String categoryId) {
        try {
            creditCategoryMapper.delete(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}