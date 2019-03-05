package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsUserdept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户部门关系 数据层
 * 
 * @author ColinCheng
 * @date 2018-11-12
 */
public interface OmsUserdeptMapper
{
	/**
     * 查询用户部门关系信息
     * 
     * @param iid 用户部门关系ID
     * @return 用户部门关系信息
     */
	public OmsUserdept selectUserdeptById(Long iid);
	
	/**
     * 查询用户部门关系列表
     * 
     * @param userdept 用户部门关系信息
     * @return 用户部门关系集合
     */
	public List<OmsUserdept> selectUserdeptList(OmsUserdept userdept);
	
	/**
     * 新增用户部门关系
     * 
     * @param userdept 用户部门关系信息
     * @return 结果
     */
	public int insertUserdept(OmsUserdept userdept);
	
	/**
     * 修改用户部门关系
     * 
     * @param userdept 用户部门关系信息
     * @return 结果
     */
	public int updateUserdept(OmsUserdept userdept);
	
	/**
     * 删除用户部门关系
     * 
     * @param iid 用户部门关系ID
     * @return 结果
     */
	public int deleteUserdeptById(Long iid);
	
	/**
     * 批量删除用户部门关系
     * 
     * @param iids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserdeptByIds(String[] iids);

	/**
	 * 批量插入用户部门关系
	 * @param userdepts
	 * @return
     */
	int insertUserdeptBatch(@Param("userdepts") List<OmsUserdept> userdepts) ;
	
}