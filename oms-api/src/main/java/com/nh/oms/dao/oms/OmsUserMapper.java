package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户 数据层
 * 
 * @author ColinCheng
 * @date 2018-11-12
 */
public interface OmsUserMapper
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
     * 删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
	public int deleteUserById(Long userId);
	
	/**
     * 批量删除用户
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserByIds(String[] userIds);


	/**
	 * 查询用户是否存在
	 * @param loginName
	 * @return
	 */
	List<String> isExists(@Param("loginName") Set<String> loginName);
}