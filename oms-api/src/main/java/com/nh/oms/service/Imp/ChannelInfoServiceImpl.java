package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsChannelGroupMapper;
import com.nh.oms.dao.oms.OmsChannelMapper;
import com.nh.oms.model.oms.OmsChannel;
import com.nh.oms.model.oms.OmsChannelGroup;
import com.nh.oms.service.IChannelInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: Tsui
 * @Date: 2018-11-12
 * @Description:
 */
@Service
public class ChannelInfoServiceImpl implements IChannelInfoService {

    @Resource
    OmsChannelGroupMapper omsChannelGroupMapper;

    @Resource
    OmsChannelMapper omsChannelMapper;

    /**
     *
     * @return
     */
    @Override
    public List<String> saveChannel(List<OmsChannelGroup> list) {

        Set<String> pids = new HashSet<String>();
        List<OmsChannelGroup> insertList = new ArrayList<OmsChannelGroup>();

        //查询数据是否已经存在
        for(OmsChannelGroup channel : list){
            pids.add(channel.getPid());
        }
        List<String> exists = omsChannelGroupMapper.isExists(pids);

        //改成存在重复数据就返回
        if(exists.size()>0){
            return exists;
        }

//        //去掉存在的数据
//        for(OmsChannelGroup channel : list){
//           if(!exists.contains(channel.getPid())){
//               insertList.add(channel);
//           }
//        }

        //新增数据
        if(list.size()>0){
            omsChannelGroupMapper.batchInsert(list);

        }

        return exists;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StringBuilder updateChannelGroupPid(List<OmsChannel> omsChannelList) {
        // 渠道编码校验信息
        StringBuilder codeMsg = new StringBuilder();
        // 渠道编码Pid校验信息
        StringBuilder pidMsg = new StringBuilder();

        for (OmsChannel omsChannel : omsChannelList) {
            // 校验渠道编码Pid是否存在
            OmsChannelGroup omsChannelGroup = omsChannelGroupMapper.selectByPrimaryKey(omsChannel.getChannelGroupPid());
            // 校验渠道编码是否存在
            OmsChannel omsChannel1 = omsChannelMapper.selectByChannelCode(omsChannel.getChannelCode());
            if (omsChannelGroup == null) {
                pidMsg.append(omsChannel.getChannelGroupPid()).append(",");
            }

            if (omsChannel1 == null) {
                codeMsg.append(omsChannel.getChannelCode()).append(",");
            }
        }
        if (!pidMsg.toString().isEmpty()) {
            pidMsg.setLength(pidMsg.length()-1);
            pidMsg = new StringBuilder("渠道分组Pid:").append(pidMsg).append("不存在。");
        }
        if (!codeMsg.toString().isEmpty()) {
            codeMsg.setLength(codeMsg.length()-1);
            codeMsg = new StringBuilder("渠道编码:").append(codeMsg).append("不存在。");
        }

        StringBuilder msg = new StringBuilder().append(codeMsg).append(pidMsg);

        if (msg.length() == 0) {
            omsChannelMapper.updateListByChannelCode(omsChannelList);
        }

        return msg;
    }
}
