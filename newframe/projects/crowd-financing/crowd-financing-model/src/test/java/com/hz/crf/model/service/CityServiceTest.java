package com.hz.crf.model.service;

import org.itboys.admin.service.BaseServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.crf.model.orm.City;

public class CityServiceTest extends BaseServiceTest{

	@Autowired
	private CityService cityService;
	
	@Test
	public void testFindById(){
		City cc = cityService.findById(1L);
		System.out.println(cc.getName());
	}
}
