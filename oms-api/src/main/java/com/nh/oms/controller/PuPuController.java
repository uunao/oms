package com.nh.oms.controller;

import com.alibaba.fastjson.JSONArray;
import com.nh.oms.common.utils.KingdeeHelper;
import com.nh.oms.model.oms.OmsOrderLog;
import com.nh.oms.model.oms.OmsPupuOrder;
import com.nh.oms.service.IOmsOrderLogService;
import com.nh.oms.service.IOmsOrderSynExpService;
import com.nh.oms.service.IPuPuCodeService;
import com.nh.oms.service.IPuPuOrderService;
import com.nh.oms.transform.*;
import com.nh.oms.util.JsonUtils;
import com.nh.oms.util.ReflectUtil;
import com.nh.oms.util.SystemHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.nh.oms.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ccWang on 2018/11/20.
 * 噗噗管订单同步与发货
 */

@RestController
@RequestMapping("api/puPu")
public class PuPuController {

    private static final Logger LOG = LoggerFactory.getLogger(PuPuController.class);

    private static final String[] NOT_NULL_EMPTY_FIELD = {"orderNo", "rowNo", "k3BillNo", "k3BillRowNo", "k3BillType"};


    @Autowired
    private IPuPuOrderService iPuPuOrderService;

    @Autowired
    private IOmsOrderSynExpService iOmsOrderSynExpService;

    @Autowired
    private IOmsOrderLogService iOmsOrderLogService;

    @Autowired
    private IPuPuCodeService iPuPuCodeService;


    //K3调用同步噗噗码信息
    @RequestMapping(value = "/sendPuPuInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MessageOut<Object> sendPuPuInfo(HttpServletRequest request) {
        try {
            StringBuffer jsonStr = new StringBuffer();
            try (BufferedReader bufferedReader = request.getReader()) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonStr.append(line);
                }
            } catch (Exception ex) {
                LOG.error("K3调用同步噗噗码信息数据接收异常：{}", ex.toString());
                return MessageOut.Get_FAIL_MESSAGE;
            }

            LOG.info("sendPuPuInfo message:{}", jsonStr.toString());

            Map<String, Object> paramsMap = JsonUtils.DEFAULT.fromJson(jsonStr.toString(), Map.class);
            PuPuCodeInfo puPuCodeInfo = new PuPuCodeInfo();
            puPuCodeInfo.setOrderNo(null == paramsMap.get("orderNo") ? "" : paramsMap.get("orderNo").toString());
            puPuCodeInfo.setRowNo(null == paramsMap.get("rowNo") ? "" : paramsMap.get("rowNo").toString());
            puPuCodeInfo.setK3BillNo(null == paramsMap.get("k3BillNo") ? "" : paramsMap.get("k3BillNo").toString());
            puPuCodeInfo.setK3BillRowNo(null == paramsMap.get("k3BillRowNo") ? "" : paramsMap.get("k3BillRowNo").toString());
            puPuCodeInfo.setK3BillType(null == paramsMap.get("k3BillType") ? "" : paramsMap.get("k3BillType").toString());
            puPuCodeInfo.setBatchNo(null == paramsMap.get("batchNo") ? "" : paramsMap.get("batchNo").toString());

            if (!checkBodyNullOrEmpty(puPuCodeInfo)) {
                LOG.error("K3调用同步噗噗码信息,单号信息必填项缺失！");
                return MessageOut.UPDATE_FAIL_MESSAGE;
            }

            if (null == paramsMap.get("puPuList") || "".equals(paramsMap.get("puPuList"))) {
                LOG.error("K3调用同步噗噗码信息，噗噗Code必填项缺失！");
                return MessageOut.UPDATE_FAIL_MESSAGE;
            }

            PuPuCodeInfo puPuCodeInfo1 = JsonUtils.DEFAULT.fromJson(jsonStr.toString(), PuPuCodeInfo.class);

            iPuPuCodeService.bulkInsertPuPuCodeInfo(puPuCodeInfo1);


        } catch (Exception ex) {

            LOG.error("K3调用同步噗噗码信息程序异常：", ex.toString());
            return MessageOut.Get_FAIL_MESSAGE;
        }

        return MessageOut.UPDATE_OK_MESSAGE;

    }


    //噗噗管订单同步
    @RequestMapping(value = "/synPuPuOrderInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MessageOut<Object> synPuPuOrder(@RequestBody SendPuPuOrderRequest sendPuPuOrderRequest) {

        String orderPuPuNo = sendPuPuOrderRequest.getOrderPuPuNo();
        String businessNo = sendPuPuOrderRequest.getBusinessNo();

        if (StringUtil.isNullOrEmpty(orderPuPuNo)) {
            LOG.error("-----噗噗管订单同步-----BusinessNo:{}，订单号为空，不能进行同步！", businessNo);
            return MessageOut.UPDATE_OK_MESSAGE;
        }

        try {//调用K3登陆接口认证登陆
            if (!KingdeeHelper.loginDefault()) {
                //未成功插入异常信息表
                iOmsOrderSynExpService.insert(orderPuPuNo, orderPuPuNo, "接口登陆未成功！");
                LOG.error("-----噗噗管订单同步-----订单号:{}，K3接口登陆未成功！", orderPuPuNo);
                return MessageOut.UPDATE_OK_MESSAGE;
            }

            //根据订单号获取oms噗噗管订单数据
            OmsPupuOrder omsPupuOrder = iPuPuOrderService.getPuPuOrderInfo(orderPuPuNo);
            if (null == omsPupuOrder) {
                return new MessageOut(false, "000005", "该订单号取不到数据！");
            }

            String resJson = getOrderJsonString(omsPupuOrder);

            LOG.info("-----噗噗管订单同步-----BusinessNo:{}，PuPuController:sendPuPuInfo入参：{}",
                    businessNo, resJson);

            String resK3 = KingdeeHelper.save("SAL_SaleOrder", resJson);
            if (resK3.contains("\"IsSuccess\":true")) {
                //修改订单状态，改成已经同步
                if (StringUtil.isNullOrEmpty(omsPupuOrder.getSynchState()) ||
                        "0".equals(omsPupuOrder.getSynchState())) {

                    iPuPuOrderService.updateOrderSynState(orderPuPuNo);
                } else {
                    //更新exchange表换货同步状态
                    iPuPuOrderService.updateExchangeSynState(orderPuPuNo);
                }
            } else {
                //未成功插入异常信息表
                iOmsOrderSynExpService.insert(orderPuPuNo, orderPuPuNo, resK3);
                return MessageOut.UPDATE_OK_MESSAGE;
            }
        } catch (Exception ex) {

            iOmsOrderSynExpService.insert(orderPuPuNo, orderPuPuNo, ex.toString());
            LOG.error("GetOrderParentController:synOrderParentNo,出现异常,噗噗管订单号：{}", orderPuPuNo);
            return MessageOut.UPDATE_OK_MESSAGE;
        }

        try {
            OmsOrderLog omsOrderLog = new OmsOrderLog();
            omsOrderLog.setOrderParentNo(orderPuPuNo);
            omsOrderLog.setSubOrderno(orderPuPuNo);
            omsOrderLog.setActionDate(SystemHelper.currentDate());
            omsOrderLog.setDeptName("系统");
            omsOrderLog.setUserId("oms_system");
            omsOrderLog.setRemark("系统噗噗管订单同步");
            iOmsOrderLogService.insert(omsOrderLog);

        } catch (Exception ex) {
            LOG.error("GetOrderParentController:synOrderParentNo,插入订单日志表时候出现错误,{},单号：{}", ex.toString(), orderPuPuNo);
        }

        return MessageOut.UPDATE_OK_MESSAGE;

    }

    private String getOrderJsonString(OmsPupuOrder omsPupuOrder) {
        String resJson = "";
        KsOrderAndDetail ksOrderAndDetail = new KsOrderAndDetail();
        KsModel ksModel = new KsModel();
        ksModel.FID = "0";
        ksModel.FBillNo = "";
        KsFNCommon ksFNCommon = new KsFNCommon();
        //根据渠道编码获取cusCode
        ksFNCommon.FNumber = iPuPuOrderService.getCusCodeByChannel(omsPupuOrder.getChannelCode());
        ksModel.FCustId = ksFNCommon;

        KsFNCommon ksFNCommon2 = new KsFNCommon();
        ksFNCommon2.FNumber = "XSDD01_SYS";
        ksModel.FBillTypeID = ksFNCommon2;
        ksModel.FDate = (null == omsPupuOrder.getCreateTime() || "".equals(omsPupuOrder.getCreateTime().toString()))
                ? SystemHelper.getNowTimeStringDate() : omsPupuOrder.getCreateTime().toString().substring(0, 10);
        KsFNCommon ksFNCommon3 = new KsFNCommon();
        ksFNCommon3.FNumber = "HLTX01_SYS";
        ksModel.FExchangeTypeId = ksFNCommon3;
        ksModel.FNote = StringUtil.replaceBlank(omsPupuOrder.getRemark());

        KsFNCommon ksFNCommon4 = new KsFNCommon();
        ksFNCommon4.FNumber = iPuPuOrderService.
                getEmpNoByUserId(null == omsPupuOrder.getSalesmanId() ? "" : omsPupuOrder.getSalesmanId().toString());//null == omsPupuOrder.getSalesmanId() ? "" : omsPupuOrder.getSalesmanId().toString();
        ksModel.FSalerId = ksFNCommon4;

        KsFNCommon ksFNCommon5 = new KsFNCommon();
        ksFNCommon5.FNumber = omsPupuOrder.getDepartment();
        ksModel.FSaleDeptId = ksFNCommon5;
        KsFNCommon ksFNCommon6 = new KsFNCommon();
        ksFNCommon6.FNumber = "400030";
        ksModel.FSaleOrgId = ksFNCommon6;
        KsFNCommon ksFNCommon9 = new KsFNCommon();
        ksFNCommon9.FNumber = "PRE001";
        ksModel.FLocalCurrId = ksFNCommon9;
        KsFNCommon ksFNCommon10 = new KsFNCommon();
        ksFNCommon10.FNumber = "PRE001";
        ksModel.FSettleCurrId = ksFNCommon10;

        //2018-12-07新加字段
        KsFNCommon ksFNCommon12 = new KsFNCommon();
        ksFNCommon12.FNumber = "400031";
        ksModel.F_JCJYS = ksFNCommon12;

        //OMS订单数据
        ksModel.F_OMS_OrderNo = omsPupuOrder.getOrderNo();
        ksModel.F_OMS_Purchaser = omsPupuOrder.getPurchaser();
        ksModel.F_OMS_PurchaserPhone = omsPupuOrder.getPurchaserPhone();
        ksModel.F_OMS_OrderMaker = omsPupuOrder.getOrderMaker();
        ksModel.F_OMS_OutTradeNo = omsPupuOrder.getOutTradeNo();
        ksModel.F_OMS_ReceiverName = StringUtil.replaceBlank(omsPupuOrder.getReceiverName());
        if ("".equals(omsPupuOrder.getAreaCode()) || null == omsPupuOrder.getAreaCode()) {

            ksModel.F_OMS_ReceiverAddress = StringUtil.replaceBlank(omsPupuOrder.getReceiverAddress());
        } else {
            List<String> listCodes = new ArrayList<>();
            listCodes.add(omsPupuOrder.getAreaCode());
            listCodes.add(omsPupuOrder.getAreaCode().substring(0, 4) + "00");
            listCodes.add(omsPupuOrder.getAreaCode().substring(0, 2) + "0000");
            String resArea = iPuPuOrderService.getAreaAddress(listCodes);
            ksModel.F_OMS_ReceiverAddress = StringUtil.replaceBlank(resArea + omsPupuOrder.getReceiverAddress());
        }

        ksModel.F_OMS_ReceiverPhone = omsPupuOrder.getReceiverPhone();
//        ksModel.F_OMS_TermDate = null == omsPupuOrder.getTermDate() ? "" : omsPupuOrder.getTermDate();
        //新加字段销售类型
        ksModel.F_PAEZ_SALETYPE = omsPupuOrder.getChannelType();

        ksModel.F_OMS_ReportIsPaper = "2";
        ksModel.F_OMS_ReportIsBusiness = "2";

        //2018-11-29新加字段
        ksModel.F_PAEZ_Combo = omsPupuOrder.getDeliveryType();
        ksModel.F_PAEZ_XNSRQR = omsPupuOrder.getTermEnable();
        ksModel.F_PAEZ_FictitiousInteger = (null == omsPupuOrder.getTermDays()
                || "".equals(omsPupuOrder.getTermDays())) ? 0 : omsPupuOrder.getTermDays();

        //2018-12-04新增字段
        ksModel.F_PAEZ_TOWMS = "1";

        //2018-12-31新加字段
        ksModel.F_PAEZ_SFKP = omsPupuOrder.getIsBill();
        ksModel.F_PAEZ_FPLX = omsPupuOrder.getBillType();
        ksModel.F_OMS_XSDDLX = "0";

        //SaleOrder Entry数据
        KsFMCommon ksFMCommon = new KsFMCommon();
        ksFMCommon.FName = omsPupuOrder.getProductUnit();

        List<KsSaleOrder> ksSaleOrderList = new ArrayList<>();
        KsSaleOrder ksSaleOrder = new KsSaleOrder();
        ksSaleOrder.FPriceUnitId = ksFMCommon;
        ksSaleOrder.FStockUnitID = ksFMCommon;
        ksSaleOrder.FUnitID = ksFMCommon;
        ksSaleOrder.FEntryID = "0";
        ksSaleOrder.F_OMS_OrderNo1 = omsPupuOrder.getOrderNo();
        ksSaleOrder.F_OMS_OrderIn = 1;
        KsFNCommon ksFNCommon8 = new KsFNCommon();
        ksFNCommon8.FNumber = omsPupuOrder.getMasterCode();
        ksSaleOrder.FMaterialId = ksFNCommon8;
        ksSaleOrder.FTAXRATE = null == omsPupuOrder.getTaxRate() ? "" : omsPupuOrder.getTaxRate().toString();

        if (StringUtil.isNullOrEmpty(omsPupuOrder.getSynchState()) || "0".equals(omsPupuOrder.getSynchState())) {
            ksSaleOrder.FQty = null == omsPupuOrder.getOrderQty() ? "" : omsPupuOrder.getOrderQty().toString();
        } else {
            //去exchange表获取数据
            ksSaleOrder.FQty = iPuPuOrderService.getExchangeQty(omsPupuOrder.getOrderNo());
            //2018-01-13新加
            ksModel.FDate = iPuPuOrderService.getCreateTime(omsPupuOrder.getOrderNo()).substring(0, 10);
        }

        if (null == omsPupuOrder.getOrderAmt() || null == omsPupuOrder.getOrderQty() ||
                "".equals(omsPupuOrder.getOrderAmt()) || "".equals(omsPupuOrder.getOrderQty())) {

            ksSaleOrder.FTaxPrice = "0";
        } else {

            ksSaleOrder.FTaxPrice = String.valueOf(omsPupuOrder.getOrderAmt().intValue() / omsPupuOrder.getOrderQty());
        }
        ksSaleOrder.FEntryTaxRate = null == omsPupuOrder.getTaxRate() ? "" : omsPupuOrder.getTaxRate().toString();
        ksSaleOrder.FBaseUnitQty = (null == omsPupuOrder.getOrderQty() ||
                "".equals(omsPupuOrder.getOrderQty().toString())) ? "" : omsPupuOrder.getOrderQty().toString();
        ksSaleOrder.FDiscountRate = "0";
        KsFNCommon ksFNCommon11 = new KsFNCommon();
        ksFNCommon11.FNumber = "400030";
        ksSaleOrder.FOwnerId = ksFNCommon11;

        //2018-12-31新加逻辑
        if ("0".equals(omsPupuOrder.getOrderAmt())) {
            ksSaleOrder.FIsFree = "1";
        } else {
            ksSaleOrder.FIsFree = "0";
        }


        ksSaleOrderList.add(ksSaleOrder);

        ksModel.FSaleOrderEntry = ksSaleOrderList;
        //拼写Json数据
        ksOrderAndDetail.Creator = "OMS";
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("");
        ksOrderAndDetail.NeedUpDateFields = (jsonArray);
        ksOrderAndDetail.Model = ksModel;

        return JsonUtils.DEFAULT.toJson(ksOrderAndDetail);
    }


    private boolean checkBodyNullOrEmpty(PuPuCodeInfo puPuCodeInfo) {
        for (String fieldName : NOT_NULL_EMPTY_FIELD) {
            Object val = ReflectUtil.invokeGetMethod(puPuCodeInfo.getClass(), puPuCodeInfo, fieldName);
            if (val == null || StringUtil.isNullOrEmpty(val.toString())) {
                return false;
            }
        }
        return true;
    }
}
