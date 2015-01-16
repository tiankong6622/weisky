package com.hz.yisheng.nosql.mongodb.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.hz.yisheng.nosql.mongodb.core.MongoDataSource;
import com.hz.yisheng.nosql.mongodb.entity.EntityIdDB;

/**
 * long类型ID自增类
 * 
 * @author WeiSky
 *
 */
@Service
public class IDGeneratorService {

	@Resource(name="baseMongoDS")
	private MongoDataSource baseMongoDS;
	
	
	
	/**
	 * 获取下一个自增主键
	 * @param name
	 * @param mongods
	 * @return
	 */
	public long getNextId(String name){
		Query<EntityIdDB> query = baseMongoDS.createQuery(EntityIdDB.class).field("name").equal(name);
		
		UpdateOperations<EntityIdDB> update = baseMongoDS.createUpdateOperations(EntityIdDB.class).inc("currentId");
		
		EntityIdDB entityId = baseMongoDS.findAndModify(query, update, false, true);
		return entityId.getCurrentId();
	}
}
