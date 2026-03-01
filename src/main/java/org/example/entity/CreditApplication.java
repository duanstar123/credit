package org.example.entity;

import java.io.Serializable;
import java.util.Date;

public class CreditApplication implements Serializable {
    private String applicationId;
    private String studentId;
    private String categoryId;
    private String applicationReason;
    private Date applyDate;
    private String status;
    private Double creditValue;
    private String reviewerId; // 添加 reviewerId 属性
    private Date reviewDate; // 添加 reviewDate 属性
    private String reviewNote; // 添加 reviewNote 属性
    private String screenshotUrl;
    private String certificateUrl;
    private String sourceCodeUrl;
    private String studentName; // 添加 studentName 属性
    // getter 和 setter 方法
    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public String getApplicationReason() { return applicationReason; }
    public void setApplicationReason(String applicationReason) { this.applicationReason = applicationReason; }
    public Date getApplyDate() { return applyDate; }
    public void setApplyDate(Date applyDate) { this.applyDate = applyDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getCreditValue() { return creditValue; }
    public void setCreditValue(Double creditValue) { this.creditValue = creditValue; }
    public String getReviewerId() { return reviewerId; } // 添加 getReviewerId 方法
    public void setReviewerId(String reviewerId) { this.reviewerId = reviewerId; } // 添加 setReviewerId 方法
    public Date getReviewDate() { return reviewDate; } // 添加 getReviewDate 方法
    public void setReviewDate(Date reviewDate) { this.reviewDate = reviewDate; } // 添加 setReviewDate 方法
    public String getReviewNote() { return reviewNote; } // 添加 getReviewNote 方法
    public void setReviewNote(String reviewNote) { this.reviewNote = reviewNote; } // 添加 setReviewNote 方法
    public String getScreenshotUrl() { return screenshotUrl; }
    public void setScreenshotUrl(String screenshotUrl) { this.screenshotUrl = screenshotUrl; }
    public String getCertificateUrl() { return certificateUrl; }
    public void setCertificateUrl(String certificateUrl) { this.certificateUrl = certificateUrl; }
    public String getSourceCodeUrl() { return sourceCodeUrl; }
    public void setSourceCodeUrl(String sourceCodeUrl) { this.sourceCodeUrl = sourceCodeUrl; }
    public String getStudentName() { return studentName; } // 添加 getStudentName 方法
    public void setStudentName(String studentName) { this.studentName = studentName; } // 添加 setStudentName 方法
}