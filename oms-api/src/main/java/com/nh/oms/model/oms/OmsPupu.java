package com.nh.oms.model.oms;

import java.util.Date;

public class OmsPupu {

    private String pupuCode;

    private String channelCode;

    private String cPersonCode;

    private Date createTime;

    public String getcPersonCode() {
        return cPersonCode;
    }

    public void setcPersonCode(String cPersonCode) {
        this.cPersonCode = cPersonCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPupuCode() {
        return pupuCode;
    }

    public void setPupuCode(String pupuCode) {
        this.pupuCode = pupuCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}
