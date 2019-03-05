package com.nh.oms.controller;

import com.nh.oms.model.oms.OmsOrderSyncException;
import com.nh.oms.service.IOmsOrderSynExpService;
import com.nh.oms.util.SystemHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ccWang on 2018/12/24.
 */

@RestController
@RequestMapping("api/exception")
public class SyncExceptionController {
    private static final Logger LOG = LoggerFactory.getLogger(PuPuController.class);

    @Autowired
    private IOmsOrderSynExpService iOmsOrderSynExpService;

    @RequestMapping(value = "/synExceptionInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void insertSyncException(@RequestBody OmsOrderSyncException omsOrderSyncException) {

        try {
            iOmsOrderSynExpService.insert(omsOrderSyncException.getOrderParentNo(), omsOrderSyncException.getOrderNo(), omsOrderSyncException.getExceptionMsg());
        } catch (Exception ex) {
            LOG.error("SyncExceptionController:insertSyncException,插入订单日志表时候出现错误,{}", ex.toString());
        }
    }
}
