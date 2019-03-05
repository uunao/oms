package com.nh.oms.service;

import com.nh.oms.model.oms.OmsOrder;
import com.nh.oms.transform.OrderReportInfo;

import java.util.List;

/**
 * Created by 2018/11/20.
 */
public interface IOrderService {

    List<OrderReportInfo> getOrderForTaiKang();

    int updatePolicyNo(String id, String policyNo);

    OmsOrder getOmsOrderByOrderNo(String orderno);

    int updateByPrimaryKeySelective(OmsOrder record);

}
