package com.nh.oms.model.oms;

import java.util.Date;

public class OmsOrderLog {

    public OmsOrderLog(){
        super();
    }

    public OmsOrderLog(String subOrderno) {
        super();
        this.userId = "9";
        this.deptName = "系统";
        this.actionDate = new Date();
        this.subOrderno = subOrderno;
    }

    private String orderParentNo;

    private String subOrderno;

    private String userId;

    private String deptName;

    private String operatorType;

    private Date actionDate;

    private String remark;

    public String getOrderParentNo() {
        return orderParentNo;
    }

    public void setOrderParentNo(String orderParentNo) {
        this.orderParentNo = orderParentNo;
    }

    public String getSubOrderno() {
        return subOrderno;
    }

    public void setSubOrderno(String subOrderno) {
        this.subOrderno = subOrderno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
