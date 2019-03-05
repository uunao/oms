package com.nh.oms.model.oms;

import java.io.Serializable;

public class OmsOrderSyncException implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_order_sync_exception.iid
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    private Integer iid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_order_sync_exception.order_parent_no
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    private String orderParentNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_order_sync_exception.order_no
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    private String orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_order_sync_exception.exception_msg
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    private String exceptionMsg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_order_sync_exception.create_time
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oms_order_sync_exception
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_order_sync_exception.iid
     *
     * @return the value of oms_order_sync_exception.iid
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public Integer getIid() {
        return iid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_order_sync_exception.iid
     *
     * @param iid the value for oms_order_sync_exception.iid
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public void setIid(Integer iid) {
        this.iid = iid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_order_sync_exception.order_parent_no
     *
     * @return the value of oms_order_sync_exception.order_parent_no
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public String getOrderParentNo() {
        return orderParentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_order_sync_exception.order_parent_no
     *
     * @param orderParentNo the value for oms_order_sync_exception.order_parent_no
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public void setOrderParentNo(String orderParentNo) {
        this.orderParentNo = orderParentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_order_sync_exception.order_no
     *
     * @return the value of oms_order_sync_exception.order_no
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_order_sync_exception.order_no
     *
     * @param orderNo the value for oms_order_sync_exception.order_no
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_order_sync_exception.exception_msg
     *
     * @return the value of oms_order_sync_exception.exception_msg
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public String getExceptionMsg() {
        return exceptionMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_order_sync_exception.exception_msg
     *
     * @param exceptionMsg the value for oms_order_sync_exception.exception_msg
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_order_sync_exception.create_time
     *
     * @return the value of oms_order_sync_exception.create_time
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_order_sync_exception.create_time
     *
     * @param createTime the value for oms_order_sync_exception.create_time
     *
     * @mbggenerated Wed Nov 14 20:53:09 CST 2018
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}