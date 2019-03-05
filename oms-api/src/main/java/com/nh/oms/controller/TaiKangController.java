package com.nh.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nh.oms.common.utils.HttpUtils;
import com.nh.oms.service.IOrderService;
import com.nh.oms.transform.*;
import com.nh.oms.util.JsonUtils;
import com.nh.oms.util.MD5Util;
import com.nh.oms.util.StringUtil;
import com.nh.oms.util.SystemHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ccWang on 2018/11/27.
 */

@RestController
@RequestMapping("api/taiKang")
public class TaiKangController {

    private static final Logger LOG = LoggerFactory.getLogger(TaiKangController.class);

    @Autowired
    Environment env;

    @Autowired
    IOrderService iOrderService;

    /*
    * 同步泰康报告数据
    */
    @RequestMapping(value = "/sendTaiKangInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sendTaiKang() {

        LOG.info("同步泰康报告数据开始接口调用,时间：{}", SystemHelper.getNowTimeString());

        String insurekey = env.getProperty("taiKang.appKey");
        String Apiurl = env.getProperty("taiKang.url");

        try {

            String taiKangUrl = env.getProperty("taiKang.url");
            String appKey = env.getProperty("taiKang.appKey");
            List<OrderReportInfo> orderReportInfoList = iOrderService.getOrderForTaiKang();

            //循环调用泰康接口推送数据
            for (int i = 0; i < orderReportInfoList.size(); i++) {


                try {

                    TaiKangInfo taiKangInfo = new TaiKangInfo();
                    OrderReportInfo orderReportInfo = orderReportInfoList.get(i);

                    //表头字段
                    TaiKangHeadInfor taiKangHeadInfor = new TaiKangHeadInfor();
                    taiKangHeadInfor.serial_no = orderReportInfoList.get(i).getOrderNo();
                    taiKangHeadInfor.timestamp = SystemHelper.getNowTimeString();

                    ProductPlan productPlan = new ProductPlan(orderReportInfo.getPolicyName());
                    //订单信息
                    TaiKangOrderInfo taiKangOrderInfo = new TaiKangOrderInfo();
                    taiKangOrderInfo.product_id = "9905";
                    taiKangOrderInfo.plan_id = productPlan.getPlan_id();
                    taiKangOrderInfo.premium = productPlan.getPremium();
                    taiKangOrderInfo.risk = productPlan.getRisk();
                    taiKangOrderInfo.check_date = orderReportInfo.getMeasureTime();
                    taiKangOrderInfo.check_value = orderReportInfo.getTestResult();
                    if (!StringUtil.isNullOrEmpty(orderReportInfo.getTestResult())) {
                        taiKangOrderInfo.check_result = Integer.parseInt(orderReportInfo.getTestResult()) > 165 ? "2" : "1";
                    }
                    taiKangOrderInfo.notification = "1";
                    taiKangOrderInfo.check_id = orderReportInfo.getSampleno();

                    //人员信息
                    TaiKangPersonInfo taiKangPersonInfo = new TaiKangPersonInfo();
                    taiKangPersonInfo.name = orderReportInfo.getDetectedPerson();
                    taiKangPersonInfo.cid_type = orderReportInfo.getCertificateType();
                    taiKangPersonInfo.cid_number = orderReportInfo.getCertificateNo();
                    taiKangPersonInfo.sex = orderReportInfo.getSex();
                    taiKangPersonInfo.birthday = !StringUtil.isNullOrEmpty(orderReportInfo.getBirthday()) ?
                            SystemHelper.getTimeToDate(orderReportInfo.getBirthday()).toString() : "";
                    taiKangPersonInfo.mobile = orderReportInfo.getDetectedPhone();

                    taiKangInfo.header_info = taiKangHeadInfor;
                    taiKangInfo.order_info = taiKangOrderInfo;
                    taiKangInfo.person_info = taiKangPersonInfo;

                    String resJson = JsonUtils.DEFAULT.toJson(taiKangInfo);

                    //调用泰康接口
                    // TODO: 2018/12/5  用GBK格式编码
                    String sign = MD5Util.md5EncryptBase64(resJson + insurekey);
                    String insureurl = Apiurl + sign;
                    String res = HttpUtils.sendJsonPost(insureurl, JSON.parseObject(resJson));

                    JSONObject jbRes = JSON.parseObject(res);
                    if (!StringUtil.isNullOrEmpty(jbRes.get("policy_no").toString())) {
                        //更新OMS数据库
                        iOrderService.updatePolicyNo(orderReportInfo.getId(), jbRes.get("policy_no").toString());

                    }
                }catch (Exception ex){
                    LOG.error("同步泰康报告数据异常：{}", ex.toString());
                }
            }
        } catch (Exception ex) {
            LOG.error("同步泰康报告数据异常：{}", ex.toString());
        }

    }
}
