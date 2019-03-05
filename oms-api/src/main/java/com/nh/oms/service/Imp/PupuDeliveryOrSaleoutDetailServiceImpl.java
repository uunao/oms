package com.nh.oms.service.Imp;

import com.nh.oms.dao.oms.PupuDeliveryOrSaleoutDetailMapper;
import com.nh.oms.model.oms.OmsPupuDeliveryExt;
import com.nh.oms.model.oms.PupuDeliveryOrSaleoutDetail;
import com.nh.oms.service.IPupuDeliveryOrSaleoutDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发货/出库同步明细 服务层实现
 * 
 * @author nh
 * @date 2018-12-25
 */
@Service
public class PupuDeliveryOrSaleoutDetailServiceImpl implements IPupuDeliveryOrSaleoutDetailService
{
	@Autowired
	private PupuDeliveryOrSaleoutDetailMapper pupuDeliveryOrSaleoutDetailMapper;

	/**
     * 查询发货/出库同步明细信息
     * 
     * @param iid 发货/出库同步明细ID
     * @return 发货/出库同步明细信息
     */
    @Override
	public PupuDeliveryOrSaleoutDetail selectPupuDeliveryOrSaleoutDetailById(Long iid)
	{
	    return pupuDeliveryOrSaleoutDetailMapper.selectPupuDeliveryOrSaleoutDetailById(iid);
	}
	
	/**
     * 查询发货/出库同步明细列表
     * 
     * @param pupuDeliveryOrSaleoutDetail 发货/出库同步明细信息
     * @return 发货/出库同步明细集合
     */
	@Override
	public List<PupuDeliveryOrSaleoutDetail> selectPupuDeliveryOrSaleoutDetailList(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail)
	{
	    return pupuDeliveryOrSaleoutDetailMapper.selectPupuDeliveryOrSaleoutDetailList(pupuDeliveryOrSaleoutDetail);
	}
	
    /**
     * 新增发货/出库同步明细
     * 
     * @param pupuDeliveryOrSaleoutDetail 发货/出库同步明细信息
     * @return 结果
     */
	@Override
	public int insertPupuDeliveryOrSaleoutDetail(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail)
	{
	    return pupuDeliveryOrSaleoutDetailMapper.insertPupuDeliveryOrSaleoutDetail(pupuDeliveryOrSaleoutDetail);
	}
	
	/**
     * 修改发货/出库同步明细
     * 
     * @param pupuDeliveryOrSaleoutDetail 发货/出库同步明细信息
     * @return 结果
     */
	@Override
	public int updatePupuDeliveryOrSaleoutDetail(PupuDeliveryOrSaleoutDetail pupuDeliveryOrSaleoutDetail)
	{
	    return pupuDeliveryOrSaleoutDetailMapper.updatePupuDeliveryOrSaleoutDetail(pupuDeliveryOrSaleoutDetail);
	}

	@Override
	public int checkDeliveryIsExists(OmsPupuDeliveryExt delivery) {
		return pupuDeliveryOrSaleoutDetailMapper.checkDeliveryIsExists(delivery);
	}

	@Override
	public void insertPupuDeliveryDetail(OmsPupuDeliveryExt delivery) {
		pupuDeliveryOrSaleoutDetailMapper.insertPupuDeliveryDetail(delivery);
	}


}
