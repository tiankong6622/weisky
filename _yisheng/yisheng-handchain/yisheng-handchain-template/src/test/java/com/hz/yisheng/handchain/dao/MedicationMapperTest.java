package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.orm.Medication;



public class MedicationMapperTest extends BaseDAOTest{
	@Autowired
	private MedicationMapper medicationMapper;
	
	@Test
	public void testList(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("medicationName", "9");
		List<Medication> medication = medicationMapper.list(param);
		for(int i=0;i<medication.size();i++){
			System.out.println(medication.get(i).getMedicationName());
		}
	}
}
