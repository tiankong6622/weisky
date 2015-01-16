package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.orm.Hospital;

public class HospitalMapperTest extends BaseDAOTest{
	@Autowired
	private HospitalMapper hospitalMapper;
	
	@Test
	public void testList(){
		Map<String,Object> param = new HashMap<String,Object>();
		List<Hospital> hospital = hospitalMapper.list(param);
		for(int i=0;i<hospital.size();i++){
			System.out.println(hospital.get(i).getId());
		}
	}
}
