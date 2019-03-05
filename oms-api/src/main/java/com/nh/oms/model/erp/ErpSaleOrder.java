package com.nh.oms.model.erp;

/**
 * @author Will
 * @date 2018/11/13
 */
public class ErpSaleOrder {

    /**
     * OMS订单号
     */
    private String orderNo;

    /**
     * 被检测人姓名
     */
    private String detectedPerson;

    /**
     * 订件类别(0-其它,1-身份证,2-护照)
     */
    private String certificateType;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 被检测人手机号
     */
    private String detectedPhone;

    /**
     * 收件人姓名
     */
    private String receiverName;

    /**
     * 收件人地址
     */
    private String receiverAddress;

    /**
     * 收件人手机
     */
    private String receiverPhone;

    /**
     * 收报告人姓名
     */
    private String reportReceiverName;

    /**
     * 收报告人手机
     */
    private String reportReceiverPhone;

    /**
     * 收报告人地址
     */
    private String reportReceiverAddress;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDetectedPerson() {
        return detectedPerson;
    }

    public void setDetectedPerson(String detectedPerson) {
        this.detectedPerson = detectedPerson;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getDetectedPhone() {
        return detectedPhone;
    }

    public void setDetectedPhone(String detectedPhone) {
        this.detectedPhone = detectedPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReportReceiverName() {
        return reportReceiverName;
    }

    public void setReportReceiverName(String reportReceiverName) {
        this.reportReceiverName = reportReceiverName;
    }

    public String getReportReceiverPhone() {
        return reportReceiverPhone;
    }

    public void setReportReceiverPhone(String reportReceiverPhone) {
        this.reportReceiverPhone = reportReceiverPhone;
    }

    public String getReportReceiverAddress() {
        return reportReceiverAddress;
    }

    public void setReportReceiverAddress(String reportReceiverAddress) {
        this.reportReceiverAddress = reportReceiverAddress;
    }
}
