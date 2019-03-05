package com.nh.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.nh.oms.common.constant.SmsConstant;
import com.nh.oms.common.utils.DateUtil;
import com.nh.oms.common.utils.SmsUtil;
import com.nh.oms.config.Interceptor;
import com.nh.oms.model.oms.*;
import com.nh.oms.service.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jjxu on 2018/11/21.
 */
@Controller
@RequestMapping("/api/report")
public class ReportController {

    private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    IReportService reportService;

    @Autowired
    IResultService resultService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IWorkorderService workorderService;

    @PostMapping("getReport")
    @ResponseBody
    public String updateReport(@RequestBody String reportJson){
        JSONObject resultJson = new JSONObject(true);
        try{

            JSONObject datas=JSONObject.parseObject(reportJson);
            String OrderNo=datas.getString("orderNo");
            String sampleno=datas.getString("sampleno");
            String report_delivery_no= datas.getString("ReportDeliveryNo");
            String saleout_time=datas.getString("saleoutTime");
            if( OrderNo==null || sampleno==null
                    || report_delivery_no==null || saleout_time==null){
                resultJson.put("status", "4");
                resultJson.put("msg", "缺少参数!");

                return resultJson.toJSONString();
            }
            if(OrderNo.equals("") || sampleno.equals("")
                    || report_delivery_no.equals("") || saleout_time.equals("")){
                resultJson.put("status", "4");
                resultJson.put("msg", "缺少参数!");

                return resultJson.toJSONString();
            }

            OmsTestResult omsTestResult=resultService.selectBySampleno(sampleno);
            //如检测结果表里存在，则报告同步成功,且返回成功!
            if(omsTestResult!=null){
                resultJson.put("status", "0");
                resultJson.put("msg", "成功!");
                return resultJson.toJSONString();
            }

            String component_name=reportService.selectComponentName(OrderNo,sampleno);
            if(component_name==null){
                resultJson.put("status", "2");
                resultJson.put("msg", "该检测单号不存在!");
                return resultJson.toJSONString();
            }
            if(component_name.equals("")){
                resultJson.put("status", "2");
                resultJson.put("msg", "该检测单号不存在!");
                return resultJson.toJSONString();
            }

            logger.info("sampleno-----------:"+sampleno,"component_name--------:"+component_name);
            String TestJson=reportService.getReportByApi(sampleno,component_name);
            logger.info(TestJson);
            if(TestJson==null){
                resultJson.put("status", "3");
                resultJson.put("msg", "Lis系统报告存在未打印情况!");
                return resultJson.toJSONString();
            }
            if(TestJson.equals("")){
                resultJson.put("status", "3");
                resultJson.put("msg", "无法取到报告信息结果!");
                return resultJson.toJSONString();
            }

            OmsOrder omsOrder=orderService.getOmsOrderByOrderNo(OrderNo);
            if(omsOrder==null){
                resultJson.put("status", "3");
                resultJson.put("msg", "订单不存在!");
                return resultJson.toJSONString();
            }

            Testresult testObject=null;
            //插入报告和修改订单状态和相关信息
            if(component_name.contains("常卫清"))
            {
                testObject=JSONObject.parseObject(TestJson,Testresult.class);
                if(testObject==null){
                    resultJson.put("status", "3");
                    resultJson.put("msg", "Lis系统报告存在未打印情况!");
                }

                resultService.insertCwqResult(testObject);
                reportService.updateReportInfo(OrderNo,sampleno,report_delivery_no,
                        DateUtil.StringToDate(saleout_time), DateUtil.StringToDate(testObject.getChecktime()) );


                //创建工单 --需要报告解读 常卫清阴性不再解读
                if(omsOrder.getIsInterpretationReport().equals("1")){
                    if(!testObject.getStayhospitalmode().equals("2")) {
                        if (Integer.parseInt(testObject.getTestresult()) >= 165) {
                            insertWorderOrder(OrderNo, sampleno);
                        }
                    }
                }

                //在合格情况下,发送短信
                if(!testObject.getStayhospitalmode().equals("2")){
                    //更新检测样本质量信息
                    orderDetailService.updateDetectStateByOrderNo(OrderNo,"1");
                    //OmsOrder omsOrder=orderService.getOmsOrderByOrderNo(OrderNo);
                    if(omsOrder.getReportSms().equals("1")) {
                        sendReportSms(OrderNo, "常卫清");
                    }
                } else {
                    orderDetailService.updateDetectStateByOrderNo(OrderNo,"0");
                }


                resultJson.put("status", "0");
                resultJson.put("msg", "成功!");
                return resultJson.toJSONString();

            } else if(component_name.contains("常卫宁")){
                 testObject=JSONObject.parseObject(TestJson,Testresult.class);
                resultService.insertCwnResult(testObject);
                reportService.updateReportInfo(OrderNo,sampleno,report_delivery_no,
                        DateUtil.StringToDate(saleout_time), DateUtil.StringToDate(testObject.getChecktime()) );

                //创建工单 常卫宁需要解读创建工单
                if(omsOrder.getIsInterpretationReport().equals("1")){
                    insertWorderOrder(OrderNo,sampleno);
                }

                //在合格情况下,发送短信
                if(!testObject.getStayhospitalmode().equals("2")){
                    //更新检测样本质量信息
                    orderDetailService.updateDetectStateByOrderNo(OrderNo,"1");
                    //OmsOrder omsOrder=orderService.getOmsOrderByOrderNo(OrderNo);
                    if(omsOrder.getReportSms().equals("1")){
                        sendReportSms(OrderNo,"常卫宁");
                    }
                } else {
                    //更新检测样本质量信息
                    orderDetailService.updateDetectStateByOrderNo(OrderNo,"0");
                }

                resultJson.put("status", "0");
                resultJson.put("msg", "成功!");
                return resultJson.toJSONString();

            }  else if(component_name.contains("常卫明")){
                testObject=JSONObject.parseObject(TestJson,Testresult.class);
                resultService.insertCwmResult(testObject);
                reportService.updateReportInfo(OrderNo,sampleno,report_delivery_no,
                        DateUtil.StringToDate(saleout_time), DateUtil.StringToDate(testObject.getChecktime()) );

                       //创建工单 常卫明需要解读的创建工单
                    if(omsOrder.getIsInterpretationReport().equals("1")){
                        insertWorderOrder(OrderNo,sampleno);
                    }


                    //在合格情况下,发送短信
                    if(!testObject.getStayhospitalmode().equals("2")){
                        //更新检测样本质量信息
                        orderDetailService.updateDetectStateByOrderNo(OrderNo,"1");
                        //OmsOrder omsOrder=orderService.getOmsOrderByOrderNo(OrderNo);
                        if(omsOrder.getReportSms().equals("1")) {
                            sendReportSms(OrderNo, "常卫明");
                        }
                    } else {
                        //更新检测样本质量信息
                        orderDetailService.updateDetectStateByOrderNo(OrderNo,"0");
                    }

                    resultJson.put("status", "0");
                    resultJson.put("msg", "成功!");
                    return resultJson.toJSONString();
            } else if (component_name.contains("一次")
                    || component_name.contains("费证清")){
                //费证情况
                List<Testresult> testresultList= JSONArray.parseArray(TestJson,Testresult.class);
                resultService.insertFzqResult(testresultList);
                reportService.updateReportInfo(OrderNo,sampleno,report_delivery_no,
                        DateUtil.StringToDate(saleout_time), DateUtil.StringToDate(testresultList.get(0).getChecktime()) );

                //创建工单 常卫明需要解读的工单
                if(omsOrder.getIsInterpretationReport().equals("1")) {
                    insertWorderOrder(OrderNo, sampleno);
                }
                //在合格情况下,发送短信
                if(!testObject.getStayhospitalmode().equals("2")){
                    //更新检测样本质量信息
                    orderDetailService.updateDetectStateByOrderNo(OrderNo,"1");
                    //OmsOrder omsOrder=orderService.getOmsOrderByOrderNo(OrderNo);
                    if(omsOrder.getReportSms().equals("1")) {
                        sendReportSms(OrderNo, "费证清");
                    }
                } else {
                    //更新检测样本质量信息
                    orderDetailService.updateDetectStateByOrderNo(OrderNo,"0");
                }

                resultJson.put("status", "0");
                resultJson.put("msg", "成功!");
                return resultJson.toJSONString();
            }

            resultJson.put("status", "1");
            resultJson.put("msg", "程序有可能异常或报告获取失败!");
            return resultJson.toJSONString();

        }catch (Exception error){
            resultJson.put("status", "1");
            resultJson.put("msg", "程序有可能异常或报告获取失败！");
            error.printStackTrace();
            logger.info("报告错误原因:"+error.getMessage());
        }

        return resultJson.toJSONString();
    }

    private void sendReportSms(String order_no,String product_name){

            OmsOrder omsOrder=orderService.getOmsOrderByOrderNo(order_no);
            if(omsOrder.getReportIsPaper().equals("0")){
                //电子报告
                String[] args = new String[] {product_name};
                Boolean flag= SmsUtil.Send(omsOrder.getDetectedPhone(),args, SmsConstant.E_TEMPLATEID);
            } else{
                //纸质报告
                OmsOrderDetail omsOrderDetail=orderDetailService.getorderDetailService(omsOrder.getId());
                logger.info("短信发送:"+omsOrderDetail.getOrderNo());
                String[] args = new String[] {product_name,omsOrderDetail.getReportDeliveryNo()};
                Boolean flag= SmsUtil.Send(omsOrder.getReportReceiverPhone(),args, SmsConstant.P_TEMPLATEID);
                logger.info("短信发送:"+flag);
            }

    }

    private void insertWorderOrder(String order_no,String sampleno){
        //创建工单
        OmsWorkorder omsWorkorder=new OmsWorkorder();
        omsWorkorder.setOrderNo(order_no);
        omsWorkorder.setSampleno(sampleno);
        omsWorkorder.setSampleStatus("0");
        omsWorkorder.setStatus("11");  //待解读
        omsWorkorder.setWorkorderType("2"); //报告解读
        omsWorkorder.setSampleStatus("0");
        omsWorkorder.setCreateBy("报告接口");
        omsWorkorder.setCreateTime(new Date());
        workorderService.insert(omsWorkorder);
    }
}
