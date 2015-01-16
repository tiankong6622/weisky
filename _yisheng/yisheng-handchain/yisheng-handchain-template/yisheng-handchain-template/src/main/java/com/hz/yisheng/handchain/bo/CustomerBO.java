package com.hz.yisheng.handchain.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hz.yisheng.handchain.dao.CustomerMapper;
import com.hz.yisheng.handchain.orm.Customer;


@Service
@Transactional
public class CustomerBO {
	
	@Autowired
	private CustomerMapper  customerMapper;
	
	/**
	 * 新增母婴信息
	 * @param customer
	 */
	public void insert(Customer customer){
		customerMapper.insert(customer);
	}
	/**
	 * 修改母婴信息
	 * @param customer
	 */
	public void update(Customer customer){
		customerMapper.update(customer);
	}
	/**
	 * 查询所有的客户基本信息
	 * @param queryParam
	 * @return
	 */
	public List<Customer> allList(Map<String,Object> queryParam){
		return customerMapper.allList(queryParam);
	}
	/**
	 * 查询产妇信息
	 * @param queryParam
	 * @return
	 */
	public List<Customer> list(Map<String,Object> queryParam){
		return customerMapper.list(queryParam);
	}
	/**
	 * 查询婴儿信息
	 * @param queryParam
	 * @return
	 */
	public List<Customer> childList(Map<String,Object> queryParam){
		return customerMapper.childList(queryParam);
	}
	/**
	 * 获取未添加婴儿信息的产妇名称
	 * @param queryParam
	 * @return
	 */
	public List<Customer> getMotherName(Map<String,Object> queryParam){
		return customerMapper.getMotherName(queryParam);
	}
	/**
	 * 根据ID获取一条母婴信息记录
	 * @param customerId
	 * @return
	 */
	public Customer getCustomerById(Long customerId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("id", customerId);
		List<Customer> alllist = customerMapper.allList(queryParam);
		//婴儿记录信息
		if(!alllist.isEmpty() && alllist.get(0).getParentId()!=0){
			List<Customer> childlist = customerMapper.childList(queryParam);
			return (childlist.isEmpty()?null:childlist.get(0));
		//产妇记录信息
		}else if(!alllist.isEmpty() && alllist.get(0).getParentId()==0){
			List<Customer> list = customerMapper.list(queryParam);
			return (list.isEmpty()?null:list.get(0));
		}else{
			return null;
		}
	}
	
	/**
	 * 获取产妇记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param){
		return customerMapper.count(param);
	}
	
	/**
	 * 获取婴儿记录个数
	 * @param param
	 * @return
	 */
	public Long childCount(Map<String,Object> param){
		return customerMapper.childCount(param);
		
	}
	
	/**
	 * 产妇信息分页查询
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<Customer> pageQuery(Page<Customer> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<Customer>() {
			@Override
			public long count() {
				return customerMapper.count(queryMap);
			}
			@Override
			public List<Customer> list() {
				return customerMapper.list(queryMap);
			}
		});
		return page;
	}
	
	/**
	 * 婴儿信息分页查询 
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<Customer> pageQuery2(Page<Customer> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<Customer>() {
			@Override
			public long count() {
				return customerMapper.childCount(queryMap);
			}
			@Override
			public List<Customer> list() {
				return customerMapper.childList(queryMap);
			}
		});
		return page;
	}
}
