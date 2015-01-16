package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.orm.CustomerDevice;

public class CustomerDeviceMapperTest extends BaseDAOTest {
	
	@Autowired
	private CustomerDeviceMapper customerDeviceMapper;
	
	/*@Test
	public void testInsert(){
		CustomerDevice customerDevice = new CustomerDevice();
		customerDevice.setNumber("1");
		customerDevice.setStatus("启动");
		customerDevice.setCreateTime(new Date());
		customerDeviceMapper.insert(customerDevice);
		System.out.println(customerDevice.getId());
	}*/
	
	@Test
	public void testList(){
		Map<String,Object> queryParam = new HashMap<String,Object>();
		List<CustomerDevice> customerDevice = customerDeviceMapper.list(queryParam);
		for(int i=0;i<customerDevice.size();i++){
			System.out.println(customerDeviceMapper.list(queryParam).get(i).getStatus());
		}
		
	}
}
