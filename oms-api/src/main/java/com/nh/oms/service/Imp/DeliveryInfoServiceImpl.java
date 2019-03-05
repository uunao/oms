package com.nh.oms.service.Imp;

import com.alibaba.fastjson.JSONObject;
import com.nh.oms.dao.oms.OmsPupuCodeMapper;
import com.nh.oms.dao.oms.OmsPupuDeliveryMapper;
import com.nh.oms.dao.oms.OmsPupuMapper;
import com.nh.oms.model.oms.OmsPupu;
import com.nh.oms.model.oms.OmsPupuCode;
import com.nh.oms.model.oms.OmsPupuDeliveryExt;
import com.nh.oms.service.IDeliveryInfoService;
import com.nh.oms.service.IOrderDetailService;
import com.nh.oms.service.IPupuDeliveryOrSaleoutDetailService;
import com.nh.oms.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Auther: Tsui
 * @Date: 2018-11-21
 * @Description:
 */
@Service
public class DeliveryInfoServiceImpl implements IDeliveryInfoService {

    @Resource
    private OmsPupuDeliveryMapper omsPupuDeliveryMapper;

    @Resource
    IOrderDetailService orderDetailService;

    @Autowired
    private OmsPupuMapper omsPupuMapper;

    @Resource
    private OmsPupuCodeMapper omsPupuCodeMapper;

    @Resource
    private IPupuDeliveryOrSaleoutDetailService pupuDeliveryOrSaleoutDetailService;

    /**
     * 噗噗管先插入到明细表，并把数量更新到pupu_code上
     *
     * @param deliverys
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertDelivery(List<OmsPupuDeliveryExt> deliverys, List<JSONObject> omspupuList) {

        OmsPupuDeliveryExt omsPupuDeliveryExt;
        List<OmsPupuDeliveryExt> notExists = new ArrayList<OmsPupuDeliveryExt>();

        //保存发货单对应的数量
        Map<String, OmsPupuDeliveryExt> deliveryQtyMap = new HashMap<String, OmsPupuDeliveryExt>();

        // 查询重复数据
        List<String> existsList = omsPupuDeliveryMapper.existsDeliveryNo(deliverys);

        //更新订单发货数量
        for (OmsPupuDeliveryExt delivery : deliverys) {
            String orderNo = delivery.getOrderNo();

            //查询噗噗管发货明细是否存在,根据订单号、k3行号、批次号、发货单号判断
            int count = pupuDeliveryOrSaleoutDetailService.checkDeliveryIsExists(delivery);

            //已经存在则不任何处理
            if (count == 0) {
                //插入到噗噗管明细表
                pupuDeliveryOrSaleoutDetailService.insertPupuDeliveryDetail(delivery);

                //汇总数量,最后一次性更新数量和状态
                if (deliveryQtyMap.containsKey(orderNo)) {
                    omsPupuDeliveryExt = deliveryQtyMap.get(orderNo);
                    omsPupuDeliveryExt.setDeliveryQty(omsPupuDeliveryExt.getDeliveryQty() + delivery.getDeliveryQty());
                    deliveryQtyMap.put(orderNo, omsPupuDeliveryExt);
                } else {

                    deliveryQtyMap.put(orderNo, delivery);
                }
            }
        }

        for (String key : deliveryQtyMap.keySet()) {
            OmsPupuDeliveryExt deliveryExt = deliveryQtyMap.get(key);
            if (!existsList.contains(deliveryExt.getDeliveryNo())) {
                //新增到pupu_code表中
                omsPupuDeliveryMapper.batchInsertDelivery(deliveryExt);
            }

            //更新oms_pupu_order数量及状态
            omsPupuDeliveryMapper.updateDeliveryQty(key, deliveryExt.getDeliveryQty());
            //更新oms_pupu_order订单状态
            omsPupuDeliveryMapper.updateDeliveryStatus(key);
        }

        if (omspupuList.size() > 0) {
            updateOmsPupuDelivery(omspupuList);
        }


        return "";
    }

    @Override
    public String updateOmsPupu(List<JSONObject> omspupuList) {
        List<String> errorList = new ArrayList<String>();
        OmsPupuCode omsPupuCode;
        for (JSONObject jsonObject : omspupuList) {
            OmsPupu omsPupu = new OmsPupu();
            String sampleno = jsonObject.get("sampleno").toString();
            String channelCode = jsonObject.get("channelCode").toString();
            omsPupu.setPupuCode(sampleno);
            omsPupu.setChannelCode(channelCode);
            omsPupu.setcPersonCode(channelCode);
            int i = omsPupuMapper.selectByPupuCode(omsPupu.getPupuCode());
            try {
                if (i == 0) {
                    omsPupuMapper.insert(omsPupu);
                } else if (i == 1) {
                    omsPupuMapper.updateByPupuCode(omsPupu);
                } else {
                    errorList.add(omsPupu.getPupuCode());
                }

                /**
                 *如果噗噗码为空需要插入oms_pupu_code表中
                 * 不管那些有PM开头的单号
                 */
                if (StringUtils.isEmpty(jsonObject.getString("sampleno").trim()) && jsonObject.get("orderNo") != null) {

                    String orderNo = jsonObject.get("orderNo").toString();
                    String boxDeliveryNo = jsonObject.get("boxDeliveryNo").toString();
                    String indexSort = jsonObject.get("indexSort").toString();
                    String batchNo = jsonObject.get("batchNo").toString();

                    omsPupuCode = new OmsPupuCode();
                    omsPupuCode.setId(String.valueOf(System.currentTimeMillis()));
                    omsPupuCode.setOrderNo(orderNo);
                    omsPupuCode.setDeliveryNo(boxDeliveryNo);
                    omsPupuCode.setRowNo(indexSort);
                    omsPupuCode.setBatchNo(batchNo);
                    omsPupuCode.setIsRefund("0");
                    omsPupuCode.setCreateTime(new Date());

                    //根据噗噗管订单号、行号、批次号查询数据是否存在
                    int count = omsPupuCodeMapper.findByNo(orderNo + indexSort + batchNo);
                    if (count == 0) {
                        omsPupuCodeMapper.insertOtcPupu(omsPupuCode);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (errorList.size() > 0) {
            return ".以下噗噗码存在重复数据：" + StringUtil.join(errorList, ",");
        }
        return "";
    }

    /**
     * PP的单据
     * 问卷调查
     *
     * @param omspupuList
     * @return
     */
    public String updateOmsPupuDelivery(List<JSONObject> omspupuList) {
        List<String> errorList = new ArrayList<String>();
        OmsPupuCode omsPupuCode;
        for (JSONObject jsonObject : omspupuList) {
            try {
                /**
                 *如果噗噗码为空需要插入oms_pupu_code表中
                 * 不管那些有PM开头的单号
                 */
                if (StringUtils.isEmpty(jsonObject.getString("sampleno").trim()) && jsonObject.get("orderNo") != null) {

                    String orderNo = jsonObject.get("orderNo").toString();
                    String boxDeliveryNo = jsonObject.get("boxDeliveryNo").toString();
                    String indexSort = jsonObject.get("indexSort").toString();
                    String batchNo = jsonObject.get("batchNo").toString();

                    omsPupuCode = new OmsPupuCode();
                    omsPupuCode.setId(String.valueOf(System.currentTimeMillis()));
                    omsPupuCode.setOrderNo(orderNo);
                    omsPupuCode.setDeliveryNo(boxDeliveryNo);
                    omsPupuCode.setRowNo(indexSort);
                    omsPupuCode.setBatchNo(batchNo);
                    omsPupuCode.setIsRefund("0");
                    omsPupuCode.setCreateTime(new Date());

                    //根据噗噗管订单号、行号、批次号查询数据是否存在
                    int count = omsPupuCodeMapper.findByNo(orderNo + indexSort + batchNo);
                    if (count == 0) {
                        omsPupuCodeMapper.insertOtcPupu(omsPupuCode);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (errorList.size() > 0) {
            return ".以下噗噗码存在重复数据：" + StringUtil.join(errorList, ",");
        }
        return "";
    }
}
