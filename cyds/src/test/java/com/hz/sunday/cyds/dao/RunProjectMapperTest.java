package com.hz.sunday.cyds.dao;

import java.math.BigDecimal;
import java.util.Date;
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
		rp.setEnteBuildTime(new Date());
		rp.setRegisCapital(new BigDecimal(20));
		rp.setIncome(new BigDecimal(30));
		rp.setIncomeCapital(new BigDecimal(40));
		rp.setScore(new BigDecimal(10));
		runProjectMapper.insert(rp);
		System.out.println(rp.getId());
	}
	
	@Test
	public void testGetList(){
		List<RunProject> list = runProjectMapper.getList(null);
		System.out.println(list.size());
	}
}
