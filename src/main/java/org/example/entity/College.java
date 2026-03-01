package org.example.entity;

import java.io.Serializable;

public class College implements Serializable {
    private String collegeId;
    private String collegeName;
    private String remark;

    // 构造方法、getter和setter
    public College() {}

    public College(String collegeId, String collegeName, String remark) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.remark = remark;
    }

    // getter和setter方法
    public String getCollegeId() { return collegeId; }
    public void setCollegeId(String collegeId) { this.collegeId = collegeId; }
    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}