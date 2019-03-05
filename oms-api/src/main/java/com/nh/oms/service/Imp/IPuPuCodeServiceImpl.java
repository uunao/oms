package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsPupuCodeMapper;
import com.nh.oms.dao.oms.OmsPupuMapper;
import com.nh.oms.dao.oms.OmsPupuOrderMapper;
import com.nh.oms.model.oms.OmsPupu;
import com.nh.oms.model.oms.OmsPupuCode;
import com.nh.oms.service.IPuPuCodeService;
import com.nh.oms.transform.PuPuCode;
import com.nh.oms.transform.PuPuCodeInfo;
import com.nh.oms.util.SystemHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccwang on 2018/11/23.
 */
@Service
public class IPuPuCodeServiceImpl implements IPuPuCodeService {

    @Autowired
    private OmsPupuCodeMapper omsPupuCodeDao;

    @Autowired
    private OmsPupuOrderMapper omsPupuOrderDao;

    @Autowired
    private OmsPupuMapper omsPupuDao;

    @Transactional(rollbackFor = Exception.class)
    public synchronized int bulkInsertPuPuCodeInfo(PuPuCodeInfo puPuCodeInfo) {

        String billNo = puPuCodeInfo.getK3BillNo();
        String billRowNo = puPuCodeInfo.getK3BillRowNo();
        String billType = puPuCodeInfo.getK3BillType();
        String channelCode = "";
        if (puPuCodeInfo.getOrderNo().contains("PP")) {
            channelCode = omsPupuOrderDao.getChannelCodeByOrder(puPuCodeInfo.getOrderNo());
        } else {
            channelCode = omsPupuOrderDao.getChannelCodeByPMOrder(puPuCodeInfo.getOrderNo());
        }

        List<OmsPupuCode> omsPupuCodeList = null;
        if ("F".equals(billType)) {
            omsPupuCodeList = omsPupuCodeDao.getModelByDeliveryNo(billNo, billRowNo);
        } else {
            omsPupuCodeList = omsPupuCodeDao.getModelBySaleOutNo(billNo, billRowNo);
        }

        List<OmsPupuCode> listPuPuCode = new ArrayList<>();
        List<OmsPupu> omsPupuList = new ArrayList<>();
        //同步未同步的噗噗码信息
        if (null == omsPupuCodeList || omsPupuCodeList.size() == 0) {

            List<PuPuCode> puPuCodeList = puPuCodeInfo.getPuPuList();

            for (int i = 0; i < puPuCodeList.size(); i++) {
                OmsPupuCode omsPuPuCode = new OmsPupuCode();
                OmsPupu omsPupu = new OmsPupu();

                PuPuCode puPuCode = puPuCodeList.get(i);

                //pupuCode表
                omsPuPuCode.setRowNo(billRowNo);
                omsPuPuCode.setBatchNo(puPuCodeInfo.getBatchNo());
                omsPuPuCode.setOrderNo(puPuCodeInfo.getOrderNo());
                if ("F".equals(billType)) {
                    omsPuPuCode.setDeliveryNo(billNo);
                } else {
                    omsPuPuCode.setSaleoutNo(billNo);
                }
                omsPuPuCode.setCreateTime(SystemHelper.currentDate());
                omsPuPuCode.setPupuCode(puPuCode.getPupuCode());
                omsPuPuCode.setId(String.valueOf(System.currentTimeMillis()));
                listPuPuCode.add(omsPuPuCode);

                //puPu表
                omsPupu.setPupuCode(puPuCode.getPupuCode());
                omsPupu.setCreateTime(SystemHelper.currentDate());
                omsPupu.setChannelCode(channelCode);
                omsPupu.setcPersonCode(channelCode);
                omsPupuList.add(omsPupu);
            }

            batchInsert(listPuPuCode, omsPupuList);
            return 1;
        } else {
            return 0;
        }

    }

    /*
    * 分批量提交数据
    * */
    private void batchInsert(List<OmsPupuCode> omsPuPuCodeList, List<OmsPupu> omsPuPuList) {

        Integer batch = 50;
        for (int i = 0; i < Math.ceil(omsPuPuCodeList.size() / (float) batch); i++) {
            Integer toIndex = (i + 1) * batch > omsPuPuCodeList.size() ? omsPuPuCodeList.size() : (i + 1) * batch;
            List<OmsPupuCode> tempList = omsPuPuCodeList.subList(i * batch, toIndex);
            omsPupuCodeDao.bulkInsertPuPuCodeInfo(tempList);
        }

        for (int j = 0; j < Math.ceil(omsPuPuList.size() / (float) batch); j++) {
            Integer toIndex = (j + 1) * batch > omsPuPuList.size() ? omsPuPuList.size() : (j + 1) * batch;
            List<OmsPupu> tempList = omsPuPuList.subList(j * batch, toIndex);
            omsPupuDao.bulkInsertPuPu(tempList);
        }
    }

}
