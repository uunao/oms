package com.nh.oms.model.oms;

/**
 * @author Will
 */
public class OmsChannel {
    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道分组PID
     */
    private String channelGroupPid;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelGroupPid() {
        return channelGroupPid;
    }

    public void setChannelGroupPid(String channelGroupPid) {
        this.channelGroupPid = channelGroupPid;
    }
}
