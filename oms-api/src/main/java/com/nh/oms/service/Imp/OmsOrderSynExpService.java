package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsOrderSyncExceptionMapper;
import com.nh.oms.model.oms.OmsOrderSyncException;
import com.nh.oms.service.IOmsOrderSynExpService;
import com.nh.oms.util.SystemHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ccwang on 2018/11/14.
 */
@Service
public class OmsOrderSynExpService implements IOmsOrderSynExpService {

    @Autowired
    private OmsOrderSyncExceptionMapper omsOrderSyncExceptionDao;

    @Override
    public void insert(String orderParentNo, String orderNo, String msg) {
        OmsOrderSyncException omsOrderSyncException = new OmsOrderSyncException();
        omsOrderSyncException.setOrderParentNo(orderParentNo);
        omsOrderSyncException.setOrderNo(orderNo);
        omsOrderSyncException.setExceptionMsg(msg);
        omsOrderSyncException.setCreateTime(SystemHelper.getNowTimeString());
        omsOrderSyncExceptionDao.insert(omsOrderSyncException);
    }
}
