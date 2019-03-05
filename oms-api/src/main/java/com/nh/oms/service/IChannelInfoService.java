package com.nh.oms.service;

import com.nh.oms.model.oms.OmsChannel;
import com.nh.oms.model.oms.OmsChannelGroup;

import java.util.List;


/**
 * @Auther: Tsui
 * @Date: 2018-11-12
 * @Description:
 */
public interface IChannelInfoService {

    List<String> saveChannel(List<OmsChannelGroup> list) ;

    StringBuilder updateChannelGroupPid(List<OmsChannel> omsChannelList);
}
