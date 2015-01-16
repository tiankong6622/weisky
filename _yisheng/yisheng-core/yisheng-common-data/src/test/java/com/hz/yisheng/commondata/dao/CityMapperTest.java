package com.hz.yisheng.commondata.dao;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.commondata.orm.City;

public class CityMapperTest extends BaseDAOTest {
	
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	DataSource dataSource;
	@Override
	protected String[] getSqlFiles() {
		return null;
	}

	/*@Test
	public void testGetAll(){
		 List<City> all = cityMapper.getAll();
		 Assert.assertTrue(all.size()>=0);
	}*/
	
	String sql = "select  (case when :subOrgId in (1,2,3,4)  then 1 when :subOrgId in (5,6,7,8) then 2 when :subOrgId in (9,10,11) then 3 end)  as ACTOR";
	@Test
	public void test(){
		List<City> list = cityMapper.getAll();
		System.out.println(list); 
	}
}
