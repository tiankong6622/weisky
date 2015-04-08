package com.hz.sunday.cyds.dao;

import java.util.List;
import java.util.Map;

import com.hz.sunday.cyds.orm.Customer;

/**
 * 客户基本信息接口
 * @author WeiSky
 *
 */
public interface CustomerMapper {

	/**
	 * 新增客户信息
	 * @param customer
	 */
	public void insert(Customer customer);
	
	/**
	 * 编辑客户信息
	 * @param customer
	 */
	public void update(Customer customer);
	
	/**
	 * 根据客户id，删除一条信息
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 根据条件查询符合条件的信息条数
	 * @param param
	 * @return
	 */
	public Long getCount(Map<String, Object> param);
	
	/**
	 * 根据条件，查询符合条件的客户信息，返回的是一个集合
	 * @param param
	 * @return
	 */
	public List<Customer> getList(Map<String, Object> param);
	
	/**
	 * 根据客户id，获取一条详情
	 * @param id
	 * @return
	 */
	public Customer getSingleDetail(Long id);
}
