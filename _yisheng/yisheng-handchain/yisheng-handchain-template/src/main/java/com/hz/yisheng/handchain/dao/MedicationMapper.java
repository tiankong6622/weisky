package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.orm.Medication;

public interface MedicationMapper {
	
	/**
	 * 新增
	 * @param customer
	 */
	public void insert(Medication medication);
	/**
	 * 修改信息
	 * @param customer
	 */
	public void update(Medication medication);

	/**
	 * 查询所有信息
	 * @param queryParam
	 * @return
	 */
	public List<Medication> list(Map<String,Object> queryParam);
	/**
	 * 得到信息的个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param);

}
