/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.entity;

import java.io.Serializable;

/**
 * 学生实体类
 * @author 段璎芮
 * @version 1.0
 */
public class Student implements Serializable {
    private String studentId;
    private String name;
    private String gender;
    private String grade;
    private String classId;
    private String majorId;
    private String collegeId;
    private String password;
    private String majorName;
    private String className;

    public Student() {}

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public String getMajorId() { return majorId; }
    public void setMajorId(String majorId) { this.majorId = majorId; }
    public String getCollegeId() { return collegeId; }
    public void setCollegeId(String collegeId) { this.collegeId = collegeId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getMajorName() { return majorName; }
    public void setMajorName(String majorName) { this.majorName = majorName; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}