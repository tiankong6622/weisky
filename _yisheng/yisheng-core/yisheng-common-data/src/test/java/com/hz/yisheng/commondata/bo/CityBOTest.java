package com.hz.yisheng.commondata.bo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.commondata.bo.CityBO;
import com.hz.yisheng.commondata.dao.BaseDAOTest;

public class CityBOTest extends BaseDAOTest {
	
	@Autowired
	private CityBO cityBO;

	@Override
	protected String[] getSqlFiles() {
		return null;
	}

	@Test
	public void testGet(){
		Assert.assertNotNull(cityBO.getProvinces());
	}
}
