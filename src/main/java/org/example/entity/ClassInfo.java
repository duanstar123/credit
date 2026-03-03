package org.example.entity;

import java.io.Serializable;

public class ClassInfo implements Serializable {
    private String classId;
    private String className;
    private String grade;
    private String majorId;
    private String collegeId;
    private String remark;

    public ClassInfo() {}

    public ClassInfo(String classId, String className, String grade, String majorId, String collegeId, String remark) {
        this.classId = classId;
        this.className = className;
        this.grade = grade;
        this.majorId = majorId;
        this.collegeId = collegeId;
        this.remark = remark;
    }

    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getMajorId() { return majorId; }
    public void setMajorId(String majorId) { this.majorId = majorId; }
    public String getCollegeId() { return collegeId; }
    public void setCollegeId(String collegeId) { this.collegeId = collegeId; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}