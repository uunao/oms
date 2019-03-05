package com.nh.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Auther: Tsui
 * @Date: 2018-11-22
 * @Description:
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OmsApiApplicationStarter.class)//这里的Application是springboot的启动类名
//@WebAppConfiguration
public class OrderDeliveryControllerTest {


    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setupMockMvc() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //@Test
    public void deliverySync() {

        try {
            JSONObject jsonObject = new JSONObject(true);
            jsonObject.put("param", "{\n" +
                    "    \"datas\": [{\n" +
                    "      \"orderNo\": \"PP201811131039175927\",\n" +
                    "      \"indexSort\": 2,\n" +
                    "      \"expressNo\": \"SF201811131039175927\",\n" +
                    "      \"sampleno\": \"SA201811131039175927\",\n" +
                    "      \"deliveryNo\": \"D201811131039175927\",\n" +
                    "      \"deliveryQty\": 10,\n" +
                    "      \"deliveryTime\": \"2018-11-22 22:10:10\"\n" +
                    "    }]\n" +
                    "  }");

            //调用接口，传入添加的用户参数
            mockMvc.perform(MockMvcRequestBuilders.post("/api/product/delivery?sign=mJKCMwF9R27CA9b1RCbMAQ==")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(jsonObject.toString()))
                    //                .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}