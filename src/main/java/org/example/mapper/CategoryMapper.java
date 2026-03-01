package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.CreditCategory;


import java.util.List;

@Mapper
public interface CategoryMapper {
    // 查询所有学分类别（包括禁用的）
    List<CreditCategory> selectAll();

    // 查询启用的学分类别
    List<CreditCategory> selectActive();

    // 根据类别ID查询
    CreditCategory selectByCategoryId(String categoryId);

    // 插入学分类别
    void insert(CreditCategory category);

    // 更新学分类别
    void update(CreditCategory category);

    // 更新学分类别状态
    void updateStatus(CreditCategory category);
}