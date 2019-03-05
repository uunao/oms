package com.nh.oms.service;

import com.nh.oms.model.oms.OmsPupuDeliveryExt;
import com.nh.oms.model.oms.PupuDeliveryOrSaleoutDetail;

import java.util.List;

/**
 * 发货/出库同步明细 服务层
 * 
 * @author nh
 * @date 2018-12-25
 */
public interface IPupuDeliveryOrSaleoutDetailService 
{
	/**
     * 查询发货/出库同步明细信息
     * 
     * @param iid 发货/出库同步明细ID
     * @return 发货/出库同步明细信息
     */
	public PupuDeliveryOrSaleoutDetail selectPupuDeliveryOrSaleoutDetailById(Long iid);
	
	/**
     * 查询发货/出库同步明细列表
     * 
     * @param pupuDeliveryOrSaleoutDetail 发货/出库同步明细信息
     * @return 发货/出库同步明细集合
     */
	public List<PupuDeliveryOrSaleoutDetail> selectPupuDeliveryOrSaleoutDetailList(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail);
	
	/**
     * 新增发货/出库同步明细
     * 
     * @param pupuDeliveryOrSaleoutDetail 发货/出库同步明细信息
     * @return 结果
     */
	public int insertPupuDeliveryOrSaleoutDetail(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail);
	
	/**
     * 修改发货/出库同步明细
     * 
     * @param pupuDeliveryOrSaleoutDetail 发货/出库同步明细信息
     * @return 结果
     */
	public int updatePupuDeliveryOrSaleoutDetail(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail);

	/**
	 * 检测发货明细是否已经存在
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
