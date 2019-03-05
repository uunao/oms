package com.nh.oms.service;

import com.nh.oms.model.oms.OmsDept;
import com.nh.oms.model.oms.OmsUser;

import java.util.List;
import java.util.Set;

/**
 * 用户及部门 服务层
 * 
 * @author ColinCheng
 * @date 2018-11-12
 */
public interface IUserService 
{
	/**
     * 查询用户信息
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
	public OmsUser selectUserById(Long userId);
	
	/**
     * 查询用户列表
     * 
     * @param user 用户信息
     * @return 用户集合
     */
	public List<OmsUser> selectUserList(OmsUser user);
	
	/**
     * 新增用户
     * 
     * @param user 用户信息
     * @return 结果
     */
	public int insertUser(OmsUser user);
	
	/**
     * 修改用户
     * 
     * @param user 用户信息
     * @return 结果
     */
	public int updateUser(OmsUser user);
		
	/**
     * 删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserByIds(String ids);

	/**
	 * 保存用户信息，并同时添加部门关联信息
	 * @param userList 用户信息列表
	 * @return
     */
	public List<String> saveUserInfo(List<OmsUser> userList, Set<String> loginNameSet);



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
	 * 删除部门信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteDeptByIds(String ids);

	/**
	 * 保存部门信息
	 * @param deptList 部门信息列表
	 * @return
	 */
	public List<String> saveDeptInfo(List<OmsDept> deptList, Set<String> deptCodeSet);
}
