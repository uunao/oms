package com.nh.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.nh.oms.config.Interceptor;
import com.nh.oms.model.oms.OmsBaseProduct;
import com.nh.oms.model.oms.OmsComponent;
import com.nh.oms.service.IProductInfoService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 同步bom信息和物料信息到系统
 *
 * @author Tsui
 * @date 2018-11-06
 */
@RestController
@RequestMapping("/api/product")
public class ProductInfoController {

    private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    private IProductInfoService productInfoService;

    @PostMapping(value = "/materialInfo")
    @ResponseBody
    public String materialInfoSave(@RequestBody String param) {
        logger.info(param);
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

            for (Object obj : datas) {
                Map<String, Object> map = new HashMap<String, Object>();
                JSONObject data = (JSONObject) obj;
                String code = (String) data.get("code");
                proCode.add(code);
                JSONObject baseProduct = (JSONObject) data.get("baseProduct");

                OmsBaseProduct omsBaseProduct = JSON.parseObject(baseProduct.toJSONString()
                        , new TypeReference<OmsBaseProduct>() {
                        });
                proCode.add(omsBaseProduct.getCode());
                map.put("header", omsBaseProduct);

                list.add(map);
            }

            if (list.size() > 0) {
                List<String> res = productInfoService.saveProduct(list, proCode);
                if (res.size() > 0) {
                    resultJson.put("status", "2");
                    resultJson.put("msg", "以下编码已经存在：" + String.join(",", res));
                } else {
                    resultJson.put("status", "0");
                    resultJson.put("msg", "同步成功！");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultJson.put("status", "1");
            resultJson.put("msg", "数据异常！");
        }

        logger.info("调用materialInfo接口完成:{}",resultJson.toJSONString());
        return resultJson.toJSONString();
    }

    @PostMapping(value = "/componentInfo")
    @ResponseBody
    public String componentInfoSave(@RequestBody String param) {
        logger.info(param);
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

            for (Object obj : datas) {
                Map<String, Object> map = new HashMap<String, Object>();
                JSONObject data = (JSONObject) obj;
                String code = (String) data.get("code");
                proCode.add(code);
                map.put("parent", code);
                JSONArray components = data.getJSONArray("components");
                List<OmsComponent> componentList = JSONObject.parseArray(components.toJSONString(), OmsComponent.class);

                //汇总所有的编码
                for(OmsComponent component : componentList){
                    proCode.add(component.getProductCode());
                    proCode.add(component.getComponentCode());
                }

                map.put("body", componentList);
                list.add(map);
            }

            if (list.size() > 0) {
                List<String> res = productInfoService.saveComponents(list, proCode);
                if (res.size() > 0) {
                    resultJson.put("status", "2");
                    resultJson.put("msg", "以下物料编码不存在：" + String.join(",", res));
                    logger.info(resultJson.toJSONString());
                } else {
                    resultJson.put("status", "0");
                    resultJson.put("msg", "同步成功！");
                    logger.info(resultJson.toJSONString());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultJson.put("status", "1");
            resultJson.put("msg", "数据异常！");
        }

        logger.info("调用componentInfo接口完成:{}",resultJson.toJSONString());
        return resultJson.toJSONString();
    }


}