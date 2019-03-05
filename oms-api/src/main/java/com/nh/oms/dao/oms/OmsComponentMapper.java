package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsComponent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface OmsComponentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_component
     *
     * @mbggenerated Tue Nov 06 19:45:41 CST 2018
     */
    int deleteByPrimaryKey(long iid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_component
     *
     * @mbggenerated Tue Nov 06 19:45:41 CST 2018
     */
    int insert(OmsComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_component
     *
     * @mbggenerated Tue Nov 06 19:45:41 CST 2018
     */
    int insertSelective(OmsComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_component
     *
     * @mbggenerated Tue Nov 06 19:45:41 CST 2018
     */
    OmsComponent selectByPrimaryKey(long iid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_component
     *
     * @mbggenerated Tue Nov 06 19:45:41 CST 2018
     */
    int updateByPrimaryKeySelective(OmsComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_component
     *
     * @mbggenerated Tue Nov 06 19:45:41 CST 2018
     */
    int updateByPrimaryKey(OmsComponent record);

    int insertByBatch(@Param("components") List<OmsComponent> OmsComponent) ;

    /**
     * 查询sku是否存在
     * @param proCode
     * @return
     */
    List<String> isExists(@Param("proCode") Set<String> proCode);


    /**
     * 根据产品和子件编码 查询是否存在
     * 存在就更新
     * @param components
     * @return
     */
    List<String> isExistsConponent(@Param("components") List<OmsComponent> components);

    /**
     * 根据产品和子件编码更新
     * @param component
     */
    void updasteComponent(OmsComponent component);
}