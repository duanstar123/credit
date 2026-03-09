/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.entity;

import java.io.Serializable;

/**
 * 教师实体类
 * @author 段璎芮
 * @version 1.0
 */
public class Teacher implements Serializable {
    private String teacherId;
    private String password;
    private String realName;
    private String collegeId;

    public Teacher() {}

    public Teacher(String teacherId, String password, String realName, String collegeId) {
        this.teacherId = teacherId;
        this.password = password;
        this.realName = realName;
        this.collegeId = collegeId;
    }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public String getCollegeId() { return collegeId; }
    public void setCollegeId(String collegeId) { this.collegeId = collegeId; }
}