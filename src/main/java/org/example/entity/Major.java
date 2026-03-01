package org.example.entity;

import java.io.Serializable;

public class Major implements Serializable {
    private String majorId;
    private String majorName;
    private String collegeId;
    private String remark;

    // 构造方法、getter和setter
    public Major() {}

    public Major(String majorId, String majorName, String collegeId, String remark) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.collegeId = collegeId;
        this.remark = remark;
    }

    // getter和setter方法
    public String getMajorId() { return majorId; }
    public void setMajorId(String majorId) { this.majorId = majorId; }
    public String getMajorName() { return majorName; }
    public void setMajorName(String majorName) { this.majorName = majorName; }
    public String getCollegeId() { return collegeId; }
    public void setCollegeId(String collegeId) { this.collegeId = collegeId; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}