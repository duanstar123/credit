/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.mapper;

import org.example.entity.CreditCategory;
import java.util.List;

/**
 * 学分分类数据访问接口
 * @author 段璎芮
 * @version 1.0
 */
public interface CreditCategoryMapper {
    List<CreditCategory> selectAll();
    int insert(CreditCategory creditCategory);
    int update(CreditCategory creditCategory);
    int delete(String categoryId);
    CreditCategory selectById(String categoryId);
}