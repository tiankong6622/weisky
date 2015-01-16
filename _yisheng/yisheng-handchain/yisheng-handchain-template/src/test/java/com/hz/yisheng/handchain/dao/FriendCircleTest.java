package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.orm.friendCircleBean;

public class FriendCircleTest extends BaseDAOTest{
	@Autowired
	private FriendCircleMapper circleMapper;

	@Test
	public void testList() {
		final Map<String,Object> query = new HashMap<String,Object>();
		Long ss = circleMapper.findCount(query);
		if(ss==null){
			System.out.println("aaaaaaa");
		}else{
			System.out.println(ss);
		}
	}
	@Test
	public void testssList() {
		Map<String,Object> query = new HashMap<String,Object>();
		List<friendCircleBean> ss = circleMapper.findFriendList(query);
		if(ss==null||ss.size()<0){
			System.out.println("aaaaaaa");
		}else{
			System.out.println(ss.size());
		}
	}
}