package com.mzx.springbootweb.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class Overtime {
    private String id;
    private String accountId;
    private String content;
    private Date fillinDate;
    private Double hours;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date overTimeEndDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date overTimeStartDate;
    private String productId;
    private String projectId;
    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getFillinDate() {
        return fillinDate;
    }

    public void setFillinDate(Date fillinDate) {
        this.fillinDate = fillinDate;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Date getOverTimeEndDate() {
        return overTimeEndDate;
    }

    public void setOverTimeEndDate(Date overTimeEndDate) {
        this.overTimeEndDate = overTimeEndDate;
    }

    public Date getOverTimeStartDate() {
        return overTimeStartDate;
    }

    public void setOverTimeStartDate(Date overTimeStartDate) {
        this.overTimeStartDate = overTimeStartDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
