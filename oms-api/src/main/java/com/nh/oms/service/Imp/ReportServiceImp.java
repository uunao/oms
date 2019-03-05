package com.nh.oms.service.Imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nh.oms.common.constant.ReportConstant;
import com.nh.oms.common.utils.HttpUtils;
import com.nh.oms.config.Interceptor;
import com.nh.oms.dao.oms.OmsOrderDetailMapper;
import com.nh.oms.dao.oms.OmsOrderMapper;
import com.nh.oms.model.oms.OmsOrder;
import com.nh.oms.model.oms.Testresult;
import com.nh.oms.service.IReportService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jjxu on 2018/11/21.
 */
@Service
public class ReportServiceImp implements IReportService {

    private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    OmsOrderDetailMapper omsOrderDetailDao;

    @Autowired
    OmsOrderMapper omsOrderMapper;

    public String selectComponentName(String order_no,
                                      String sampleno) {
        return omsOrderDetailDao.selectComponentName(order_no, sampleno);
    }

    @Transactional
    public int updateReportInfo(String order_no,
                                String sampleno,
                                String report_delivery_no,
                                Date saleout_time,
                                Date report_time) {

        Map<String, Object> reportMap = new HashedMap();
        reportMap.put("order_no", order_no);
        reportMap.put("sampleno", sampleno);
        reportMap.put("report_delivery_no", report_delivery_no);
        reportMap.put("salout_time", saleout_time);
        reportMap.put("report_time", report_time);
        omsOrderMapper.updateReportStateByOrderNo(order_no);
        return omsOrderDetailDao.updateReportInfo(reportMap);

    }

    @Override
    public String getReportByApi(String sampleno, String component_name) {

        String reportJs = "";
        String pJson = "";
        Map<String, String> reportPara = null;

        try{
            if (component_name.contains("常卫清")) {

                reportPara = new HashMap<String, String>();
                reportPara.put("SAMPLENO", sampleno);
                reportPara.put("product_name", "1"); //此post到.net端，产生乱码，故改成数字替代
                pJson = JSON.toJSONString(reportPara);

                String str = "ReportConstant.reportHost-----:" +ReportConstant.reportHost+ ", ReportConstant.CSCMD:--------"+
                        ReportConstant.CSCMD + ",json:------" + pJson;
                logger.info(str);

                reportJs = HttpUtils.sendPost(ReportConstant.reportHost + ReportConstant.CSCMD, pJson);
                logger.info("reportJs:------", reportJs);
            } else if (component_name.contains("常卫宁")) {
                reportPara = new HashMap<String, String>();
                reportPara.put("SAMPLENO", sampleno);
                reportPara.put("product_name", "2");
                pJson = JSON.toJSONString(reportPara);
                reportJs = HttpUtils.sendPost(ReportConstant.reportHost + ReportConstant.CSCMD, pJson);

            } else if (component_name.contains("常卫明")) {
                reportPara = new HashMap<String, String>();
                reportPara.put("SAMPLENO", sampleno);
                reportPara.put("product_name", "3");
                pJson = JSON.toJSONString(reportPara);
                reportJs = HttpUtils.sendPost(ReportConstant.reportHost + ReportConstant.CSCMD, pJson);

            } else if (component_name.contains("费证清") || component_name.contains("一次性")) {
                reportPara = new HashMap<String, String>();
                reportPara.put("SAMPLENO", sampleno);
                pJson = JSON.toJSONString(reportPara);
                reportJs = HttpUtils.sendPost(ReportConstant.reportHost + ReportConstant.FZQCMD, pJson);
            }

        }catch (Exception erro){
            reportJs="";
        }

        return reportJs;
    }


//    public int insertResult(Testresult testresult,String product_name){
//
//        if(product_name.contains("常卫清")){
//
//        }
//
//    }

}
