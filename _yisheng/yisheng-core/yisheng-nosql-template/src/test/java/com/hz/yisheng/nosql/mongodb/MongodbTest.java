package com.hz.yisheng.nosql.mongodb;

import java.net.UnknownHostException;

import com.mongodb.Mongo;

public class MongodbTest {

	@org.junit.Test
	public void test() throws UnknownHostException{
		@SuppressWarnings("deprecation")
		Mongo mg = new Mongo("192.168.3.224");
		for(String name : mg.getDatabaseNames()){
			System.out.println(name);
		}
	}
}
