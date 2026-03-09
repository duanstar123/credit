/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.entity;

import java.io.Serializable;

/**
 * 班级数据访问对象
 * @author 段璎芮
 * @version 1.0
 */
public class Major implements Serializable {
    private String majorId;
    private String majorName;
    private String collegeId;
    private String remark;

    public Major() {}

    public Major(String majorId, String majorName, String collegeId, String remark) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.collegeId = collegeId;
        this.remark = remark;
    }

    public String getMajorId() { return majorId; }
    public void setMajorId(String majorId) { this.majorId = majorId; }
    public String getMajorName() { return majorName; }
    public void setMajorName(String majorName) { this.majorName = majorName; }
    public String getCollegeId() { return collegeId; }
    public void setCollegeId(String collegeId) { this.collegeId = collegeId; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}