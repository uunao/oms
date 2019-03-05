package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsDeptMapper;
import com.nh.oms.dao.oms.OmsUserMapper;
import com.nh.oms.dao.oms.OmsUserdeptMapper;
import com.nh.oms.model.oms.OmsDept;
import com.nh.oms.model.oms.OmsUser;
import com.nh.oms.model.oms.OmsUserdept;
import com.nh.oms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

/**
 * 用户及部门 服务层实现
 * 
 * @author ColinCheng
 * @date 2018-11-12
 */
@Service
public class UserServiceImpl implements IUserService
{
	@Autowired
	private OmsUserMapper userMapper;

	@Autowired
	private OmsUserdeptMapper userdeptMapper;

	@Autowired
	private OmsDeptMapper deptMapper;

	/**
     * 查询用户信息
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
	public OmsUser selectUserById(Long userId)
	{
	    return userMapper.selectUserById(userId);
	}
	
	/**
     * 查询用户列表
     * 
     * @param user 用户信息
     * @return 用户集合
     */
	@Override
	public List<OmsUser> selectUserList(OmsUser user)
	{
	    return userMapper.selectUserList(user);
	}
	
    /**
     * 新增用户
     * 
     * @param user 用户信息
     * @return 结果
     */
	@Override
	public int insertUser(OmsUser user)
	{
	    return userMapper.insertUser(user);
	}
	
	/**
     * 修改用户
     * 
     * @param user 用户信息
     * @return 结果
     */
	@Override
	public int updateUser(OmsUser user)
	{
	    return userMapper.updateUser(user);
	}

	/**
     * 删除用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserByIds(String ids)
	{
		return userMapper.deleteUserByIds(ids.split(","));
	}


	/**
	 * 查询部门信息
	 *
	 * @param deptId 部门ID
	 * @return 部门信息
	 */
	@Override
	public OmsDept selectDeptById(Long deptId)
	{
		return deptMapper.selectDeptById(deptId);
	}

	/**
	 * 查询部门列表
	 *
	 * @param dept 部门信息
	 * @return 部门集合
	 */
	@Override
	public List<OmsDept> selectDeptList(OmsDept dept)
	{
		return deptMapper.selectDeptList(dept);
	}

	/**
	 * 新增部门
	 *
	 * @param dept 部门信息
	 * @return 结果
	 */
	@Override
	public int insertDept(OmsDept dept)
	{
		return deptMapper.insertDept(dept);
	}

	/**
	 * 修改部门
	 *
	 * @param dept 部门信息
	 * @return 结果
	 */
	@Override
	public int updateDept(OmsDept dept)
	{
		return deptMapper.updateDept(dept);
	}

	/**
	 * 删除部门对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteDeptByIds(String ids)
	{
		return deptMapper.deleteDeptByIds(ids.split(","));
	}


	/**
	 * 保存部门信息
	 * @param deptList 部门信息列表
	 * @return
	 */
	public List<String> saveDeptInfo(List<OmsDept> deptList, Set<String> deptCodeSet){
		List<String> existDept = deptMapper.isExists(deptCodeSet);

		for (OmsDept dept:deptList){
			if(!existDept.contains(dept.getDeptCode())){
				OmsDept omsDept = deptMapper.selectDeptByCode(dept.getParentCode());
				if (omsDept != null) {
					dept.setParentId(omsDept.getDeptId());
					dept.setAncestors(omsDept.getAncestors() + "," + omsDept.getDeptId());
				}else{
					dept.setParentId(0L);
					dept.setAncestors("0");
				}

				dept.setCreateBy("ERP SYNC");

				// 插入部门信息
				deptMapper.insertDept(dept);
			}
		}

		return  existDept;
	}


	/**
	 * 保存用户信息，并同时添加部门关联信息
	 * @param userList 用户信息列表
	 * @return
	 */
	@Transient
	@Override
	public List<String> saveUserInfo(List<OmsUser> userList, Set<String> loginNameSet){
		List<String> existUser = userMapper.isExists(loginNameSet);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (OmsUser user:userList) {
			if(!existUser.contains(user.getLoginName())) {
				user.setEmpno(user.getLoginName());
				user.setEmpname(user.getUserName());
				if(user.getBirthday() != null) {
					user.setEmpBirthday(dateFormat.parse(user.getBirthday(), new ParsePosition(0)));
				}
                user.setDeptId(100L);
                user.setPassword("93bcab4ab719fde430e5ad90656a240e");   // 默认123456
				user.setCreateBy("ERP SYNC");

				// 插入用户信息
				userMapper.insertUser(user);

				// 插入用户部门关系信息
				List<OmsUserdept> userdeptList = user.getDepartment();
				for (OmsUserdept userdept:userdeptList) {
					userdept.setUserId(user.getUserId());
				}
				userdeptMapper.insertUserdeptBatch(userdeptList);
			}
		}

		return  existUser;
	}
}