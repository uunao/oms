package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.ResultDetail;

public interface ResultDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result_detail
     *
     * @mbggenerated Wed Nov 21 17:45:00 CST 2018
     */
    int deleteByPrimaryKey(Integer iid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result_detail
     *
     * @mbggenerated Wed Nov 21 17:45:00 CST 2018
     */
    int insert(ResultDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result_detail
     *
     * @mbggenerated Wed Nov 21 17:45:00 CST 2018
     */
    int insertSelective(ResultDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result_detail
     *
     * @mbggenerated Wed Nov 21 17:45:00 CST 2018
     */
    ResultDetail selectByPrimaryKey(Integer iid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result_detail
     *
     * @mbggenerated Wed Nov 21 17:45:00 CST 2018
     */
    int updateByPrimaryKeySelective(ResultDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oms_test_result_detail
     *
     * @mbggenerated Wed Nov 21 17:45:00 CST 2018
     */
    int updateByPrimaryKey(ResultDetail record);
}