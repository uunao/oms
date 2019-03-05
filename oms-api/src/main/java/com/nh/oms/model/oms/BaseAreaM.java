package com.nh.oms.model.oms;

/**
 * 地址区域表 oms_base_area
 * 
 * @author CCwang
 * @date 2018-11-16
 */
public class BaseAreaM
{
	/** 自增ID */
	private Integer iid;
	/** 区域ID(旧) */
	private String id;
	/** 区域类别 */
	private Integer areaType;
	/** 区域编码 */
	private String areaCode;
	/** 区域名称 */
	private String areaName;
	/** 状态 */
	private Integer status;
	/** 上级行政区域ID(旧) */
	private String parentid;
	/** 邮政编码 */
	private String zipCode;
	/** 拼音首字母简写 */
	private String alphaCode;

	public void setIid(Integer iid) 
	{
		this.iid = iid;
	}

	public Integer getIid() 
	{
		return iid;
	}
	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setAreaType(Integer areaType) 
	{
		this.areaType = areaType;
	}

	public Integer getAreaType() 
	{
		return areaType;
	}
	public void setAreaCode(String areaCode) 
	{
		this.areaCode = areaCode;
	}

	public String getAreaCode() 
	{
		return areaCode;
	}
	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	public String getAreaName()
	{
		return areaName;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setParentid(String parentid) 
	{
		this.parentid = parentid;
	}

	public String getParentid() 
	{
		return parentid;
	}
	public void setZipCode(String zipCode) 
	{
		this.zipCode = zipCode;
	}

	public String getZipCode() 
	{
		return zipCode;
	}
	public void setAlphaCode(String alphaCode) 
	{
		this.alphaCode = alphaCode;
	}

	public String getAlphaCode() 
	{
		return alphaCode;
	}

}
