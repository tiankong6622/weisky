package com.hz.yisheng.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hz.yisheng.nio.bo.BabyPositionBO;
import com.hz.yisheng.nio.orm.BabyPosition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-dao-test.xml")
public class MongoDBTest {
	
	@Autowired
	private BabyPositionBO babyPositionBO;
	
	
	@Test
	public void test(){
		BabyPosition pos = new BabyPosition();
		pos.setAddr("AAAAA1");
		babyPositionBO.save(pos);
		System.out.println(pos.getId());
	}
	
}
