package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.orm.ChildHealth;

public class ChildHealthMapperTest extends BaseDAOTest{
	@Autowired
	private ChildHealthMapper childHealthMapper;
	/*@Test
	public void testInsert(){
		ChildHealth childHealth = new ChildHealth();
		childHealth.setIcterus(212l);
		childHealth.setBloodPre(12l);
		childHealth.setCustomerId(11l);
		childHealthMapper.insert(childHealth);
		
	}
	@Test
	public void testSearch(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", "%李娜%");
		List<ChildHealth> xx =   childHealthMapper.list(param);
		System.out.println(xx.size());
		
	}*/
	@Test
	public void testList(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("customerId", 12);
		List<ChildHealth> childHealth = childHealthMapper.list(param);
		for(int i=0;i<childHealth.size();i++){
			System.out.println(childHealth.get(i).getId());
		}
	}

}
