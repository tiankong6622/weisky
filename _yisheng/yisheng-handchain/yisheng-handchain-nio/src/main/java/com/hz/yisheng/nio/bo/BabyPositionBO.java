package com.hz.yisheng.nio.bo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hz.yisheng.nio.orm.BabyPosition;
import com.hz.yisheng.nosql.mongodb.core.MongoDataSource;
import com.hz.yisheng.nosql.mongodb.service.BaseService;

@Service
public class BabyPositionBO extends BaseService<BabyPosition, Long>{
	
	@Resource(name="adminDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<BabyPosition> getEntityClass() {
		return BabyPosition.class;
	}

}
