package com.hz.sunday.cyds.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.hz.sunday.cyds.orm.RunProject;

public class RunProjectMapperTest extends BaseDAOTest{

	@Autowired
	private RunProjectMapper runProjectMapper;
	
	@Test
	@Rollback(false)
	public void testInsert(){
		RunProject rp = new RunProject();
		rp.setArea("vvv");
		runProjectMapper.insert(rp);
		System.out.println(rp.getId());
	}
	
	@Test
	public void testGetList(){
		List<RunProject> list = runProjectMapper.getList(null);
		System.out.println(list.size());
	}
}
