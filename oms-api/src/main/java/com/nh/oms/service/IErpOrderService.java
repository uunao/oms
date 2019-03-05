package com.nh.oms.service;

import com.nh.oms.model.erp.ErpSaleOrder;

import java.util.List;

/**
 * @author Will
 * @date 2018/11/13
 */
public interface IErpOrderService {

    int updateErpOrder(List<ErpSaleOrder> erpSaleOrderList);
}
