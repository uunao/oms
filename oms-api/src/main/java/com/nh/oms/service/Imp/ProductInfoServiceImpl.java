package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.OmsBaseProductMapper;
import com.nh.oms.dao.oms.OmsComponentMapper;
import com.nh.oms.model.oms.OmsBaseProduct;
import com.nh.oms.model.oms.OmsComponent;
import com.nh.oms.service.IProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: Tsui
 * @Date: 2018\11\6
 * @Description:
 */
@Service
public class ProductInfoServiceImpl implements IProductInfoService {

    @Resource
    OmsBaseProductMapper omsBaseProductMapper;

    @Resource
    OmsComponentMapper omsComponentMapper;

    @Transient
    @Override
    public List<String> saveProduct(List<Map<String, Object>> list, Set<String> proCode) {
        StringBuilder sb = new StringBuilder();

        //查询SKU是否存在
        List<String> exists = omsBaseProductMapper.isExists(proCode);

//        //不存在的编码
//        if (exists.size() > 0) {
//            return exists;
//        }

        for (Map<String, Object> map : list) {
            OmsBaseProduct baseProduct = (OmsBaseProduct) map.get("header");

            if (!exists.contains(baseProduct.getCode())) {

                //插入sku表
                omsBaseProductMapper.insert(baseProduct);
            }else{
                //更新sku表
                omsBaseProductMapper.updateByProduct(baseProduct);
            }
        }

        return exists;
    }

    @Transient
    @Override
    public List<String> saveComponents(List<Map<String, Object>> list, Set<String> proCode) {
        List<OmsComponent> insertList = new ArrayList<OmsComponent>();

        //查询SKU是否存在
        List<String> exists = omsBaseProductMapper.isExists(proCode);

        //不存在的编码
        proCode.removeAll(exists);
        List res = new ArrayList(proCode);
        if (res.size() > 0) {
            return res;
        }

        for (Map<String, Object> map : list) {
            List<OmsComponent> components = (List<OmsComponent>) map.get("body");

            //查询套子件是否存在
            List<String> existsComponentList = omsComponentMapper.isExistsConponent(components);

            for(OmsComponent component : components){
                if(existsComponentList.contains(component.getProductCode()+component.getComponentCode())){
                    omsComponentMapper.updasteComponent(component);
                }else{
                    insertList.add(component);
                }
            }

            if (insertList != null && insertList.size() > 0) {

                //批量新增产品套子件
                omsComponentMapper.insertByBatch(insertList);
            }

        }

        return res;
    }


}
