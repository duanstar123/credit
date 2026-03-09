/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.mapper;

import org.example.entity.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 专业数据访问接口
 * @author 段璎芮
 * @version 1.0
 */
@Mapper
public interface MajorMapper {
    // 查询所有专业
    List<Major> selectAll();

    // 根据专业ID查询
    Major selectByMajorId(String majorId);

    // 根据院系ID查询专业
    List<Major> selectByCollegeId(String collegeId);

    // 插入专业
    void insert(Major major);

    // 更新专业信息
    void update(Major major);
}