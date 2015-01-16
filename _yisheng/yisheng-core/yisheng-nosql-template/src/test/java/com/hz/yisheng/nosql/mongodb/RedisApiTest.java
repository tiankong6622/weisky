package com.hz.yisheng.nosql.mongodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

import com.hz.yisheng.nosql.redis.RedisApi;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-dao-test.xml")
public class RedisApiTest{

	@Autowired
	private RedisApi redisApi;
	
	@Test
	public void setTest(){
		/*Jedis jedis = new Jedis("192.168.3.224");  
		jedis.set("hb", "xxxx");
		System.out.println(jedis.get("hb"));*/
		
		/*redisApi.set("aa", "xxxx12312321");
		System.out.println(redisApi.get("aa"));*/
		
		Jedis jedis = new Jedis("192.168.3.224");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "nomouse");
		map.put("password", "1234569797987");
		map.put("sex", "male");
		
		jedis.hmset("user", map);
		
		Map<String, String> mm = jedis.hgetAll("user");
		System.out.println(mm);
		
		List<String> list = jedis.hmget("rfidhb00000001","00000001","ct","count");
		
		System.out.println("name=" + list.get(0));
		System.out.println("password=" + list.get(1));
		System.out.println("password=" + list.get(2));
	}
}
