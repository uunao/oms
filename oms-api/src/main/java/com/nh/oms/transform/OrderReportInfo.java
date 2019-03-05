package com.nh.oms.transform;

import java.util.Date;

/**
 * Created by ccWang on 2018/12/3.
 */
public class OrderReportInfo {

    private String id;

    private String orderNo;

    private String sampleno;

    private String detectedPerson;

    private String sex;

    private String birthday;

    private String certificateType;

    private String certificateNo;

    private String detectedPhone;

    private String policyName;

    private String measureTime;

    private String testResult;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSampleno() {
        return sampleno;
    }

    public void setSampleno(String sampleno) {
        this.sampleno = sampleno;
    }

    public String getDetectedPerson() {
        return detectedPerson;
    }

    public void setDetectedPerson(String detectedPerson) {
        this.detectedPerson = detectedPerson;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getDetectedPhone() {
        return detectedPhone;
    }

    public void setDetectedPhone(String detectedPhone) {
        this.detectedPhone = detectedPhone;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(String measureTime) {
        this.measureTime = measureTime;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
}
