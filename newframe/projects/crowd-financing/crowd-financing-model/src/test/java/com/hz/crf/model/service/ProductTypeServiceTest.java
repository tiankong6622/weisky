package com.hz.crf.model.service;

import java.util.List;

import org.itboys.admin.service.BaseServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.crf.model.orm.ProductType;

public class ProductTypeServiceTest extends BaseServiceTest{
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Test
	public void findAllTest(){
		List<ProductType> list = productTypeService.findAll();
		System.out.println(list);
	}

}
