package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsOrderLog;

import java.util.List;

public interface OmsOrderLogMapper {

    void insertList(List<OmsOrderLog> omsOrderLogList);

    int insert(OmsOrderLog record);
}
