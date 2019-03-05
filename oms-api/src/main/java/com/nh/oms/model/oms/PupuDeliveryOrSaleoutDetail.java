package com.nh.oms.model.oms;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 发货/出库同步明细表 oms_pupu_delivery_or_saleout_detail
 * 
 * @author will
 * @date 2018-12-25
 */
public class PupuDeliveryOrSaleoutDetail
{
	/** 自增ID */
	private Long iid;
	/** 噗噗管订单号 */
	private String orderNo;
	/** 订单行号 */
	private Integer indexSort;
	/** 噗噗码 */
	private String sampleNo;
	/** 失效时间 */
	private Date deadTime;
	/** 物料类型 */
	private String objectType;
	/** 发货/出库单号 */
	private String number;
	/** 发货/出库数量 */
	private BigDecimal quantity;
	/** 发货/出库时间 */
	private Date deliveryTime;
	/** 生产日期 */
	private Date manufactureTime;
	/** 渠道编码 */
	private String channelCode;
	/** 批次号 */
	private String batchNo;
	/** K3行号 */
	private String rowNum;
	/** K3仓储位置 */
	private String postitions;
	/** 类型（1-发货，2-出库） */
	private Integer type;
	/** 创建时间 */
	private Date createTime;
	/** 快递单号 */
	private String deliveryNo;

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public void setIid(Long iid)
	{
		this.iid = iid;
	}

	public Long getIid() 
	{
		return iid;
	}
	public void setOrderNo(String orderNo) 
	{
		this.orderNo = orderNo;
	}

	public String getOrderNo() 
	{
		return orderNo;
	}
	public void setIndexSort(Integer indexSort) 
	{
		this.indexSort = indexSort;
	}

	public Integer getIndexSort() 
	{
		return indexSort;
	}
	public void setSampleNo(String sampleNo) 
	{
		this.sampleNo = sampleNo;
	}

	public String getSampleNo() 
	{
		return sampleNo;
	}
	public void setDeadTime(Date deadTime) 
	{
		this.deadTime = deadTime;
	}

	public Date getDeadTime() 
	{
		return deadTime;
	}
	public void setObjectType(String objectType) 
	{
		this.objectType = objectType;
	}

	public String getObjectType() 
	{
		return objectType;
	}
	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getNumber() 
	{
		return number;
	}
	public void setQuantity(BigDecimal quantity) 
	{
		this.quantity = quantity;
	}

	public BigDecimal getQuantity() 
	{
		return quantity;
	}
	public void setDeliveryTime(Date deliveryTime) 
	{
		this.deliveryTime = deliveryTime;
	}

	public Date getDeliveryTime() 
	{
		return deliveryTime;
	}
	public void setManufactureTime(Date manufactureTime) 
	{
		this.manufactureTime = manufactureTime;
	}

	public Date getManufactureTime() 
	{
		return manufactureTime;
	}
	public void setChannelCode(String channelCode) 
	{
		this.channelCode = channelCode;
	}

	public String getChannelCode() 
	{
		return channelCode;
	}
	public void setBatchNo(String batchNo) 
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo() 
	{
		return batchNo;
	}
	public void setRowNum(String rowNum) 
	{
		this.rowNum = rowNum;
	}

	public String getRowNum() 
	{
		return rowNum;
	}
	public void setPostitions(String postitions) 
	{
		this.postitions = postitions;
	}

	public String getPostitions() 
	{
		return postitions;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("iid", getIid())
            .append("orderNo", getOrderNo())
            .append("indexSort", getIndexSort())
            .append("sampleNo", getSampleNo())
            .append("deadTime", getDeadTime())
            .append("objectType", getObjectType())
            .append("number", getNumber())
            .append("quantity", getQuantity())
            .append("deliveryTime", getDeliveryTime())
            .append("manufactureTime", getManufactureTime())
            .append("channelCode", getChannelCode())
            .append("batchNo", getBatchNo())
            .append("rowNum", getRowNum())
            .append("postitions", getPostitions())
            .append("type", getType())
            .append("createTime", getCreateTime())
            .toString();
    }
}
