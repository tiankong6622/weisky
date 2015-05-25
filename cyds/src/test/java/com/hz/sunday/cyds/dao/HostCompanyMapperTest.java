package com.hz.sunday.cyds.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.sunday.cyds.orm.HostCompany;

public class HostCompanyMapperTest extends BaseDAOTest{
	

	@Autowired
	private HostCompanyMapper hostCompanyMapper;
	
	@Test
	public void testInsert(){
		HostCompany hc= new HostCompany();
		hc.setName("xcv");
		hostCompanyMapper.insert(hc);
		System.out.println(hc.getId());
	}
	
	@Test
	public void testGethcType(){
		/*List<Integer> hcd = hostCompanyMapper.gethcType();
		System.out.println(hcd.size());
*/	}
}
