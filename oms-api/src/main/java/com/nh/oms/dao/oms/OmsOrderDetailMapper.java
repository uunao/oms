package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author will
 * @date 2018/11/06
 */
public interface OmsOrderDetailMapper {

    /**
     * 根据子单号查询母单号
     * @param orderNo 子单号
     * @return 母丹编号
     */
    String selectParentOrderNo(String orderNo);

    /**
     * 订单状态、传递样本签回情况、传递采集盒发货情况接口统一调用
     * @param omsOrderDetailList
     * @return
     */
    int updateList(List<OmsOrderDetail> omsOrderDetailList);

    //int updateSampleno(List<OmsOrderDetail> omsOrderDetailList);

    String selectComponentName(@Param("order_no") String order_no,
                               @Param("sampleno") String sampleno);

//    int updateReportInfo(@Param("order_no") String order_no,
//                         @Param("sampleno") String sampleno,
//                         @Param("report_delivery_no") String report_delivery_no,
//                         @Param("salout_time") Date  saleout_time,
//                         @Param("report_time") Date report_time);


    //add by xujingjing at 2018-11-23
    int updateReportInfo(Map<String,Object> ms);



    /**
     * 更新发货信息
     * @param omsOrderList
     */
    int updateSaleOutOrderDetail(List<OmsOrderDetail> omsOrderList);

    /**
     * 更新服务的检测单号
     * @param omsOrderDetailList
     */
    void updateSaleOutOrder(List<OmsOrderDetail> omsOrderDetailList);

    /**
     * 更新服务行检测单号和状态
     * @param omsOrderDetail
     */
    void updateServingLine(OmsOrderDetail omsOrderDetail);

    /**
     * 根据orderNo查询rowState大于0的盒子行
     * @param orderNo 订单号
     * @param objectType 物料类型
     */
    OmsOrderDetail selectByOrderNo(@Param("orderNo")String orderNo, @Param("objectType")String objectType);

    OmsOrderDetail getorderDetailService(String order_id);

    int updateDetectStateByOrderNo(@Param("orderNo") String orderNo,
                                   @Param("state") String state);
}