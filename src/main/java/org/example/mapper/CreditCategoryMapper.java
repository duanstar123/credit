package org.example.mapper;

import org.example.entity.CreditCategory;

import java.util.List;

public interface CreditCategoryMapper {
    List<CreditCategory> selectAll();
    CreditCategory selectById(String categoryId);
    void insert(CreditCategory category);
    void update(CreditCategory category);
    void delete(String categoryId);
}