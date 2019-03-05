package com.nh.oms.service;

import com.nh.oms.model.oms.OmsPrice;

import java.util.List;
import java.util.Map;

/**
 * Created by jjxu on 2018/11/7.
 */
public interface IPriceService {

    String insertSelective(List<OmsPrice> omsPrices);
}
