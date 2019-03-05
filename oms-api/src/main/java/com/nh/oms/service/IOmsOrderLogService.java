package com.nh.oms.service;

import com.nh.oms.model.oms.OmsOrderLog;

import java.util.List;

/**
 * Created by ccwang on 2018/11/16.
 */
public interface IOmsOrderLogService {

    void insert(OmsOrderLog omsOrderLog);

    void insertList(List<OmsOrderLog> orderLogList);
}
