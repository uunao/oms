package com.nh.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.nh.oms.config.Interceptor;
import com.nh.oms.model.oms.OmsDept;
import com.nh.oms.model.oms.OmsUser;
import com.nh.oms.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 同步职员信息及部门到OMS系统
 * 
 * @author ColinCheng
 * @date 2018-11-12
 */
@Controller
@RequestMapping("/api/baseInfo")
public class UserController
{
	private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	@Autowired
	private IUserService userService;

	/**
	 * 保存部门信息
	 */
	@PostMapping("/department")
	@ResponseBody
	public String deptInfoSave(@RequestBody String param)
	{
		JSONObject resultJson = new JSONObject(true);
		Set<String> deptCodeSet = new HashSet<String>();
		List<OmsDept> deptList = new ArrayList<OmsDept>();

		try{
			JSONObject jsonObject = JSON.parseObject(param);
			JSONArray datas = jsonObject.getJSONArray("datas");

			if (datas.size() > 1000) {
				resultJson.put("error_code", "1");
				resultJson.put("error_msg", "数据不允许超过1000条");
				return resultJson.toJSONString();
			}

			for (Object obj : datas) {
				JSONObject data = (JSONObject) obj;
				OmsDept omsDept = JSON.parseObject(data.toJSONString(), new TypeReference<OmsDept>(){});

				deptCodeSet.add(omsDept.getDeptCode());
				deptList.add(omsDept);
			}

			if (deptList.size() > 0) {
				List<String> res = userService.saveDeptInfo(deptList, deptCodeSet);
				if (res.size() > 0) {
					resultJson.put("error_code", "2");
					resultJson.put("error_msg", "以下部门已经存在，未进行同步。" + StringUtils.join(res, ","));
				} else {
					resultJson.put("error_code", "0");
					resultJson.put("error_msg", "同步成功！");
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			resultJson.put("error_code", "1");
			resultJson.put("error_msg", "数据异常！");
		}

		return resultJson.toJSONString();
	}
	
	/**
	 * 保存用户信息，并同时添加部门关联信息
	 */
	@PostMapping("/staff")
	@ResponseBody
	public String userInfoSave(@RequestBody String param)
	{
		JSONObject resultJson = new JSONObject(true);
		Set<String> loginNameSet = new HashSet<String>();
		List<OmsUser> userList = new ArrayList<OmsUser>();

		try{
			JSONObject jsonObject = JSON.parseObject(param);
			JSONArray datas = jsonObject.getJSONArray("datas");

			if (datas.size() > 1000) {
				resultJson.put("status", "1");
				resultJson.put("msg", "数据不允许超过1000条");
				return resultJson.toJSONString();
			}

			for (Object obj : datas) {
				JSONObject data = (JSONObject) obj;
				OmsUser omsUser = JSON.parseObject(data.toJSONString(), new TypeReference<OmsUser>(){});

				loginNameSet.add(omsUser.getLoginName());
				userList.add(omsUser);
			}

			if (userList.size() > 0) {
				List<String> res = userService.saveUserInfo(userList, loginNameSet);
				if (res.size() > 0) {
					resultJson.put("status", "2");
					resultJson.put("msg", "以下用户已经存在，未进行同步。" + StringUtils.join(res, ","));
				} else {
					resultJson.put("status", "0");
					resultJson.put("msg", "同步成功！");
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			resultJson.put("status", "1");
			resultJson.put("msg", "数据异常！");
		}

		return resultJson.toJSONString();
	}
}
