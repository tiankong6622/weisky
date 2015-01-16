package com.hz.yisheng.nio.conf;

import java.net.UnknownHostException;

import com.google.code.morphia.Morphia;
import com.mongodb.MongoClient;

/**
 * mongodb操作的一些简单封装
 * 
 * @author WeiSky
 *
 */
public class MongoClientApi extends MongoClient{
	
	private Morphia morphia;

	public MongoClientApi() throws UnknownHostException {
		super("192.168.3.224", 27017);
		morphia = new Morphia();
	}
	
	

}
