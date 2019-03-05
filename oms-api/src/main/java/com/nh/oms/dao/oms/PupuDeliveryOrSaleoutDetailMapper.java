package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsPupuDeliveryExt;
import com.nh.oms.model.oms.PupuDeliveryOrSaleoutDetail;

import java.util.List;

/**
 * 发货/出库同步明细 数据层
 * 
 * @author nh
 * @date 2018-12-25
 */
public interface PupuDeliveryOrSaleoutDetailMapper
{
	/**
     * 查询发货/出库同步明细信息
     * 
     * @param iid 发货/出库同步明细ID
     * @return 发货/出库同步明细信息
     */
	PupuDeliveryOrSaleoutDetail selectPupuDeliveryOrSaleoutDetailById(Long iid);
	
	/**
     * 查询发货/出库同步明细列表
     * 
     * @param pupuDeliveryOrSaleoutDetail 发货/出库同步明细信息
     * @return 发货/出库同步明细集合
     */
	List<PupuDeliveryOrSaleoutDetail> selectPupuDeliveryOrSaleoutDetailList(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail);
	
	/**
     * 新增发货/出库同步明细
     * 
     * @param pupuDeliveryOrSaleoutDetail 发货/出库同步明细信息
     * @return 结果
     */
	int insertPupuDeliveryOrSaleoutDetail(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail);
	
	/**
     * 修改发货/出库同步明细
     * 
     * @param pupuDeliveryOrSaleoutDetail 发货/出库同步明细信息
     * @return 结果
     */
	int updatePupuDeliveryOrSaleoutDetail(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail);
	
	/**
     * 删除发货/出库同步明细
     * 
     * @param iid 发货/出库同步明细ID
     * @return 结果
     */
	int deletePupuDeliveryOrSaleoutDetailById(Long iid);
	
	/**
     * 批量删除发货/出库同步明细
     * 
     * @param iids 需要删除的数据ID
     * @return 结果
     */
	int deletePupuDeliveryOrSaleoutDetailByIds(String[] iids);

	int isExist(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail);

	void insert(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail);

	/**
	 * 校验噗噗管发货明细是否存在
	 * @param delivery
	 * @return
	 */
	int checkDeliveryIsExists(OmsPupuDeliveryExt delivery);

	/**
	 * 保存噗噗管发货明细
	 * @param delivery
	 */
	void insertPupuDeliveryDetail(OmsPupuDeliveryExt delivery);
}