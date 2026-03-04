package org.example.mapper;

import org.example.entity.CreditCategory;
import java.util.List;

public interface CreditCategoryMapper {
    List<CreditCategory> selectAll();
    int insert(CreditCategory creditCategory);
    int update(CreditCategory creditCategory);
    int delete(String categoryId);
    CreditCategory selectById(String categoryId);
}