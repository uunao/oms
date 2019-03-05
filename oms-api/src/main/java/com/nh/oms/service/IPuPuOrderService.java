package com.nh.oms.service;

import com.nh.oms.model.oms.OmsPupuOrder;

import java.util.List;

/**
 * Created by ccwang on 2018/11/20.
 */
public interface IPuPuOrderService {

    OmsPupuOrder getPuPuOrderInfo(String orderNo);

    int updateOrderSynState(String orderNo);

    int updateExchangeSynState(String orderNo);

    String getExchangeQty(String orderNo);

    String getCusCodeByChannel(String channelCode);

    String getEmpNoByUserId(String userId);

    String getCreateTime(String orderNo);

    String getAreaAddress(List<String> list);
}
