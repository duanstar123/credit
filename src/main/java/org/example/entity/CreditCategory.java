package org.example.entity;

import java.io.Serializable;
import java.util.Date;

public class CreditCategory implements Serializable {
    private String categoryId;
    private String categoryName;
    private double creditValue;
    private String remark;
    private Date createDate;
    private int status;
    private String description;

    public CreditCategory() {}

    public CreditCategory(String categoryId, String categoryName, double creditValue, String remark, Date createDate, int status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.creditValue = creditValue;
        this.remark = remark;
        this.createDate = createDate;
        this.status = status;
    }
    public CreditCategory(String categoryId, String categoryName, double creditValue, String remark, Date createDate, int status, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.creditValue = creditValue;
        this.remark = remark;
        this.createDate = createDate;
        this.status = status;
        this.description = description;
    }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public double getCreditValue() { return creditValue; }
    public void setCreditValue(double creditValue) { this.creditValue = creditValue; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}