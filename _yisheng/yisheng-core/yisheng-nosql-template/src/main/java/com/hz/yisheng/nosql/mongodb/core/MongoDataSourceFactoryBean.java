package com.hz.yisheng.nosql.mongodb.core;

import org.javafans.framework.spring.common.SpringContextHolder;
import org.springframework.beans.factory.FactoryBean;

/**
 * 在不同项目中 各个工程的mongodb数据源 如果引用同一个数据源的话 可以用该工厂bean创建已有的bean对象引用
 * @author WeiSky
 *
 */
public class MongoDataSourceFactoryBean implements FactoryBean<MongoDataSource>{

	private String mongoDataSourceBean;
	
	@Override
	public MongoDataSource getObject() throws Exception {
		return SpringContextHolder.getBean(mongoDataSourceBean);
	}

	@Override
	public Class<MongoDataSource> getObjectType() {
		return MongoDataSource.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setMongoDataSourceBean(String mongoDataSourceBean) {
		this.mongoDataSourceBean = mongoDataSourceBean;
	}

	public String getMongoDataSourceBean() {
		return mongoDataSourceBean;
	}

}
