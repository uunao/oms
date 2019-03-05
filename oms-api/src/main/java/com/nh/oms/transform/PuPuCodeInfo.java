package com.nh.oms.transform;

import java.util.List;

/**
 * Created by ccwang on 2018/11/22.
 */
public class PuPuCodeInfo {

    public String k3BillNo;

    public String k3BillRowNo;

    public String orderNo;

    public String rowNo;

    public String k3BillType;

    public String batchNo;

    public List<PuPuCode> puPuList;


    public String getK3BillNo() {
        return k3BillNo;
    }

    public void setK3BillNo(String k3BillNo) {
        this.k3BillNo = k3BillNo;
    }

    public String getK3BillRowNo() {
        return k3BillRowNo;
    }

    public void setK3BillRowNo(String k3BillRowNo) {
        this.k3BillRowNo = k3BillRowNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public String getK3BillType() {
        return k3BillType;
    }

    public void setK3BillType(String k3BillType) {
        this.k3BillType = k3BillType;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public List<PuPuCode> getPuPuList() {
        return puPuList;
    }

    public void setPuPuList(List<PuPuCode> puPuList) {
        this.puPuList = puPuList;
    }
}
