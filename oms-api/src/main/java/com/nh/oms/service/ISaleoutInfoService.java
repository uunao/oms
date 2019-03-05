package com.nh.oms.service;

import com.nh.oms.model.oms.PupuDeliveryOrSaleoutDetail;

import java.util.List;

/**
 * @Auther: Tsui
 * @Date: 2018-11-21
 * @Description:
 */
public interface ISaleoutInfoService {

    void insertSaleOut(List<PupuDeliveryOrSaleoutDetail> pupuDeliveryOrSaleoutDetailList) throws Exception;
}
