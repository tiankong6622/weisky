package com.hz.sunday.cyds.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.hz.sunday.cyds.orm.Customer;

public class CustomerMapperTest extends BaseDAOTest{
	
	@Autowired
	private CustomerMapper customerMapper;

	@Test
	@Rollback(false)
	public void testInsert(){
		Customer customer = new Customer();
		customer.setComment("xxxxx");
		customer.setCtype("2121");
		customerMapper.insert(customer);
		System.out.println(customer.getId());
	}
	
	@Test
	public void testGetList(){
		List<Customer> list = customerMapper.getList(null);
		Long count = customerMapper.getCount(null);
		System.out.println(list.size() + "," + count);
	}
}
