package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.orm.CustomerChild;


public interface CustomerChildMapper {
	/**
	 * 插入
	 * @param customerChild
	 */
	public void insert(CustomerChild customerChild);
	/**
	 * 修改
	 * @param customerChild
	 */
	public void update(CustomerChild customerChild);
	/**
	 * 获取所有的家长与孩子关联信息
	 * @param queryParam
	 * @return
	 */
	public List<CustomerChild> list(Map<String,Object> queryParam);
	/**
	 * 获取记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param);
	
	/**
	 * 查询家长与孩子关系
	 * @param queryParam
	 * @return
	 */
	public List<CustomerChild> contactList(Map<String,Object> queryParam);
	
	/**
	 * 根据家长id查询中间表信息
	 * @param queryParam
	 * @return
	 */
	public List<CustomerChild> queryByCustomerId(Map<String,Object> queryParam);
	

}
