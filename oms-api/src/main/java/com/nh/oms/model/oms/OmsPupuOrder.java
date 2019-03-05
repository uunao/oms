package com.nh.oms.model.oms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OmsPupuOrder implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.iid
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private Long iid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.id
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.order_no
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.master_code
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String masterCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.order_qty
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private Integer orderQty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.delivery_qty
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private Integer deliveryQty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.saleout_qty
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private BigDecimal saleoutQty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.order_amt
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private BigDecimal orderAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.pay_type
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String payType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.purchaser
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String purchaser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.purchaser_phone
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String purchaserPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.department
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String department;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.channel_code
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String channelCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.salesman_id
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private Long salesmanId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.channel_type
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String channelType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.delivery_type
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String deliveryType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.area_code
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String areaCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.receiver_name
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String receiverName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.receiver_address
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String receiverAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.receiver_phone
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String receiverPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.order_sms
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String orderSms;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.box_sms
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String boxSms;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.order_state
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String orderState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.order_maker
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String orderMaker;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.coupon_id
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String couponId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.out_trade_no
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String outTradeNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.tax_rate
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private Integer taxRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.create_time
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.term_enable
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String termEnable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.term_date
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String termDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.synch_state
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String synchState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_order.remark
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private String remark;

    private Integer termDays;

    private String productUnit;

    private String isBill;

    private String billType;

    public String getIsBill() {
        return isBill;
    }

    public void setIsBill(String isBill) {
        this.isBill = isBill;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    public Integer getTermDays() {
        return termDays;
    }

    public void setTermDays(Integer termDays) {
        this.termDays = termDays;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oms_pupu_order
     *
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.iid
     *
     * @return the value of oms_pupu_order.iid
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public Long getIid() {
        return iid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.iid
     *
     * @param iid the value for oms_pupu_order.iid
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setIid(Long iid) {
        this.iid = iid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.id
     *
     * @return the value of oms_pupu_order.id
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.id
     *
     * @param id the value for oms_pupu_order.id
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.order_no
     *
     * @return the value of oms_pupu_order.order_no
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.order_no
     *
     * @param orderNo the value for oms_pupu_order.order_no
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.master_code
     *
     * @return the value of oms_pupu_order.master_code
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getMasterCode() {
        return masterCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.master_code
     *
     * @param masterCode the value for oms_pupu_order.master_code
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.order_qty
     *
     * @return the value of oms_pupu_order.order_qty
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public Integer getOrderQty() {
        return orderQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.order_qty
     *
     * @param orderQty the value for oms_pupu_order.order_qty
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.delivery_qty
     *
     * @return the value of oms_pupu_order.delivery_qty
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public Integer getDeliveryQty() {
        return deliveryQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.delivery_qty
     *
     * @param deliveryQty the value for oms_pupu_order.delivery_qty
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setDeliveryQty(Integer deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.saleout_qty
     *
     * @return the value of oms_pupu_order.saleout_qty
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public BigDecimal getSaleoutQty() {
        return saleoutQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.saleout_qty
     *
     * @param saleoutQty the value for oms_pupu_order.saleout_qty
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setSaleoutQty(BigDecimal saleoutQty) {
        this.saleoutQty = saleoutQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.order_amt
     *
     * @return the value of oms_pupu_order.order_amt
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.order_amt
     *
     * @param orderAmt the value for oms_pupu_order.order_amt
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.pay_type
     *
     * @return the value of oms_pupu_order.pay_type
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.pay_type
     *
     * @param payType the value for oms_pupu_order.pay_type
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.purchaser
     *
     * @return the value of oms_pupu_order.purchaser
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getPurchaser() {
        return purchaser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.purchaser
     *
     * @param purchaser the value for oms_pupu_order.purchaser
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.purchaser_phone
     *
     * @return the value of oms_pupu_order.purchaser_phone
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.purchaser_phone
     *
     * @param purchaserPhone the value for oms_pupu_order.purchaser_phone
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setPurchaserPhone(String purchaserPhone) {
        this.purchaserPhone = purchaserPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.department
     *
     * @return the value of oms_pupu_order.department
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.department
     *
     * @param department the value for oms_pupu_order.department
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.channel_code
     *
     * @return the value of oms_pupu_order.channel_code
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.channel_code
     *
     * @param channelCode the value for oms_pupu_order.channel_code
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.salesman_id
     *
     * @return the value of oms_pupu_order.salesman_id
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public Long getSalesmanId() {
        return salesmanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.salesman_id
     *
     * @param salesmanId the value for oms_pupu_order.salesman_id
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.channel_type
     *
     * @return the value of oms_pupu_order.channel_type
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.channel_type
     *
     * @param channelType the value for oms_pupu_order.channel_type
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.delivery_type
     *
     * @return the value of oms_pupu_order.delivery_type
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getDeliveryType() {
        return deliveryType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.delivery_type
     *
     * @param deliveryType the value for oms_pupu_order.delivery_type
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.area_code
     *
     * @return the value of oms_pupu_order.area_code
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.area_code
     *
     * @param areaCode the value for oms_pupu_order.area_code
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.receiver_name
     *
     * @return the value of oms_pupu_order.receiver_name
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.receiver_name
     *
     * @param receiverName the value for oms_pupu_order.receiver_name
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.receiver_address
     *
     * @return the value of oms_pupu_order.receiver_address
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.receiver_address
     *
     * @param receiverAddress the value for oms_pupu_order.receiver_address
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.receiver_phone
     *
     * @return the value of oms_pupu_order.receiver_phone
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.receiver_phone
     *
     * @param receiverPhone the value for oms_pupu_order.receiver_phone
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.order_sms
     *
     * @return the value of oms_pupu_order.order_sms
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getOrderSms() {
        return orderSms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.order_sms
     *
     * @param orderSms the value for oms_pupu_order.order_sms
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setOrderSms(String orderSms) {
        this.orderSms = orderSms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.box_sms
     *
     * @return the value of oms_pupu_order.box_sms
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getBoxSms() {
        return boxSms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.box_sms
     *
     * @param boxSms the value for oms_pupu_order.box_sms
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setBoxSms(String boxSms) {
        this.boxSms = boxSms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.order_state
     *
     * @return the value of oms_pupu_order.order_state
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.order_state
     *
     * @param orderState the value for oms_pupu_order.order_state
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.order_maker
     *
     * @return the value of oms_pupu_order.order_maker
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getOrderMaker() {
        return orderMaker;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.order_maker
     *
     * @param orderMaker the value for oms_pupu_order.order_maker
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setOrderMaker(String orderMaker) {
        this.orderMaker = orderMaker;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.coupon_id
     *
     * @return the value of oms_pupu_order.coupon_id
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getCouponId() {
        return couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.coupon_id
     *
     * @param couponId the value for oms_pupu_order.coupon_id
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.out_trade_no
     *
     * @return the value of oms_pupu_order.out_trade_no
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.out_trade_no
     *
     * @param outTradeNo the value for oms_pupu_order.out_trade_no
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.tax_rate
     *
     * @return the value of oms_pupu_order.tax_rate
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public Integer getTaxRate() {
        return taxRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.tax_rate
     *
     * @param taxRate the value for oms_pupu_order.tax_rate
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.create_time
     *
     * @return the value of oms_pupu_order.create_time
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.create_time
     *
     * @param createTime the value for oms_pupu_order.create_time
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.term_enable
     *
     * @return the value of oms_pupu_order.term_enable
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getTermEnable() {
        return termEnable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.term_enable
     *
     * @param termEnable the value for oms_pupu_order.term_enable
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setTermEnable(String termEnable) {
        this.termEnable = termEnable;
    }



    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.synch_state
     *
     * @return the value of oms_pupu_order.synch_state
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getSynchState() {
        return synchState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.synch_state
     *
     * @param synchState the value for oms_pupu_order.synch_state
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setSynchState(String synchState) {
        this.synchState = synchState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_order.remark
     *
     * @return the value of oms_pupu_order.remark
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_order.remark
     *
     * @param remark the value for oms_pupu_order.remark
     * @mbggenerated Tue Nov 20 19:18:42 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}