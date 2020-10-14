package com.mzx.springbootweb.dto;

import java.util.Date;

public class OverTimeDto {
    private Date date1;
    private Date date2;
    private String username;
    private Double times;
    private String productName;
    private String projectName;
    private String overTimeContext;
    private Integer overTimeType;

    public Integer getOverTimeType() {
        return overTimeType;
    }

    public void setOverTimeType(Integer overTimeType) {
        this.overTimeType = overTimeType;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getTimes() {
        return times;
    }

    public void setTimes(Double times) {
        this.times = times;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOverTimeContext() {
        return overTimeContext;
    }

    public void setOverTimeContext(String overTimeContext) {
        this.overTimeContext = overTimeContext;
    }
}
