package com.nh.oms.controller;

import com.nh.oms.model.erp.ErpSaleOrder;
import com.nh.oms.service.IErpOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author Will
 * @date 2018/11/13
 */
@RestController
@RequestMapping("api/erpOrder")
public class ErpOrderController {

    @Autowired
    private IErpOrderService iErpOrderService;

    @PostMapping
    public HashMap<String, Object> updateRowState(@RequestBody List<ErpSaleOrder> erpSaleOrderList) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            iErpOrderService.updateErpOrder(erpSaleOrderList);
            map.put("status",0);
            map.put("msg","ERP订单更新成功!");
        }catch (Exception error) {
            map.put("status",1);
            map.put("msg","错误原因"+error.getMessage());
        }
        return map;
    }

}
