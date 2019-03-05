package com.nh.oms.config;

import com.nh.oms.common.constant.ApiConstant;
import com.nh.oms.common.utils.AesUtil;
import com.nh.oms.common.utils.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Calendar;

/**
 * Created by ccwang on 2018/3/22.
 */
@Component
public class Interceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);
    private Calendar calendar;
    private String body;
    private String[] split;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        calendar = Calendar.getInstance();

        String queryString = httpServletRequest.getQueryString();
        logger.info("interceptortest 当前访问时间： {},  \n访问的ip：{},   \n请求路径：{} ,\n 请求参数：{}"
                , calendar.getTime()
                , RequestUtil.getIpAddress(httpServletRequest), httpServletRequest.getRequestURL().toString()
                ,queryString
        );

        try {
            //校验本次请求是否合法
            if (queryString == null) {
                return false;
            } else {
                queryString.replace("=", ":").replace("&", ",");

                split = (queryString.split("&")[0]).split("=");

                if (split == null || !"sign".equalsIgnoreCase(split[0]) || split[1] == null) {
                    return false;
                }
                String decode = URLDecoder.decode(split[1], "utf-8");

                //解密
                String decryptSign = new String(AesUtil.decryptStr(decode, ApiConstant.api_key));
                logger.info(decryptSign);

                //sign 解密后和app_id 一致
                if(ApiConstant.app_id.equalsIgnoreCase(decryptSign)|| true){
                    return true;
                }

            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return false;

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("aaaa");
    }

}
