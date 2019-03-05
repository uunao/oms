package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsOrderMapper;
import com.nh.oms.model.oms.OmsOrder;
import com.nh.oms.model.oms.OmsOrderDetail;
import com.nh.oms.service.IOrderService;
import com.nh.oms.transform.OrderReportInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 2018/11/20.
 */
@Service
public class OrderServiceImp implements IOrderService {

    @Autowired
    OmsOrderMapper OmsOrderDao;

    @Override
    public List<OrderReportInfo> getOrderForTaiKang() {

        return OmsOrderDao.getOrderForTaiKang();

    }

    @Override
    public int updatePolicyNo(String id, String policyNo) {
        return OmsOrderDao.updatePolicyNoById(id, policyNo);
    }

    public OmsOrder getOmsOrderByOrderNo(String orderno){
        return OmsOrderDao.getOmsOrderByOrderNo(orderno);
    }

    public int updateByPrimaryKeySelective(OmsOrder record){
        return OmsOrderDao.updateByPrimaryKeySelective(record);
    }

}
