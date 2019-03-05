package com.nh.oms.model.oms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author will
 * @date 2018/11/06
 */
public class OmsOrderDetail implements Serializable {

    private Long iid;

    private String orderId;

    private String orderNo;

    private String sampleno;

    private Integer indexSort;

    private String componentCode;

    private String componentName;

    private BigDecimal componentPrice;

    private Integer rowState;

    private String createTime;

    private String boxDeliveryNo;

    private String backDeliveryNo;

    private String reportDeliveryNo;

    private Date sendBoxTime;

    private Date backTime;

    private Date reportTime;

    private String backQuality;

    private Date saleOutTime;

    private Double quantity;

    private Date deadTime;

    private String unqualifiedCause;

    private String objectType;

    private String batchNo;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * 生产日期
     */
    private Date manufactureTime;

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


    public String getUnqualifiedCause() {
        return unqualifiedCause;
    }

    public void setUnqualifiedCause(String unqualifiedCause) {
        this.unqualifiedCause = unqualifiedCause;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getSaleOutTime() {
        return saleOutTime;
    }

    public void setSaleOutTime(Date saleOutTime) {
        this.saleOutTime = saleOutTime;
    }

    public String getBackQuality() {
        return backQuality;
    }

    public void setBackQuality(String backQuality) {
        this.backQuality = backQuality;
    }

    /**
     * 实验室信息，用于接收ERP调用接口
     */
    private String laboratory;

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    private static final long serialVersionUID = 1L;

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Integer getIndexSort() {
        return indexSort;
    }

    public void setIndexSort(Integer indexSort) {
        this.indexSort = indexSort;
    }

    public String getComponentCode() {
        return componentCode;
    }

    public void setComponentCode(String componentCode) {
        this.componentCode = componentCode;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public BigDecimal getComponentPrice() {
        return componentPrice;
    }

    public void setComponentPrice(BigDecimal componentPrice) {
        this.componentPrice = componentPrice;
    }

    public Integer getRowState() {
        return rowState;
    }

    public void setRowState(Integer rowState) {
        this.rowState = rowState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBoxDeliveryNo() {
        return boxDeliveryNo;
    }

    public void setBoxDeliveryNo(String boxDeliveryNo) {
        this.boxDeliveryNo = boxDeliveryNo;
    }

    public String getBackDeliveryNo() {
        return backDeliveryNo;
    }

    public void setBackDeliveryNo(String backDeliveryNo) {
        this.backDeliveryNo = backDeliveryNo;
    }

    public String getReportDeliveryNo() {
        return reportDeliveryNo;
    }

    public void setReportDeliveryNo(String reportDeliveryNo) {
        this.reportDeliveryNo = reportDeliveryNo;
    }

    public Date getSendBoxTime() {
        return sendBoxTime;
    }

    public void setSendBoxTime(Date sendBoxTime) {
        this.sendBoxTime = sendBoxTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
}