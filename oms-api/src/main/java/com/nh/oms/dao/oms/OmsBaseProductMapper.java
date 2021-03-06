package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsBaseProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface OmsBaseProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_base_product
     *
     * @mbggenerated Thu Nov 08 13:14:29 CST 2018
     */
    int deleteByPrimaryKey(Long iid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_base_product
     *
     * @mbggenerated Thu Nov 08 13:14:29 CST 2018
     */
    int insert(OmsBaseProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_base_product
     *
     * @mbggenerated Thu Nov 08 13:14:29 CST 2018
     */
    int insertSelective(OmsBaseProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_base_product
     *
     * @mbggenerated Thu Nov 08 13:14:29 CST 2018
     */
    OmsBaseProduct selectByPrimaryKey(Long iid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_base_product
     *
     * @mbggenerated Thu Nov 08 13:14:29 CST 2018
     */
    int updateByPrimaryKeySelective(OmsBaseProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_base_product
     *
     * @mbggenerated Thu Nov 08 13:14:29 CST 2018
     */
    int updateByPrimaryKey(OmsBaseProduct record);

    /**
     * 查询sku是否存在
     * @param proCode
     * @return
     */
    List<String> isExists(@Param("proCode") Set<String> proCode);

    /**
     * 查询不存在的sku
     * @param proCode
     * @return
     */
    List<String> isNotExists(@Param("proCode") Set<String> proCode);


    int updateByProduct(OmsBaseProduct baseProduct);
}