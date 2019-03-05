package com.nh.oms.model.oms;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户表 sys_user
 * 
 * @author ColinCheng
 * @date 2018-11-12
 */
public class OmsUser implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/** 用户ID */
	private Long userId;
	/** 部门ID */
	private Long deptId;
	/** 登录账号 */
	private String loginName;
	/** 用户名 */
	private String userName;
	/** 用户类型（00系统用户,01员工/渠道,02客户） */
	private String userType;
	/** 用户邮箱 */
	private String email;
	/** 手机号码 */
	private String phonenumber;
	/** 用户性别（0-未知, 1-男, 2-女） */
	private String sex;
	/** 头像路径 */
	private String avatar;
	/** 密码 */
	private String password;
	/** 盐加密 */
	private String salt;
	/** 帐号状态（0正常 1停用） */
	private String status;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;
	/** 最后登陆IP */
	private String loginIp;
	/** 最后登陆时间 */
	private Date loginDate;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 密码修改时间(旧) */
	private Date modifyPasswordTime;
	/** 员工编号(旧) */
	private String empno;
	/** 员工姓名(旧) */
	private String empname;
	/** 员工生日(旧) */
	private Date empBirthday;
	/** 客户对应用户ID(旧) */
	private Long parentUserId;
	/** 超级数据权限(旧) */
	private String isDataAdmin;
	/** 备注 */
	private String remark;

	/** 生日 */
	private String birthday;
	/** 所属部门关系 */
	private List<OmsUserdept> department;

	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setDeptId(Long deptId) 
	{
		this.deptId = deptId;
	}

	public Long getDeptId() 
	{
		return deptId;
	}
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}

	public String getLoginName() 
	{
		return loginName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setUserType(String userType) 
	{
		this.userType = userType;
	}

	public String getUserType() 
	{
		return userType;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	public void setPhonenumber(String phonenumber) 
	{
		this.phonenumber = phonenumber;
	}

	public String getPhonenumber() 
	{
		return phonenumber;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setAvatar(String avatar) 
	{
		this.avatar = avatar;
	}

	public String getAvatar() 
	{
		return avatar;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}
	public void setSalt(String salt) 
	{
		this.salt = salt;
	}

	public String getSalt() 
	{
		return salt;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}
	public void setLoginIp(String loginIp) 
	{
		this.loginIp = loginIp;
	}

	public String getLoginIp() 
	{
		return loginIp;
	}
	public void setLoginDate(Date loginDate) 
	{
		this.loginDate = loginDate;
	}

	public Date getLoginDate() 
	{
		return loginDate;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setModifyPasswordTime(Date modifyPasswordTime)
	{
		this.modifyPasswordTime = modifyPasswordTime;
	}

	public Date getModifyPasswordTime()
	{
		return modifyPasswordTime;
	}
	public void setEmpno(String empno) 
	{
		this.empno = empno;
	}

	public String getEmpno() 
	{
		return empno;
	}
	public void setEmpname(String empname) 
	{
		this.empname = empname;
	}

	public String getEmpname() 
	{
		return empname;
	}
	public void setEmpBirthday(Date empBirthday) 
	{
		this.empBirthday = empBirthday;
	}

	public Date getEmpBirthday() 
	{
		return empBirthday;
	}
	public void setParentUserId(Long parentUserId) 
	{
		this.parentUserId = parentUserId;
	}

	public Long getParentUserId() 
	{
		return parentUserId;
	}
	public void setIsDataAdmin(String isDataAdmin) 
	{
		this.isDataAdmin = isDataAdmin;
	}

	public String getIsDataAdmin() 
	{
		return isDataAdmin;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<OmsUserdept> getDepartment() {
		if(department == null){
			department = new ArrayList<OmsUserdept>();
		}
		return department;
	}

	public void setDepartment(List<OmsUserdept> department) {
		this.department = department;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("loginName", getLoginName())
            .append("userName", getUserName())
            .append("userType", getUserType())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("password", getPassword())
            .append("salt", getSalt())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("modifyPasswordTime", getModifyPasswordTime())
            .append("empno", getEmpno())
            .append("empname", getEmpname())
            .append("empBirthday", getEmpBirthday())
            .append("parentUserId", getParentUserId())
            .append("isDataAdmin", getIsDataAdmin())
            .append("remark", getRemark())
            .toString();
    }
}
