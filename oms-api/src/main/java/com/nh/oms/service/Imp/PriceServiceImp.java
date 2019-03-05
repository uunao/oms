package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsPriceMapper;
import com.nh.oms.model.oms.OmsPrice;
import com.nh.oms.service.IPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jjxu on 2018/11/7.
 */
@Service
public class PriceServiceImp implements IPriceService {

    @Resource
    OmsPriceMapper omsPriceMapper;

    @Override
    public String insertSelective(List<OmsPrice> omsPrices) {

        //产品
        List<String> exists = new ArrayList<String>();
        //分组
        List<String> groupPidExists = new ArrayList<String>();
        //Id
        List<String> idsExists = new ArrayList<String>();

        List<String> pricesList = new ArrayList<String>();

        Set<String> pid = new HashSet<String>();
        Set<String> groupPid = new HashSet<String>();
        Set<String> ids = new HashSet<String>();

        List<OmsPrice> updateList = new ArrayList<OmsPrice>();

        for (OmsPrice price : omsPrices) {
            //优先级 1-渠道 ，2-渠道分组 ，3-物料。
            //如果等于渠道分组的需要判断分组是否存在
            pid.add(price.getProductCode());
            if (price.getPriceSort() == 2) {
                groupPid.add(price.getChannelGroupPid());
            }

            //校验产品是否存在
            //优先级 1-渠道 ，2-渠道分组 ，3-物料。
            if ((price.getPriceSort() == 1 ||price.getPriceSort() == 2)&& "".equals(price.getChannelGroupPid())) {
                pricesList.add(price.getProductCode());
            }

            ids.add(price.getId() + price.getChannelGroupPid());
        }
        //校验产品是否存在
        if (pid.size() > 0) {
            exists = omsPriceMapper.isExists(pid);
            pid.removeAll(exists);
        }

        //价格分组是否存在
        if (groupPid.size() > 0) {
            groupPidExists = omsPriceMapper.isGroupPidExists(groupPid);
            groupPid.removeAll(groupPidExists);
        }

        //判断id是否已经存在，存在做更新
        if (ids.size() > 0) {
            idsExists = omsPriceMapper.isIdExists(ids);
        }

        StringBuffer stringBuffer = new StringBuffer();
        if (pid.size() > 0) {
            stringBuffer.append(">>oms>>以下产品编码不存在：").append(String.join(",", pid));
        }
        if (groupPid.size() > 0) {
            stringBuffer.append("  >>oms>>以下渠道分组编码不存在：").append(String.join(",", groupPid));
        }

        if(pricesList.size()>0){
            stringBuffer.append("  >>oms>>以下物料,优先级如果为1和2时,channelGroupPid为必填：").append(String.join(",", pricesList));
        }

        if (stringBuffer.length() > 0) {
            return stringBuffer.toString();
        }


        if (idsExists.size() > 0) {
            for (OmsPrice price : omsPrices) {
                if (idsExists.contains(price.getId() + price.getChannelGroupPid())) {
                    updateList.add(price);
                }
            }
        }

        if (updateList.size() > 0) {
            omsPrices.removeAll(updateList);
            for (OmsPrice price : updateList) {
//                omsPriceMapper.updateByPrimaryKey(price);
                omsPriceMapper.updateByIdGroupId(price);
            }

        }

        if (omsPrices.size() > 0) {
            omsPriceMapper.batchInsert(omsPrices);
        }

        return "";
    }


}
