package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsPupuOrderMapper;
import com.nh.oms.model.oms.BaseAreaM;
import com.nh.oms.model.oms.OmsPupuOrder;
import com.nh.oms.service.IPuPuOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ccWang on 2018/11/20.
 */
@Service
public class PuPuOrderServiceImpl implements IPuPuOrderService {

    @Autowired
    private OmsPupuOrderMapper omsPupuOrderDao;


    @Override
    public OmsPupuOrder getPuPuOrderInfo(String orderNo) {

        OmsPupuOrder omsPupuOrder = omsPupuOrderDao.getInfoByOrderNo(orderNo);
        return omsPupuOrder;
    }

    @Override
    public int updateOrderSynState(String orderNo) {
        return omsPupuOrderDao.updateOrderSynState(orderNo);
    }

    @Override
    public int updateExchangeSynState(String orderNo) {
        return omsPupuOrderDao.updateExchangeSynState(orderNo);
    }

    @Override
    public String getExchangeQty(String orderNo) {
        return omsPupuOrderDao.getExchangeQty(orderNo);
    }

    @Override
    public String getCusCodeByChannel(String channelCode) {
        return omsPupuOrderDao.getCusCodeByChannel(channelCode);
    }

    @Override
    public String getEmpNoByUserId(String userId) {
        return omsPupuOrderDao.getEmpNoByUserId(userId);
    }

    @Override
    public String getCreateTime(String orderNo) {
        return omsPupuOrderDao.getCreateTime(orderNo);
    }

    @Override
    public String getAreaAddress(List<String> list) {
        String res = "";
        List<BaseAreaM> listArea = omsPupuOrderDao.getAreaAddress(list);
        for (int i = 0; i < listArea.size(); i++) {
            if (null != listArea.get(i)) {
                res = res + listArea.get(i).getAreaName();
            }
        }
        return res.trim();
    }
}
