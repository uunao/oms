package com.nh.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nh.oms.config.Interceptor;
import com.nh.oms.model.oms.OmsChannel;
import com.nh.oms.model.oms.OmsChannelGroup;
import com.nh.oms.service.IChannelInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 同步渠道分类表针对价格体系
 *
 * @author Tsui
 * @date 2018-11-06
 */
@RestController
@RequestMapping("/api/channel")
public class ChannelInfoController {

    private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    private IChannelInfoService channelInfoService;

    @PostMapping(value = "/channelGroup")
    @ResponseBody
    public String materialInfoSave(@RequestBody String param) {

        JSONObject resultJson = new JSONObject(true);
        Set<String> proCode = new HashSet<String>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try {
            JSONObject jsonObject = JSON.parseObject(param);
            JSONArray datas = jsonObject.getJSONArray("datas");

            if (datas.size() > 1000) {
                resultJson.put("status", "1");
                resultJson.put("msg", "数据不允许超过1000条");
                return resultJson.toJSONString();
            }

            List<OmsChannelGroup> componentList = JSONObject.parseArray(datas.toJSONString(), OmsChannelGroup.class);


            if (componentList.size() > 0) {
                List<String> res = channelInfoService.saveChannel(componentList);
                if (res.size() > 0) {
                    resultJson.put("status", "2");
                    resultJson.put("msg", "以下编码已经存在：" + String.join(",", res));
                } else {
                    resultJson.put("status", "0");
                    resultJson.put("msg", "插入成功！");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultJson.put("status", "1");
            resultJson.put("msg", "数据异常！");
        }

        logger.info("调用channelGroup接口完成:{}",resultJson.toJSONString());
        return resultJson.toJSONString();
    }

    @PostMapping("channelGroupPid")
    public HashMap<String, Object>  updateChannelGroupPid(@RequestBody List<OmsChannel> omsChannelList) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            StringBuilder msg =  channelInfoService.updateChannelGroupPid(omsChannelList);
            if (msg.length() == 0) {
                map.put("status", "0");
                map.put("msg", "同步成功");
            }else {
                map.put("status", "1");
                map.put("msg", msg);
            }
        }catch (Exception e) {
            map.put("status", "1");
            map.put("msg", "未知错误请联系OMS开发人员:" + e.getMessage());
        }
        return map;
    }
}