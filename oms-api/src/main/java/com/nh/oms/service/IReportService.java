package com.nh.oms.service;

import com.nh.oms.model.oms.Testresult;

import java.util.Date;

/**
 * Created by jjxu on 2018/11/21.
 */
public interface IReportService {

    String selectComponentName(String order_no,
                               String sampleno);

    int updateReportInfo(String order_no,
                         String sampleno,
                          String report_delivery_no,
                          Date saleout_time,
                         Date report_time
    );

    String getReportByApi(String sampleno,String component_name);


}
