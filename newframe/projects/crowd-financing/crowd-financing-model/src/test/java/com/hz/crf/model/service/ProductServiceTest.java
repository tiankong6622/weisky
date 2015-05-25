package com.hz.crf.model.service;

import org.itboys.admin.service.BaseServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.crf.model.orm.Product;

public class ProductServiceTest extends BaseServiceTest{

	@Autowired
	private ProductService productService;
	
	@Test
	public void testSave(){
		Product pd = new Product();
		pd.setBigLogo("asdasd");
		productService.save(pd);
	}
	
	@Test
	public void testGetById(){
		Product pd = productService.findById(1L);
		System.out.println(pd);
	}
}
