package com.hz.sunday.cyds.bo;

import java.util.List;
import java.util.Map;

import org.javafans.common.utils.encryption.Digests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hz.sunday.cyds.dao.CustomerMapper;
import com.hz.sunday.cyds.orm.Customer;

/**
 * 客户基本信息
 * @author WeiSky
 *
 */
@Service
@Transactional
public class CustomerBO {
	
	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * 新增客户信息
	 * @param customer
	 */
	public void insert(Customer customer){
		customerMapper.insert(customer);
	}
	
	/**
	 * 编辑客户信息
	 * @param customer
	 */
	public void update(Customer customer){
		customerMapper.update(customer);
	}
	
	/**
	 * 根据客户id，删除一条信息
	 * @param id
	 */
	public void delete(Long id){
		customerMapper.delete(id);
	}
	
	/**
	 * 根据条件查询符合条件的信息条数
	 * @param param
	 * @return
	 */
	public Long getCount(Map<String, Object> param){
		return customerMapper.getCount(param);
	}
	
	/**
	 * 根据条件，查询符合条件的客户信息，返回的是一个集合
	 * @param param
	 * @return
	 */
	public List<Customer> getList(Map<String, Object> param){
		return customerMapper.getList(param);
	}
	
	/**
	 * 根据客户id，获取一条详情
	 * @param id
	 * @return
	 */
	public Customer getSingleDetail(Long id){
		return customerMapper.getSingleDetail(id);
	}
	
	/**
	 * 参赛选手登陆
	 * @param customer
	 * @return
	 */
	public Customer doLogin(Customer customer){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(2);
		param.put("mobile", customer.getMobile());
		param.put("passw", Digests.md5(customer.getPassw()));//明文密码转MD5
		List<Customer> list = customerMapper.getList(param);
		if(list != null && list.size() == 1){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 判断手机号是否已存在
	 * @param mobi
	 * @return
	 */
	public boolean extisMobi(String mobi){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("mobile", mobi);
		List<Customer> list = customerMapper.getList(param);
		if(list != null && list.size() > 0){
			return true;
		}else{
			return false;
		}
	}
}
