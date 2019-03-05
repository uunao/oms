package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsChannel;

import java.util.List;

/**
 * @author Will
 */
public interface OmsChannelMapper {
    void updateListByChannelCode(List<OmsChannel> omsChannelList);

    OmsChannel selectByChannelCode(String channelCode);
}
