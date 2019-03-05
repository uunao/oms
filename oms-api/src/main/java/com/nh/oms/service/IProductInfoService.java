package com.nh.oms.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: Tsui
 * @Date: 2018\11\6
 * @Description:
 */
public interface IProductInfoService {

    /**
     * 插入物料信息
     * @param list
     * @param set 所有的sku
     * @return
     */
    List<String> saveProduct(List<Map<String,Object>> list,Set<String> set);

    /**
     * 插入bom信息
     * @param list
     * @param set
     * @return
     */
    List<String> saveComponents(List<Map<String,Object>> list,Set<String> set);

}
