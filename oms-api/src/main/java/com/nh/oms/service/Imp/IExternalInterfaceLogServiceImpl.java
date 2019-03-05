package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsExternalInterfaceLogMapper;
import com.nh.oms.model.oms.OmsExternalInterfaceLog;
import com.nh.oms.service.IExternalInterfaceLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: Tsui
 * @Date: 2019-01-18
 * @Description:
 */
@Service
public class IExternalInterfaceLogServiceImpl implements IExternalInterfaceLogService {

    @Resource
    private OmsExternalInterfaceLogMapper omsExternalInterfaceLogMapper;

    @Override
    public void saveExternalLog(OmsExternalInterfaceLog omsExternalInterfaceLog) {

        omsExternalInterfaceLogMapper.insert(omsExternalInterfaceLog);

    }
}
