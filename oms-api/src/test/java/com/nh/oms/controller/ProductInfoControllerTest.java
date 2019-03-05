package com.nh.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Autheor: Tsui
 * @Date: 2018\11\9 0009
 * @Description:
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OmsApiApplicationStarter.class)//这里的Application是springboot的启动类名
//@WebAppConfiguration
public class ProductInfoControllerTest {


    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setupMockMvc() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    //@Test
    public void materialInfoSave() throws Exception {

        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("param", "{\n" +
                "    \"datas\": [{\n" +
                "        \"code\": \"110100010\",\n" +
                "        \"baseProduct\": {\n" +
                "            \"id\": \"11\",\n" +
                "            \"status\": 22,\n" +
                "            \"objecttype\": 33,\n" +
                "            \"typeid\": \"44\",\n" +
                "            \"code\": \"110100010\",\n" +
                "            \"name\": \"66\",\n" +
                "            \"ename\": \"7\",\n" +
                "            \"shortname\": \"8\",\n" +
                "            \"marketPrice\": 9.1,\n" +
                "            \"memberPrice\": 10.01,\n" +
                "            \"tax_rate\":12.5,\n" +
                "            \"shortcontent\": \"11\",\n" +
                "            \"description\": \"12\",\n" +
                "            \"sort\": 1,\n" +
                "            \"weight\": 14.2,\n" +
                "            \"unit\": \"15\",\n" +
                "            \"remark\": \"16\"\n" +
                "        },\n" +
                "        \"components\": [{\n" +
                "            \"productCode\": \"110100010\",\n" +
                "            \"componentCode\": \"001\",\n" +
                "            \"componentName\": \"XXX盒子\",\n" +
                "            \"object_type\":1\n" +
                "        }, {\n" +
                "            \"productCode\": \"110100010\",\n" +
                "            \"componentCode\": \"002\",\n" +
                "            \"componentName\": \"XXX服务\",\n" +
                "            \"object_type\":2\n" +
                "        }, {\n" +
                "            \"productCode\": \"110100010\",\n" +
                "            \"componentCode\": \"003\",\n" +
                "            \"componentName\": \"盒子服务\",\n" +
                "            \"object_type\":1\n" +
                "        }]\n" +
                "    },{\n" +
                "        \"code\": \"110100009\",\n" +
                "        \"baseProduct\": {\n" +
                "            \"id\": \"11\",\n" +
                "            \"status\": 22,\n" +
                "            \"objecttype\": 33,\n" +
                "            \"typeid\": \"44\",\n" +
                "            \"code\": \"110100010\",\n" +
                "            \"name\": \"66\",\n" +
                "            \"ename\": \"7\",\n" +
                "            \"shortname\": \"8\",\n" +
                "            \"marketPrice\": 9.1,\n" +
                "            \"memberPrice\": 10.01,\n" +
                "            \"tax_rate\":12.5,\n" +
                "            \"shortcontent\": \"11\",\n" +
                "            \"description\": \"12\",\n" +
                "            \"sort\": 1,\n" +
                "            \"weight\": 14.2,\n" +
                "            \"unit\": \"15\",\n" +
                "            \"remark\": \"16\"\n" +
                "        },\n" +
                "        \"components\": [{\n" +
                "            \"productCode\": \"110100010\",\n" +
                "            \"componentCode\": \"001\",\n" +
                "            \"componentName\": \"XXX盒子\",\n" +
                "            \"object_type\":1\n" +
                "        }, {\n" +
                "            \"productCode\": \"110100010\",\n" +
                "            \"componentCode\": \"002\",\n" +
                "            \"componentName\": \"XXX服务\",\n" +
                "            \"object_type\":2\n" +
                "        }, {\n" +
                "            \"productCode\": \"110100010\",\n" +
                "            \"componentCode\": \"003\",\n" +
                "            \"componentName\": \"盒子服务\",\n" +
                "            \"object_type\":1\n" +
                "        }]\n" +
                "    }]\n" +
                "}");
/*

        //调用接口，传入添加的用户参数
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/materialInfo?sign=6BD867F36BB9F10E5105DFA80EB41A25")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toString()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());
*/

    }
}