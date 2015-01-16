package com.hz.yisheng.handchain.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * DAO层测试基类
 *
 */

public  class KnowledgeBaseDAOTest extends BaseDAOTest {
	
	@Autowired
	private KnowledgeStockMapper stockMapper;
	
	@Test
	public void testGetRootMenu(){
		Map<String,Object> queryParam = new HashMap<String,Object>();
		queryParam.put("sicktime", "0-1岁");
	    stockMapper.findKnowledge(queryParam);
	}
	


	
}
