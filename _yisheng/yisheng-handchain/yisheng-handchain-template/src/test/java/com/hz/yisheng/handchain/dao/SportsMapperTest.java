package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.orm.Sports;

public class SportsMapperTest extends BaseDAOTest{
	@Autowired
	private SportsMapper sportsMapper;
	
	@Test
	public void testList(){
		Map<String,Object> param = new HashMap<String,Object>();
		List<Sports> sports = sportsMapper.list(param);
			System.out.println(sports.get(0).getId());
	}
	
}
