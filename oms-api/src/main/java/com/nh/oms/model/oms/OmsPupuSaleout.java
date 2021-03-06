package com.nh.oms.model.oms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OmsPupuSaleout implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_saleout.iid
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    private Long iid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_saleout.order_no
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    private String orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_saleout.delivery_no
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    private String deliveryNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_saleout.saleout_no
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    private String saleoutNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_saleout.saleout_qty
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    private BigDecimal saleoutQty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_saleout.saleout_time
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    private Date saleoutTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_pupu_saleout.create_time
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    private Date createTime;

    private String expressNo;

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oms_pupu_saleout
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_saleout.iid
     *
     * @return the value of oms_pupu_saleout.iid
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public Long getIid() {
        return iid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_saleout.iid
     *
     * @param iid the value for oms_pupu_saleout.iid
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public void setIid(Long iid) {
        this.iid = iid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_saleout.order_no
     *
     * @return the value of oms_pupu_saleout.order_no
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_saleout.order_no
     *
     * @param orderNo the value for oms_pupu_saleout.order_no
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_saleout.delivery_no
     *
     * @return the value of oms_pupu_saleout.delivery_no
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public String getDeliveryNo() {
        return deliveryNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_saleout.delivery_no
     *
     * @param deliveryNo the value for oms_pupu_saleout.delivery_no
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_saleout.saleout_no
     *
     * @return the value of oms_pupu_saleout.saleout_no
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public String getSaleoutNo() {
        return saleoutNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_saleout.saleout_no
     *
     * @param saleoutNo the value for oms_pupu_saleout.saleout_no
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public void setSaleoutNo(String saleoutNo) {
        this.saleoutNo = saleoutNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_saleout.saleout_qty
     *
     * @return the value of oms_pupu_saleout.saleout_qty
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public BigDecimal getSaleoutQty() {
        return saleoutQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_saleout.saleout_qty
     *
     * @param saleoutQty the value for oms_pupu_saleout.saleout_qty
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public void setSaleoutQty(BigDecimal saleoutQty) {
        this.saleoutQty = saleoutQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_saleout.saleout_time
     *
     * @return the value of oms_pupu_saleout.saleout_time
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public Date getSaleoutTime() {
        return saleoutTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_saleout.saleout_time
     *
     * @param saleoutTime the value for oms_pupu_saleout.saleout_time
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public void setSaleoutTime(Date saleoutTime) {
        this.saleoutTime = saleoutTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_pupu_saleout.create_time
     *
     * @return the value of oms_pupu_saleout.create_time
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_pupu_saleout.create_time
     *
     * @param createTime the value for oms_pupu_saleout.create_time
     *
     * @mbggenerated Tue Nov 20 19:19:52 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}