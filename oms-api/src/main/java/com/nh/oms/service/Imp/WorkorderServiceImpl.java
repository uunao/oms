package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsWorkorderMapper;
import com.nh.oms.model.oms.OmsWorkorder;
import com.nh.oms.service.IWorkorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jjxu on 2019/1/3.
 */
@Service
public class WorkorderServiceImpl implements IWorkorderService {

    @Autowired
    OmsWorkorderMapper omsWorkorderMapper;

    public int insert(OmsWorkorder record){
        return  omsWorkorderMapper.insertSelective(record);
    }
}
