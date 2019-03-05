package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 部门 数据层
 * 
 * @author ColinCheng
 * @date 2018-11-12
 */
public interface OmsDeptMapper
{
	/**
     * 查询部门信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
	public OmsDept selectDeptById(Long deptId);
	
	/**
     * 查询部门列表
     * 
     * @param dept 部门信息
     * @return 部门集合
     */
	public List<OmsDept> selectDeptList(OmsDept dept);
	
	/**
     * 新增部门
     * 
     * @param dept 部门信息
     * @return 结果
     */
	public int insertDept(OmsDept dept);
	
	/**
     * 修改部门
     * 
     * @param dept 部门信息
     * @return 结果
     */
	public int updateDept(OmsDept dept);
	
	/**
     * 删除部门
     * 
     * @param deptId 部门ID
     * @return 结果
     */
	public int deleteDeptById(Long deptId);
	
	/**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeptByIds(String[] deptIds);

	/**
	 * 查询部门是否存在
	 * @param deptCode
	 * @return
	 */
	List<String> isExists(@Param("deptCode") Set<String> deptCode);

	/**
	 * 查询部门信息
	 *
	 * @param deptCode 部门编码
	 * @return 部门信息
	 */
	public OmsDept selectDeptByCode(String deptCode);
}