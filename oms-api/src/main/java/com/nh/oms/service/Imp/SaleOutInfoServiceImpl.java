package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsPupuOrderMapper;
import com.nh.oms.dao.oms.OmsPupuSaleoutMapper;
import com.nh.oms.dao.oms.PupuDeliveryOrSaleoutDetailMapper;
import com.nh.oms.model.oms.OmsPupuOrder;
import com.nh.oms.model.oms.OmsPupuSaleout;
import com.nh.oms.model.oms.PupuDeliveryOrSaleoutDetail;
import com.nh.oms.service.ISaleoutInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Tsui
 * @Date: 2018-11-21
 * @Description:
 */
@Service
public class SaleOutInfoServiceImpl implements ISaleoutInfoService {

    @Resource
    private OmsPupuSaleoutMapper omsPupuSaleoutMapper;

    @Resource
    private OmsPupuOrderMapper omsPupuOrderMapper;

    @Resource
    private PupuDeliveryOrSaleoutDetailMapper pupuDeliveryOrSaleoutDetailMapper;

    /**
     *
     * @param pupuDeliveryOrSaleoutDetailList
     * @throws Exception
     */
    @Override
    public void insertSaleOut(List<PupuDeliveryOrSaleoutDetail> pupuDeliveryOrSaleoutDetailList) throws Exception {

        StringBuilder errorMsg = new StringBuilder();

        // 判断出库单号是否存在，存在则抛出异常
        for (PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail : pupuDeliveryOrSaleoutDetailList) {
            int i = pupuDeliveryOrSaleoutDetailMapper.isExist(pupuDeliveryOrSaleoutDetail);
            if (i > 0) {
                errorMsg.append("行号：").append(pupuDeliveryOrSaleoutDetail.getRowNum()).append(",").append("批次：")
                        .append(pupuDeliveryOrSaleoutDetail.getBatchNo()).append(",").append("出库单号：")
                        .append(pupuDeliveryOrSaleoutDetail.getNumber()).append("已存在");
                throw new Exception(errorMsg.toString());
            } else {
                pupuDeliveryOrSaleoutDetailMapper.insert(pupuDeliveryOrSaleoutDetail);

                int a = omsPupuSaleoutMapper.selectBySaleOutNo(pupuDeliveryOrSaleoutDetail.getNumber(),
                        pupuDeliveryOrSaleoutDetail.getOrderNo());
                OmsPupuSaleout omsPupuSaleout = new OmsPupuSaleout();
                omsPupuSaleout.setOrderNo(pupuDeliveryOrSaleoutDetail.getOrderNo());
                omsPupuSaleout.setSaleoutNo(pupuDeliveryOrSaleoutDetail.getNumber());
                omsPupuSaleout.setSaleoutQty(pupuDeliveryOrSaleoutDetail.getQuantity());
                omsPupuSaleout.setSaleoutTime(pupuDeliveryOrSaleoutDetail.getDeliveryTime());
                omsPupuSaleout.setExpressNo(pupuDeliveryOrSaleoutDetail.getDeliveryNo());
                omsPupuSaleout.setCreateTime(new Date());
                if (a == 0) {
                    omsPupuSaleoutMapper.insert(omsPupuSaleout);
                }else {
                    omsPupuSaleoutMapper.updateSaleOutQty(omsPupuSaleout.getOrderNo(), omsPupuSaleout.getSaleoutNo()
                            , omsPupuSaleout.getSaleoutQty());
                }


                OmsPupuOrder omsPupuOrder = new OmsPupuOrder();
                omsPupuOrder.setOrderNo(pupuDeliveryOrSaleoutDetail.getOrderNo());
                omsPupuOrder.setSaleoutQty(pupuDeliveryOrSaleoutDetail.getQuantity());
                omsPupuOrder.setOrderState("99");
                int partDeliveryAndPartSaleOut = omsPupuOrderMapper.partDeliveryAndPartSaleOut(omsPupuOrder.getOrderNo(),omsPupuOrder.getSaleoutQty());
                if (partDeliveryAndPartSaleOut == 1) {
                    omsPupuOrder.setOrderState("14");
                }
                int wholeDeliveryAndPartSaleOut = omsPupuOrderMapper.wholeDeliveryAndPartSaleOut(omsPupuOrder.getOrderNo(),omsPupuOrder.getSaleoutQty());
                if (wholeDeliveryAndPartSaleOut == 1) {
                    omsPupuOrder.setOrderState("18");
                }
                int wholeDelivery = omsPupuOrderMapper.wholeDelivery(omsPupuOrder.getOrderNo(), omsPupuOrder.getSaleoutQty());
                if (wholeDelivery == 1) {
                    omsPupuOrder.setOrderState("30");
                }
                omsPupuOrderMapper.updateSaleOutQty(omsPupuOrder);
                omsPupuOrderMapper.updateOrderState(omsPupuOrder);
            }
        }

    }

}
