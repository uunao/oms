package com.nh.oms.model.oms;

import java.util.Date;

/**
 * @Auther: Tsui
 * @Date: 2018-11-22
 * @Description:
 */
public class OmsPupuDeliveryExt extends OmsPupuDelivery {

    /**
     * 订单行号
     */
    private Integer indexSort;
    /**
     * 检测单号
     */
    private String sampleno;

    /**
     * 盒子的失效时间
     */
    private Date deadTime;

    /**
     * 快递单号
     *
     * @return
     */
    private String boxDeliveryNo;

    /**
     * 类型 1、采样盒、2、检测服务、3、噗噗管、4、套餐、5、其他
     *
     */
    private String objectType;

    /**
     * 盒子生产日期
     */
    private Date manufactureTime;

    /**
     * 渠道编码
     * @return
     */
    private String channelCode;

    /**
     * 批次号
     * @return
     */
    private String batchNo;

    /**
     * K3行号
     */
    private String  rowNum;

    /**
     * 出货仓位
     */
    private String positions;

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Date getManufactureTime() {
        return manufactureTime;
    }

    public void setManufactureTime(Date manufactureTime) {
        this.manufactureTime = manufactureTime;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getBoxDeliveryNo() {
        return boxDeliveryNo;
    }

    public void setBoxDeliveryNo(String boxDeliveryNo) {
        this.boxDeliveryNo = boxDeliveryNo;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public Integer getIndexSort() {
        return indexSort;
    }

    public void setIndexSort(Integer indexSort) {
        this.indexSort = indexSort;
    }

    public String getSampleno() {
        return sampleno;
    }

    public void setSampleno(String sampleno) {
        this.sampleno = sampleno;
    }
}
