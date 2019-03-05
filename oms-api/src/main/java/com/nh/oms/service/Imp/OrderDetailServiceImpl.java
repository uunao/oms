package com.nh.oms.service.Imp;

import com.alibaba.fastjson.JSONObject;
import com.nh.oms.config.Interceptor;
import com.nh.oms.dao.oms.*;
import com.nh.oms.model.oms.*;
import com.nh.oms.service.IDeliveryInfoService;
import com.nh.oms.service.IOrderDetailService;
import com.nh.oms.service.ISaleoutInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * 订单状态接口更新 服务层实现
 *
 * @author Will
 * @date 2018-11-6
 */
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(Interceptor.class);

    private final int batch = 50;
    @Autowired
    private OmsOrderDetailMapper omsOrderDetailMapper;
//
//    @Autowired
//    private OmsOrderLogMapper omsOrderLogMapper;

//    @Resource
//    private OmsOrderLogService omsOrderLogService;

    @Autowired
    private OmsOrderMapper omsOrderMapper;

    @Autowired
    private ISaleoutInfoService iSaleoutInfoService;

    @Autowired
    private OmsPupuMapper omsPupuMapper;

    @Autowired
    private OmsPupuOrderMapper omsPupuOrderMapper;

    @Autowired
    private OmsPupuCodeMapper omsPupuCodeMapper;

    @Autowired
    IDeliveryInfoService deliveryInfoService;

    @Resource
    private DataSourceTransactionManager transactionManager;

    @Override
    public OmsOrderDetail getorderDetailService(String order_id) {
        return omsOrderDetailMapper.getorderDetailService(order_id);
    }


    /**
     * @param omsOrderDetailList
     * @param rowState           状态码
     * @Description: 订单取消、传递样本签回情况、虚拟收入确认
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateList(List<OmsOrderDetail> omsOrderDetailList, int rowState) throws Exception {

        List<OmsOrderLog> orderLogList = new ArrayList<>();

        List<OmsOrder> omsOrderList = new ArrayList<>();

        for (ListIterator<OmsOrderDetail> listIterator = omsOrderDetailList.listIterator(); listIterator.hasNext(); ) {
            OmsOrderDetail omsOrderDetail = listIterator.next();

            omsOrderDetail.setRowState(rowState);
            String parentOrderNo = omsOrderDetailMapper.selectParentOrderNo(omsOrderDetail.getOrderNo());
            OmsOrderLog omsOrderLog = new OmsOrderLog(omsOrderDetail.getOrderNo());
            omsOrderLog.setOrderParentNo(parentOrderNo);
            omsOrderLog.setOperatorType(String.valueOf(rowState));
            switch (rowState) {
                case 20:
                    omsOrderLog.setRemark("虚拟收入确认");
                    break;
                case 21:
                    omsOrderLog.setRemark("签回操作");
                    // 签回同步过来的是服务行或者套餐，如果是服务行，则要去查找相应的盒子行并更新，如果是套餐就直接更新
                    OmsOrderDetail boxLine = omsOrderDetailMapper.selectByOrderNo(omsOrderDetail.getOrderNo(), "1");
                    // 如果不为空则表示传过来的是服务行，要更新相应的盒子行
                    if (boxLine != null) {
                        boxLine.setObjectType("1");
                        boxLine.setBackTime(omsOrderDetail.getBackTime());
                        boxLine.setBackDeliveryNo(omsOrderDetail.getBackDeliveryNo());
                        boxLine.setBackQuality(omsOrderDetail.getBackQuality());
                        boxLine.setUnqualifiedCause(omsOrderDetail.getUnqualifiedCause());
                        listIterator.add(boxLine);
                    }
                    OmsOrder omsOrder1 = new OmsOrder();
                    omsOrder1.setOrderNo(omsOrderDetail.getOrderNo());
                    omsOrder1.setOrderState(rowState + "");
                    omsOrderList.add(omsOrder1);
                    break;
                case -1:
                    /*
                        判断是噗噗管订单还是常氏订单。
                        由于项目前期没有把噗噗管订单取消接口梳理出来，导致前期只做了常氏订单取消，统一用了OmsOrderDetail这个对象
                        来接收参数，理论上是要用JSONObject来接收，不过好在这两个接口的参数名称是一样的都是orderNo，所以将错就错
                        把OmsOrderDetail中的orderNo取出来判断一下就行了。
                     */
                    if ("PM".equals(omsOrderDetail.getOrderNo().substring(0, 2))) {
                        // 取消时K3传过来盒子行，在OMS中把服务行同时也改成取消状态
                        OmsOrderDetail servingLine = omsOrderDetailMapper.selectByOrderNo(omsOrderDetail.getOrderNo()
                                , "2");
                        if (servingLine != null) {
                            servingLine.setRowState(rowState);
                            listIterator.add(servingLine);
                        }
                        OmsOrder omsOrder = new OmsOrder();
                        omsOrder.setOrderNo(omsOrderDetail.getOrderNo());
                        omsOrder.setOrderState(rowState + "");
                        omsOrderList.add(omsOrder);
                    } else {
                        OmsPupuOrder omsPupuOrder = new OmsPupuOrder();
                        omsPupuOrder.setOrderNo(omsOrderDetail.getOrderNo());
                        omsPupuOrder.setOrderState("-1");
                        omsPupuOrderMapper.updateOrderState(omsPupuOrder);
                        listIterator.remove();
                    }
                    omsOrderLog.setRemark("取消");
                    break;
                default:
            }
            orderLogList.add(omsOrderLog);
            // 判断是否包含实验室信息，有则更新oms_order表
            if (omsOrderDetail.getLaboratory() != null && !omsOrderDetail.getLaboratory().isEmpty()) {
                OmsOrder omsOrder = new OmsOrder();
                omsOrder.setLaboratory(omsOrderDetail.getLaboratory());
                omsOrder.setOrderNo(omsOrderDetail.getOrderNo());
                omsOrderList.add(omsOrder);
            }
        }
        // 插入日志
//        omsOrderLogService.insertList(orderLogList);
        // 更新order表中的实验室信息字段

        if (omsOrderList.size() > 0) {
            for (int i=1; i<=(omsOrderList.size()+batch-1)/batch; i++) {
                int toIndex = i * batch > omsOrderList.size() ? omsOrderList.size() : i * batch;
                List<OmsOrder> tempOrderList =  omsOrderList.subList((i-1)*batch, toIndex);
                omsOrderMapper.updateByOrderNo(tempOrderList);
            }
        }
        // 更新订单信息
        if (omsOrderDetailList.size() > 0) {
            for (int i=1; i<=(omsOrderDetailList.size()+batch-1)/batch; i++) {
                int toIndex = i * batch > omsOrderDetailList.size() ? omsOrderDetailList.size() : i * batch;
                List<OmsOrderDetail> tempList = omsOrderDetailList.subList((i-1)*batch, toIndex);
                int updateList = omsOrderDetailMapper.updateList(tempList);
                if (updateList != 1) {
                    throw new Exception("请检查单号行号是否正确");
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delivery(List<JSONObject> jsonObjectList) throws Exception {

        List<OmsOrderLog> orderLogList = new ArrayList<>();

        List<PupuDeliveryOrSaleoutDetail> pupuDeliveryOrSaleoutDetailList = new ArrayList<>();

        List<OmsOrderDetail> omsOrderDetailList = new ArrayList<>();

        List<OmsOrder> omsOrderList = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");

        for (JSONObject jsonObject : jsonObjectList) {
            if (jsonObject.get("orderNo") == null || "".equals(jsonObject.get("orderNo").toString())) {
                throw new Exception("单号不能为空");
            } else if (jsonObject.get("saleoutNo") == null || "".equals(jsonObject.get("saleoutNo").toString())) {
                throw new Exception("出库单号不能为空");
            } else {
                String orderType = jsonObject.get("orderNo").toString().substring(0, 2);
                // 判断订单号前两个字母，如果为"PP"则走噗噗管出库，如果为"PM"则走盒子出库
                if ("PP".equals(orderType)) {
                    PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail = new PupuDeliveryOrSaleoutDetail();
                    // 订单号
                    pupuDeliveryOrSaleoutDetail.setOrderNo(jsonObject.get("orderNo").toString());
                    // 出库单号
                    pupuDeliveryOrSaleoutDetail.setNumber(jsonObject.get("saleoutNo").toString());
                    // 出库数量
                    pupuDeliveryOrSaleoutDetail.setQuantity(BigDecimal.valueOf(Double.valueOf(jsonObject.get(
                            "saleoutQty").toString())));
                    // 出库时间
                    pupuDeliveryOrSaleoutDetail.setDeliveryTime(simpleDateFormat.parse(jsonObject.get("saleoutTime").toString()));
                    // 噗噗码
                    pupuDeliveryOrSaleoutDetail.setSampleNo(jsonObject.get("sampleno").toString());
                    // K3行号
                    pupuDeliveryOrSaleoutDetail.setRowNum(jsonObject.get("rowNum").toString());
                    // 批次号
                    pupuDeliveryOrSaleoutDetail.setBatchNo(jsonObject.get("batchNo").toString());
                    // 失效时间
                    pupuDeliveryOrSaleoutDetail.setDeadTime(simpleDateFormat.parse(jsonObject.get("deadTime").toString()));
                    // 生产日期
                    String manufactureTime = jsonObject.get("manufactureTime").toString();
                    if (!manufactureTime.isEmpty()) {
                        pupuDeliveryOrSaleoutDetail.setManufactureTime(simpleDateFormat.parse(manufactureTime));
                    }
                    // 类型
                    pupuDeliveryOrSaleoutDetail.setType(2);
                    // 渠道编码
                    pupuDeliveryOrSaleoutDetail.setChannelCode(jsonObject.get("channelCode").toString());
                    // 物料类型
                    pupuDeliveryOrSaleoutDetail.setObjectType(jsonObject.get("objectType").toString());
                    // K3仓储位置
                    pupuDeliveryOrSaleoutDetail.setPostitions(jsonObject.get("positions").toString());
                    // 快递单号
                    pupuDeliveryOrSaleoutDetail.setDeliveryNo(jsonObject.get("deliveryNo").toString());
                    pupuDeliveryOrSaleoutDetail.setCreateTime(new Date());
                    pupuDeliveryOrSaleoutDetailList.add(pupuDeliveryOrSaleoutDetail);

                    if (StringUtils.isBlank(jsonObject.get("sampleno").toString())) {
                        OmsPupuCode omsPupuCode = new OmsPupuCode();
                        omsPupuCode.setOrderNo(jsonObject.get("orderNo").toString());
                        omsPupuCode.setSaleoutNo(jsonObject.get("saleoutNo").toString());
                        omsPupuCode.setRowNo(jsonObject.get("rowNum").toString());
                        omsPupuCode.setBatchNo(jsonObject.get("batchNo").toString());
                        omsPupuCodeMapper.updateByOrderNo(omsPupuCode);
                    }

                } else if ("PM".equals(orderType)) {
                    OmsOrderDetail omsOrderDetail = new OmsOrderDetail();
                    omsOrderDetail.setBatchNo(jsonObject.get("batchNo").toString());
                    // 判断是产品类型是否为1:盒子，如果是盒子，则更新相应的服务行
                    if ((Integer) jsonObject.get("objectType") == 1) {
                        OmsOrderDetail serving = new OmsOrderDetail();
                        serving.setBatchNo(omsOrderDetail.getBatchNo());
                        serving.setOrderNo(jsonObject.get("orderNo").toString());
                        serving.setSampleno(jsonObject.get("sampleno").toString());
                        serving.setRowState(11);
                        serving.setSendBoxTime(simpleDateFormat.parse(jsonObject.get("saleoutTime").toString()));
                        serving.setBoxDeliveryNo(jsonObject.get("deliveryNo").toString());
                        omsOrderDetailMapper.updateServingLine(serving);
                    } /*else if ((Integer) jsonObject.get("objectType") == 3) {
                        modifyOmsPupu(jsonObject);
                        OmsPupuCode omsPupuCode = new OmsPupuCode();
                        omsPupuCode.setOrderNo(jsonObject.get("orderNo").toString());
                        omsPupuCode.setBatchNo(jsonObject.get("batchNo").toString());
                        omsPupuCode.setSaleoutNo(jsonObject.get("deliveryNo").toString());
                        omsPupuCode.setPupuCode(jsonObject.get("sampleno").toString());
                        omsPupuCode.setId(String.valueOf(System.currentTimeMillis()));
                        omsPupuCode.setCreateTime(new Date());
                        omsPupuCode.setRowNo(jsonObject.get("indexSort").toString());
                        omsPupuCodeMapper.insertSelective(omsPupuCode);
                    }*/
                    // 订单号
                    omsOrderDetail.setOrderNo(jsonObject.get("orderNo").toString());
                    // 订单明细行号
                    omsOrderDetail.setIndexSort((Integer) jsonObject.get("indexSort"));
                    // 快递单号
                    omsOrderDetail.setBoxDeliveryNo(jsonObject.get("deliveryNo").toString());
                    // 出库时间
                    omsOrderDetail.setSaleOutTime(simpleDateFormat.parse(jsonObject.get("saleoutTime").toString()));
                    // 检测单号
                    omsOrderDetail.setSampleno(jsonObject.get("sampleno").toString());
                    // 状态
                    omsOrderDetail.setRowState(30);
                    // 失效日期
                    omsOrderDetail.setDeadTime(simpleDateFormat.parse(jsonObject.get("deadTime").toString()));
                    // 生产日期
                    String manufactureTime = jsonObject.get("manufactureTime").toString();
                    if (!manufactureTime.isEmpty()) {
                        omsOrderDetail.setManufactureTime(simpleDateFormat.parse(manufactureTime));
                    }
                    omsOrderDetailList.add(omsOrderDetail);

                    // 插入日志表
                    OmsOrderLog omsOrderLog = new OmsOrderLog(omsOrderDetail.getOrderNo());
                    String parentOrderNo = omsOrderDetailMapper.selectParentOrderNo(omsOrderDetail.getOrderNo());
                    omsOrderLog.setOrderParentNo(parentOrderNo);
                    omsOrderLog.setOperatorType("30");
                    omsOrderLog.setRemark("交易完成");
                    orderLogList.add(omsOrderLog);

                    // 将订单主表状态更新为已发盒待签回
                    OmsOrder omsOrder = new OmsOrder();
                    omsOrder.setOrderNo(jsonObject.get("orderNo").toString());
                    omsOrder.setOrderState("11");
                    omsOrderList.add(omsOrder);
                } else {
                    throw new Exception("单号错误");
                }
            }
        }
        // 更新订单主表状态
        if (omsOrderList.size() > 0) {
            for (int i=1; i<=(omsOrderList.size()+batch-1)/batch; i++) {
                int toIndex = i * batch > omsOrderList.size() ? omsOrderList.size() : i * batch;
                List<OmsOrder> tempOrderList =  omsOrderList.subList((i-1)*batch, toIndex);
                omsOrderMapper.updateByOrderNo(tempOrderList);
            }
        }
        // 插入噗噗管出库信息，并更新噗噗管主表订单状态和出库数量
        iSaleoutInfoService.insertSaleOut(pupuDeliveryOrSaleoutDetailList);
        // 插入日志
//        omsOrderLogService.insertList(orderLogList);
        // 更新盒子订单

        if (omsOrderDetailList.size() > 0) {
            for (int i=1; i<=(omsOrderDetailList.size()+batch-1)/batch; i++) {
                int toIndex = i * batch > omsOrderDetailList.size() ? omsOrderDetailList.size() : i * batch;
                List<OmsOrderDetail> tempList = omsOrderDetailList.subList((i-1)*batch, toIndex);
                int updateList = omsOrderDetailMapper.updateList(tempList);
                if (updateList != 1) {
                    throw new Exception("请检查单号行号是否正确");
                }
            }
        }
    }

    /**
     * 更新噗噗管问卷表和噗噗码记录表
     */
    public void modifyOmsPupu(JSONObject jsonObject) throws Exception {

        OmsPupu omsPupu = new OmsPupu();
        omsPupu.setPupuCode(jsonObject.get("sampleno").toString());
        omsPupu.setChannelCode(jsonObject.get("channelCode").toString());
        omsPupu.setcPersonCode(omsPupu.getChannelCode());
        omsPupu.setCreateTime(new Date());
        int i = omsPupuMapper.selectByPupuCode(omsPupu.getPupuCode());
        if (i == 0) {
            omsPupuMapper.insert(omsPupu);
        } else if (i == 1) {
            omsPupuMapper.updateByPupuCode(omsPupu);
        } else {
            throw new Exception("噗噗码" + omsPupu.getPupuCode() + "重复，请通知OMS人员处理问题");
        }
    }

    /**
     * @param omsOrderDetailList PM订单
     * @param omspupuList        PM单中 噗噗管 -用来更新问卷调查
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateSaleOut(List<OmsOrderDetail> omsOrderDetailList, List<JSONObject> omspupuList) {

        List<OmsOrderDetail> omsOrderDetailListRes = new ArrayList<OmsOrderDetail>();

//        List<OmsOrderLog> orderLogList = new ArrayList<>();

//        OmsOrder omsOrder;
//        for (OmsOrderDetail omsOrderDetail : omsOrderDetailList) {
//            omsOrder = new OmsOrder();
//            omsOrder.setOrderNo(omsOrderDetail.getOrderNo());
//
//            String parentOrderNo = omsOrderDetailMapper.selectParentOrderNo(omsOrderDetail.getOrderNo());
//
//            OmsOrderLog omsOrderLog = new OmsOrderLog(omsOrderDetail.getOrderNo());
//            omsOrderLog.setOrderParentNo(parentOrderNo);
//            omsOrderLog.setOperatorType(omsOrderDetail.getRowState().toString());
//            switch (omsOrderDetail.getRowState()) {
//                case 11:
//                    omsOrderLog.setRemark("发货操作");
//                    break;
//                case 21:
//                    omsOrderLog.setRemark("签回操作");
//                    break;
//                case 30:
//                    omsOrderLog.setRemark("交易完成");
//                    break;
//                case -1:
//                    omsOrderLog.setRemark("取消");
//                    break;
//                default:
//            }
//            orderLogList.add(omsOrderLog);
//
//        }
//        omsOrderLogService.insertList(orderLogList);

        int num = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        int ceil = (new Double(Math.ceil(omsOrderDetailList.size() / 100.0))).intValue();
        int size = 50;

        TransactionStatus status = null;
        try {
            while (num < ceil) {
                if (num == (ceil - 1)) {
                    size = omsOrderDetailList.size();
                } else {
                    size = (num + 1) * 100;
                }
//                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
//                status = transactionManager.getTransaction(def); // 获得事务状态
                omsOrderDetailListRes = omsOrderDetailList.subList(num * 100, size);


                /**
                 * 加入判断 是否虚拟交易
                 * 如果明细行状态为30已经结束的订单，不允许在更新，跳过所有的更新
                 */

                //更新明细表状态不包含服务行
                int i = omsOrderDetailMapper.updateSaleOutOrderDetail(omsOrderDetailListRes);

                if(i>0){
                    //更新明细表服务行的状态
                    omsOrderDetailMapper.updateSaleOutOrder(omsOrderDetailListRes);

                    //更新订单主表状态
                    omsOrderMapper.updateOrderStateByOrderNo(omsOrderDetailListRes);
                }else{
                    logger.error("发货异常：该订单状态已经为30,数据【{}】",JSONObject.toJSONString(omsOrderDetailListRes));
                }
//                transactionManager.commit(status);

                //噗噗管时更新问卷调查
                if (omspupuList.size() > 0) {
                    deliveryInfoService.updateOmsPupu(omspupuList);
                }

                num++;
            }

        } catch (Exception e) {
//            if(status != null){
//                transactionManager.rollback(status);
//            }
        }
        return "";
    }

    @Override
    public int updateDetectStateByOrderNo(String order_no,
                                          String state) {
        return omsOrderDetailMapper.updateDetectStateByOrderNo(order_no,
                state);
    }

}
