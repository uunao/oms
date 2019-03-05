package com.nh.oms.transform;

/**
 * Created by ccwang on 2018/11/6.
 */
public class SendPuPuOrderRequest {

    public String orderPuPuNo;

    public String businessNo;

    public String timeStamp;

    public String getOrderPuPuNo() {
        return orderPuPuNo;
    }

    public void setOrderPuPuNo(String orderPuPuNo) {
        this.orderPuPuNo = orderPuPuNo;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
