package com.nh.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.nh.oms.model.oms.OmsOrderDetail;

import java.util.List;

/**
 * @author will
 * @date 2018/11/06
 */
public interface IOrderDetailService {

    /**
     * @Description: 订单状态、传递样本签回情况、传递采集盒发货情况接口统一调用
     * @param omsOrderDetailList
     * @Author: Will
     * @CreateDate: 2018/11/20 13:34
     */
    void updateList(List<OmsOrderDetail> omsOrderDetailList, int rowState) throws Exception;

    void delivery(List<JSONObject> jsonObjectList) throws Exception;

    /**
     * 发货更新订单数量，发货单号，发货时间，检测单号
     * @param pmList
     */
    String updateSaleOut(List<OmsOrderDetail> pmList, List<JSONObject> omspupuList);

    //add by xujingjing at 2019-01-03 获取明细服务信息
    /**
     * 获取明细服务信息
     * @param order_id
     * @return
     */
    OmsOrderDetail getorderDetailService(String order_id);

    int updateDetectStateByOrderNo(String order_no,
                                    String state);

}
