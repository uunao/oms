package com.nh.oms.model.oms;

import java.io.Serializable;
import java.util.Date;

public class OmsExternalInterfaceLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_external_interface_log.iid
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    private Long iid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_external_interface_log.interface_name
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    private String interfaceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_external_interface_log.is_success
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    private String isSuccess;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_external_interface_log.is_sync
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    private String isSync;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_external_interface_log.create_time
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_external_interface_log.is_del
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    private String isDel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_external_interface_log.modify_time
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oms_external_interface_log.param
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    private String param;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oms_external_interface_log
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_external_interface_log.iid
     *
     * @return the value of oms_external_interface_log.iid
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public Long getIid() {
        return iid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_external_interface_log.iid
     *
     * @param iid the value for oms_external_interface_log.iid
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public void setIid(Long iid) {
        this.iid = iid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_external_interface_log.interface_name
     *
     * @return the value of oms_external_interface_log.interface_name
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public String getInterfaceName() {
        return interfaceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_external_interface_log.interface_name
     *
     * @param interfaceName the value for oms_external_interface_log.interface_name
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_external_interface_log.is_success
     *
     * @return the value of oms_external_interface_log.is_success
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public String getIsSuccess() {
        return isSuccess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_external_interface_log.is_success
     *
     * @param isSuccess the value for oms_external_interface_log.is_success
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_external_interface_log.is_sync
     *
     * @return the value of oms_external_interface_log.is_sync
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public String getIsSync() {
        return isSync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_external_interface_log.is_sync
     *
     * @param isSync the value for oms_external_interface_log.is_sync
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_external_interface_log.create_time
     *
     * @return the value of oms_external_interface_log.create_time
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_external_interface_log.create_time
     *
     * @param createTime the value for oms_external_interface_log.create_time
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_external_interface_log.is_del
     *
     * @return the value of oms_external_interface_log.is_del
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_external_interface_log.is_del
     *
     * @param isDel the value for oms_external_interface_log.is_del
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_external_interface_log.modify_time
     *
     * @return the value of oms_external_interface_log.modify_time
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_external_interface_log.modify_time
     *
     * @param modifyTime the value for oms_external_interface_log.modify_time
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oms_external_interface_log.param
     *
     * @return the value of oms_external_interface_log.param
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public String getParam() {
        return param;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oms_external_interface_log.param
     *
     * @param param the value for oms_external_interface_log.param
     *
     * @mbggenerated Fri Jan 18 18:09:32 CST 2019
     */
    public void setParam(String param) {
        this.param = param;
    }
}