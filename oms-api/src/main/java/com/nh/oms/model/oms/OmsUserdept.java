package com.nh.oms.model.oms;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 用户部门关系表 oms_userdept
 * 
 * @author ColinCheng
 * @date 2018-11-12
 */
public class OmsUserdept implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Long iid;
	/** 部门ID */
	private Long deptId;
	/** 用户ID */
	private Long userId;

	/** 部门编码 */
	private String deptCode;
	/** 用户名 */
	private String loginName;

	public void setIid(Long iid) 
	{
		this.iid = iid;
	}

	public Long getIid() 
	{
		return iid;
	}
	public void setDeptId(Long deptId) 
	{
		this.deptId = deptId;
	}

	public Long getDeptId() 
	{
		return deptId;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("iid", getIid())
            .append("deptId", getDeptId())
            .append("userId", getUserId())
            .toString();
    }
}
