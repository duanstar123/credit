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
    private String reviewerId;
    private Date reviewDate;
    private String reviewNote;
    private String screenshotUrl;
    private String certificateUrl;
    private String sourceCodeUrl;

    // 构造方法、getter和setter
    public CreditApplication() {}

    public CreditApplication(String applicationId, String studentId, String categoryId, String applicationReason,
                             Date applyDate, String status, String reviewerId, Date reviewDate,
                             String reviewNote, String screenshotUrl, String certificateUrl, String sourceCodeUrl) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.categoryId = categoryId;
        this.applicationReason = applicationReason;
        this.applyDate = applyDate;
        this.status = status;
        this.reviewerId = reviewerId;
        this.reviewDate = reviewDate;
        this.reviewNote = reviewNote;
        this.screenshotUrl = screenshotUrl;
        this.certificateUrl = certificateUrl;
        this.sourceCodeUrl = sourceCodeUrl;
    }

    // getter和setter方法
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
    public String getReviewerId() { return reviewerId; }
    public void setReviewerId(String reviewerId) { this.reviewerId = reviewerId; }
    public Date getReviewDate() { return reviewDate; }
    public void setReviewDate(Date reviewDate) { this.reviewDate = reviewDate; }
    public String getReviewNote() { return reviewNote; }
    public void setReviewNote(String reviewNote) { this.reviewNote = reviewNote; }
    public String getScreenshotUrl() { return screenshotUrl; }
    public void setScreenshotUrl(String screenshotUrl) { this.screenshotUrl = screenshotUrl; }
    public String getCertificateUrl() { return certificateUrl; }
    public void setCertificateUrl(String certificateUrl) { this.certificateUrl = certificateUrl; }
    public String getSourceCodeUrl() { return sourceCodeUrl; }
    public void setSourceCodeUrl(String sourceCodeUrl) { this.sourceCodeUrl = sourceCodeUrl; }
}