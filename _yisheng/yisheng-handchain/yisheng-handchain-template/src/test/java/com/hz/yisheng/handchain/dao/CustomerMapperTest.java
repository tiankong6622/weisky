package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.orm.Customer;

public class CustomerMapperTest extends BaseDAOTest{
	@Autowired
	private CustomerMapper customerMapper;
	/*@Test
	public void testInsert(){
		Customer customer = new Customer();
		customer.setName("220");
		customer.setAge(25);
		customer.setCreateTime(new Date());
		
		customerMapper.insert(customer);
		System.out.println(customer.getId());
		
	}
	@Test
	public void testUpdate(){
		
		Customer customer = new Customer();
		customer.setId(1l);
		customer.setAge(25);
		customer.setCreateTime(new Date());
		customerMapper.update(customer);
		System.out.println(customer.getId());
		
	}*/
	
	@Test
	public void testList(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("phone", "13757988811");
		List<Customer> customer = customerMapper.allList(param);
		
		System.out.println(customer.get(0).getId());
		
	}

}
