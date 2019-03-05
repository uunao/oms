package com.nh.oms.service.Imp;

import com.nh.oms.dao.erp.ErpOrderMapper;
import com.nh.oms.model.erp.ErpSaleOrder;
import com.nh.oms.service.IErpOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErpOrderServiceImpl implements IErpOrderService {

    @Autowired
    ErpOrderMapper erpOrderMapper;

    @Override
    public int updateErpOrder(List<ErpSaleOrder> erpSaleOrderList) {
        return erpOrderMapper.updateErpOrder(erpSaleOrderList);
    }
}
