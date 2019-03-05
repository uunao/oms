package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsTestResult;

public interface OmsTestResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result
     *
     * @mbggenerated Tue Nov 27 21:05:52 CST 2018
     */
    int deleteByPrimaryKey(Long iid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result
     *
     * @mbggenerated Tue Nov 27 21:05:52 CST 2018
     */
    int insert(OmsTestResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result
     *
     * @mbggenerated Tue Nov 27 21:05:52 CST 2018
     */
    int insertSelective(OmsTestResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result
     *
     * @mbggenerated Tue Nov 27 21:05:52 CST 2018
     */
    OmsTestResult selectByPrimaryKey(Long iid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result
     *
     * @mbggenerated Tue Nov 27 21:05:52 CST 2018
     */
    int updateByPrimaryKeySelective(OmsTestResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result
     *
     * @mbggenerated Tue Nov 27 21:05:52 CST 2018
     */
    int updateByPrimaryKey(OmsTestResult record);

    OmsTestResult selectBySampleno(String sampleno);
}