package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.dto.CustomerDto;
import com.hz.yisheng.handchain.orm.Customer;


public interface CustomerMapper {
	/**
	 * 新增母婴信息
	 * @param customer
	 */
	public void insert(Customer customer);
	/**
	 * 修改母婴信息
	 * @param customer
	 */
	public void update(Customer customer);
	/**
	 * 查询所有的客户基本信息
	 * @param queryParam
	 * @return
	 */
	public List<Customer> allList(Map<String,Object> queryParam);
	/**
	 * 验证信息
	 * @param queryparam
	 * @return
	 */
	public List<Customer> checkList(Map<String,Object> queryparam);
	/**
	 * 查询所有的产妇基本信息
	 * @param queryParam
	 * @return
	 */
	public List<Customer> list(Map<String,Object> queryParam);
	/**
	 * 查询所有的产妇基本信息2
	 * @param queryParam
	 * @return
	 */
	public List<CustomerDto> list2(Map<String,Object> queryParam);
	/**
	 * 获取婴儿的基本信息
	 * @param queryParam
	 * @return
	 */
	public List<Customer> childList(Map<String,Object> queryParam);
	/**
	 * 根据母婴ID查询母婴信息
	 * @param customerId
	 * @return
	 */
	public List<Customer> getCustomerById(Long customerId);
	/**
	 * 根据多个ID查询母婴信息
	 * @param list
	 * @return
	 */
	public List<Customer> getCustomerByIds(List<Long> list);
	/**
	 * 得到产妇信息的个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param);
	/**
	 * 得到婴儿信息的个数
	 * @param param
	 * @return
	 */
	public Long childCount(Map<String,Object> param);
	
	/**
	 * 得到未添加婴儿信息的产妇名称
	 * @param queryParam
	 * @return
	 */
	public List<Customer> getMotherName(Map<String,Object> queryParam);
	
	/**
	 * 查询出所有有设备编号的客户名称及设备编号
	 * @param queryParam
	 * @return
	 */
	public List<Customer> queryCustomerNameAndDevice(Map<String,Object> queryParam);
	/**
	 * 查询出所有有设备编号的婴儿名称及设备编号
	 * @param queryParam
	 * @return
	 */
	public List<Customer> queryChildNameAndDevice(Map<String,Object> queryParam);

}
