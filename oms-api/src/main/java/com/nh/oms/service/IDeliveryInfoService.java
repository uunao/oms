package com.nh.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.nh.oms.model.oms.OmsPupuDeliveryExt;

import java.util.List;

/**
 * @Auther: Tsui
 * @Date: 2018-11-21
 * @Description:
 */
public interface IDeliveryInfoService {

    /**
     * 保存噗噗管出库信息
     * @param deliverys
     * @return
     */
    String insertDelivery(List<OmsPupuDeliveryExt> deliverys ,List<JSONObject> omspupuList);

    /**
     * 更新调查问卷
     * @param omspupuList
     * @return
     */
    String updateOmsPupu(List<JSONObject> omspupuList);
}
