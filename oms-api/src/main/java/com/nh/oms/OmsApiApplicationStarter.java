package com.nh.oms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 *
 */
@SpringBootApplication(scanBasePackages = "com.nh.oms")
@EnableAsync //允许异步执行任务
public class OmsApiApplicationStarter {

    private static final Log LOG = LogFactory.getLog(OmsApiApplicationStarter.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(OmsApiApplicationStarter.class, args);
        LOG.info("NH-OMS-API is started，接口服务启动成功！");
    }

}
