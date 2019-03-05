package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsOrderLogMapper;
import com.nh.oms.model.oms.OmsOrderLog;
import com.nh.oms.service.IOmsOrderLogService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccwang on 2018/11/16.
 */
@Service
public class OmsOrderLogService implements IOmsOrderLogService {

    private final static Logger logger = LoggerFactory.getLogger(OmsOrderLogService.class);

    @Autowired
    private OmsOrderLogMapper omsOrderLogDao;

    @Resource
    private DataSourceTransactionManager transactionManager;

    @Override
    public void insert(OmsOrderLog omsOrderLog) {
        omsOrderLogDao.insert(omsOrderLog);
    }

    /**
     * 每次提交50条
     *
     * @param orderLogList
     */
    @Override
    public void insertList(List<OmsOrderLog> orderLogList) {
        int num = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        int ceil = (new Double(Math.ceil(orderLogList.size() / 50.0))).intValue();
        int size = 50;


        while (num < ceil) {
            if (num == (ceil - 1)) {
                size = orderLogList.size();
            } else {
                size = (num + 1) * 50;
            }
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
            TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
            List<OmsOrderLog> omsOrderLogs = orderLogList.subList(num * 50, size);

            try {
                omsOrderLogDao.insertList(omsOrderLogs);

                transactionManager.commit(status);
            } catch (Exception e) {
                JSONArray json = JSONArray.fromObject(omsOrderLogs);
                logger.error("日志插入错误：｛｝", json);
                transactionManager.rollback(status);
            }
            num++;
        }


    }
}
