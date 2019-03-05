package com.nh.oms.dao.erp;

import com.nh.oms.model.erp.ErpSaleOrder;

import java.util.List;

/**
 * @author Will
 * @date 2018/11/13
 */
public interface ErpOrderMapper {

    int updateErpOrder(List<ErpSaleOrder> erpSaleOrderList);
}
