package com.nh.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nh.oms.config.Interceptor;
import com.nh.oms.model.oms.OmsExternalInterfaceLog;
import com.nh.oms.model.oms.OmsOrderDetail;
import com.nh.oms.model.oms.OmsPupuDeliveryExt;
import com.nh.oms.service.IDeliveryInfoService;
import com.nh.oms.service.IExternalInterfaceLogService;
import com.nh.oms.service.IOrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Tsui
 * @Date 2018-11-21
 * @Description 订单发货接口
 */
@Controller
@RequestMapping("/api/product")
public class OrderDeliveryController {

    private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    IDeliveryInfoService deliveryInfoService;

    @Resource
    private IOrderDetailService orderDetailService;

    @Resource
    private IExternalInterfaceLogService iExternalInterfaceLogService;

    @PostMapping("/delivery")
    @ResponseBody
    public String deliverySync(@RequestBody String param) {

        long startTime = System.currentTimeMillis();
        String errorMsg = "同步异常!";
        JSONObject result = new JSONObject(true);
        logger.info("开始调用时间：{},参数：{}",startTime,param);
        try {
            JSONObject datas = JSON.parseObject(param);
            JSONArray delivery = (JSONArray) datas.get("datas");

            List<OmsPupuDeliveryExt> omsPupuSaleoutExtList = JSONObject.parseArray(delivery.toJSONString(),
                    OmsPupuDeliveryExt.class);

            //每次传输的数量不允许超过1000条,
            if (omsPupuSaleoutExtList.size() > 1000) {
                result.put("status", "1");
                result.put("msg", "为了保证传输效率,每次请求数量不允许超过1000条记录！");
            }

            //噗噗管信息
            List<OmsPupuDeliveryExt> pupuList = new ArrayList<OmsPupuDeliveryExt>();
            //PM信息
            List<OmsOrderDetail> pmList = new ArrayList<OmsOrderDetail>();
            //噗噗管
            List<JSONObject> omspupuList = new ArrayList<JSONObject>();

            OmsOrderDetail omsOrderDetail = new OmsOrderDetail();
            JSONObject jsonObject;
            for (OmsPupuDeliveryExt omsPupuSaleoutExt : omsPupuSaleoutExtList) {

                if (omsPupuSaleoutExt.getOrderNo().startsWith("PP")) {
                    jsonObject = new JSONObject();
                    pupuList.add(omsPupuSaleoutExt);

                    //更新噗噗管问卷表
                    jsonObject.put("sampleno", omsPupuSaleoutExt.getSampleno()); //噗噗码
                    jsonObject.put("channelCode", omsPupuSaleoutExt.getChannelCode());

                    //噗噗code
                    jsonObject.put("orderNo", omsPupuSaleoutExt.getOrderNo());
                    jsonObject.put("boxDeliveryNo", omsPupuSaleoutExt.getBoxDeliveryNo());
                    jsonObject.put("indexSort", omsPupuSaleoutExt.getIndexSort());
                    jsonObject.put("batchNo", omsPupuSaleoutExt.getBatchNo());
                    jsonObject.put("rowNum", omsPupuSaleoutExt.getRowNum());
                    jsonObject.put("positions", omsPupuSaleoutExt.getPositions());

                    omspupuList.add(jsonObject);

                } else if (omsPupuSaleoutExt.getOrderNo().startsWith("PM")) {
                    omsOrderDetail = new OmsOrderDetail();
                    jsonObject = new JSONObject();
                    try {

                        omsOrderDetail.setOrderNo(omsPupuSaleoutExt.getOrderNo());
                        omsOrderDetail.setIndexSort(omsPupuSaleoutExt.getIndexSort());
                        omsOrderDetail.setSampleno(omsPupuSaleoutExt.getSampleno());
                        omsOrderDetail.setBoxDeliveryNo(omsPupuSaleoutExt.getBoxDeliveryNo());
                        //发货单号 omsPupuSaleoutExt.getDeliveryNo()
                        omsOrderDetail.setSendBoxTime(omsPupuSaleoutExt.getDeliveryTime());
                        omsOrderDetail.setQuantity(omsPupuSaleoutExt.getDeliveryQty());
                        omsOrderDetail.setDeadTime(omsPupuSaleoutExt.getDeadTime());
                        omsOrderDetail.setObjectType(omsPupuSaleoutExt.getObjectType());
                        omsOrderDetail.setManufactureTime(omsPupuSaleoutExt.getManufactureTime());
                        omsOrderDetail.setBatchNo(omsPupuSaleoutExt.getBatchNo());
                        omsOrderDetail.setRowState(11);

                        pmList.add(omsOrderDetail);

                        if ("3".equals(omsPupuSaleoutExt.getObjectType())) {
                            //更新噗噗管问卷表

                            jsonObject.put("sampleno", omsPupuSaleoutExt.getSampleno());
                            jsonObject.put("channelCode", omsPupuSaleoutExt.getChannelCode());

                            omspupuList.add(jsonObject);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (pmList.size() > 0) {
                errorMsg = orderDetailService.updateSaleOut(pmList ,omspupuList);
            }
            //如果是噗噗管,则插入到噗噗管发货表中
            if (pupuList.size() > 0) {
                errorMsg = deliveryInfoService.insertDelivery(pupuList ,omspupuList);
            }

//            if (omspupuList.size() > 0) {
//                errorMsg += deliveryInfoService.updateOmsPupu(omspupuList);
//            }

            if (errorMsg.length() > 0) {
                result.put("status", "2");
                result.put("msg", errorMsg);
            } else {
                OmsExternalInterfaceLog omsExternalInterfaceLog = new OmsExternalInterfaceLog();
                omsExternalInterfaceLog.setParam(param);
                omsExternalInterfaceLog.setInterfaceName("api/product/delivery");
                omsExternalInterfaceLog.setIsSuccess("1");
                iExternalInterfaceLogService.saveExternalLog(omsExternalInterfaceLog);

                result.put("status", "0");
                result.put("msg", "同步成功！");
            }

        } catch (Exception error) {
            logger.error(error.getMessage());
            result.put("status", 1);
            result.put("msg", "错误原因" + error.getMessage());
        }

        long endTime = System.currentTimeMillis();
        logger.info("调用delivery接口完成时间:{},耗时:{},参数：{}",endTime,endTime-startTime, result.toJSONString());

        return result.toJSONString();
    }
}
