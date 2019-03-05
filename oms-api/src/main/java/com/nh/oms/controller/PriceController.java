package com.nh.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nh.oms.config.Interceptor;
import com.nh.oms.model.oms.OmsPrice;
import com.nh.oms.service.IPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jjxu on 2018/11/6.
 */
@Controller
@RequestMapping("/api/priceSystem")
public class PriceController {

    private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    IPriceService priceService;

    @PostMapping("/doPrice")
    @ResponseBody
    public String postPriceJson(@RequestBody String param){
        JSONObject result = new JSONObject(true);
        logger.info(param);
        try{
            JSONObject datas= JSON.parseObject(param);
            JSONArray omsPrice = (JSONArray) datas.get("datas");

            List<OmsPrice> omsPrices = JSONObject.parseArray(omsPrice.toJSONString(), OmsPrice.class);

            String errorMsg = priceService.insertSelective(omsPrices);
            if(errorMsg.length()>0){
                result.put("status", "2");
                result.put("msg", errorMsg);
            }else{
                result.put("status","0");
                result.put("msg","同步成功！");
            }

        }catch (Exception error){
            logger.error(error.getMessage());
            result.put("status",1);
            result.put("msg","错误原因"+error.getMessage());
        }
        logger.info("调用doPrice接口完成:{}",result.toJSONString());
        return  result.toJSONString();
    }
}
