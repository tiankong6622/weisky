package org.itboys.mongodb.service;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.entity.EntityIdDB;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

/**
 * long类型ID自增类
 * @author 俊哥
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
