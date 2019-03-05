package com.nh.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nh.oms.config.Interceptor;
import com.nh.oms.model.oms.OmsExternalInterfaceLog;
import com.nh.oms.model.oms.OmsOrderDetail;
import com.nh.oms.service.IExternalInterfaceLogService;
import com.nh.oms.service.IOrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author Will
 * @date 2018/11/06
 */
@RestController
@RequestMapping("api/orderDetail")
public class OrderDetailController {

    private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    private IOrderDetailService iOrderDetailService;

    @Resource
    private IExternalInterfaceLogService iExternalInterfaceLogService;

    /**
     * 取消订单
     *
     * @param omsOrderDetailList
     * @return
     */
    @PostMapping("rowState")
    public HashMap<String, Object> cancel(@RequestBody List<OmsOrderDetail> omsOrderDetailList) {
        logger.info(JSON.toJSONString(omsOrderDetailList));
        HashMap<String, Object> map = new HashMap<>();
        try {
            iOrderDetailService.updateList(omsOrderDetailList, -1);
            map.put("status", 0);
            map.put("msg", "同步成功!");
        } catch (Exception error) {
            logger.error(error.getMessage());
            map.put("status", 1);
            map.put("msg", "错误原因：" + error.getMessage());
        }
        return map;
    }

    /**
     * 传递样本签回情况
     *
     * @param omsOrderDetailList
     * @return
     */
    @PostMapping("signBack")
    public HashMap<String, Object> updateSignBackInfo(@RequestBody List<OmsOrderDetail> omsOrderDetailList) {
        logger.info(JSON.toJSONString(omsOrderDetailList));
        HashMap<String, Object> map = new HashMap<>();
        try {
            iOrderDetailService.updateList(omsOrderDetailList, 21);
            map.put("status", 0);
            map.put("msg", "样本签回信息更新成功!");
        } catch (Exception error) {
            logger.error(error.getMessage());
            map.put("status", 1);
            map.put("msg", "错误原因：" + error.getMessage());
        }
        return map;
    }

    /**
     * 虚拟收入确认
     *
     * @param omsOrderDetailList
     * @return
     */
    @PostMapping("virtualIncomeConfirm")
    public HashMap<String, Object> virtualIncomeConfirm(@RequestBody List<OmsOrderDetail> omsOrderDetailList) {
        logger.info(JSON.toJSONString(omsOrderDetailList));
        HashMap<String, Object> map = new HashMap<>();
        try {
            iOrderDetailService.updateList(omsOrderDetailList, 20);
            map.put("status", 0);
            map.put("msg", "样本签回信息更新成功!");
        } catch (Exception error) {
            logger.error(error.getMessage());
            map.put("status", 1);
            map.put("msg", "错误原因：" + error.getMessage());
        }
        return map;
    }

    /**
     * 出库
     *
     * @param jsonObjectList
     * @return
     */
    @PostMapping("delivery")
    public HashMap<String, Object> updateStateOfBoxDelivery(@RequestBody List<JSONObject> jsonObjectList) {
        logger.info(jsonObjectList.toString());
        HashMap<String, Object> map = new HashMap<>();
        try {
            iOrderDetailService.delivery(jsonObjectList);
            map.put("status", 0);
            map.put("msg", "更新成功!");

            OmsExternalInterfaceLog omsExternalInterfaceLog = new OmsExternalInterfaceLog();
            omsExternalInterfaceLog.setParam(JSONObject.toJSONString(jsonObjectList));
            omsExternalInterfaceLog.setInterfaceName("api/orderDetail/delivery");
            omsExternalInterfaceLog.setIsSuccess("1");
            iExternalInterfaceLogService.saveExternalLog(omsExternalInterfaceLog);

        } catch (Exception error) {
            logger.error(error.getMessage());
            /*System.out.println(error.getMessage());
            // 李丕佑的特殊要求，如果出库单号已存在要视为同步成功，状态要为0，其余异常则视为失败
            if ("出库单号".equals(error.getMessage().substring(0, 4))) {
                map.put("status", 0);
                map.put("msg", "错误原因：" + error.getMessage());
            }else {
                map.put("status", 1);
                map.put("msg", "错误原因：" + error.getMessage());
            }*/
            map.put("status", 1);
            map.put("msg", "错误原因：" + error.getMessage());
        }
        return map;
    }
}
