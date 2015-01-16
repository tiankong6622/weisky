package com.hz.yisheng.nosql.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.hz.yisheng.nosql.mongodb.core.MongoDataSource;
import com.hz.yisheng.nosql.mongodb.entity.BaseLongIdEntity;

/**
 * 业务层父类
 * 
 * @author WeiSky
 * @param <T>
 *
 */
public abstract class BaseService<T, K> {

	protected abstract MongoDataSource getMongoDataSource();
	
	protected abstract Class<T> getEntityClass();
	
	@Autowired
	protected IDGeneratorService iDGeneratorService;
	
	public T getById(K id){
		return getMongoDataSource().get(getEntityClass(), id);
	}
	
	public void save(T t){
		if(t instanceof BaseLongIdEntity){
			BaseLongIdEntity entity = (BaseLongIdEntity)t;
			entity.setId(getNextId());
		}
		getMongoDataSource().save(t);
	}
	
	public void batchSave(List<T> list){
		for(T t:list){
			if(t instanceof BaseLongIdEntity){
				BaseLongIdEntity entity = (BaseLongIdEntity)t;
				entity.setId(getNextId());
			}
		}
		getMongoDataSource().save(list);
	}
	
	/**
	 * 判断对象含有filed的值是否存在
	 * @param filed
	 * @param value
	 * @return
	 */
	public boolean exists(String filed,Object value){
		return getMongoDataSource().createQuery(getEntityClass()).field(filed).equal(value).get()!=null;
	}
	
	public List<T> getByIds(List<K> ids){
		return getMongoDataSource().getList(getEntityClass(), ids);
	}
	
	public Query<T> getQueryById(K id){
		return getMongoDataSource().getQueryById(id, getEntityClass());
	}
	

	
	public boolean delete(K id){
		return getMongoDataSource().delete(id, getEntityClass());
	}
	/**
	 * When t contains a exactly Id,update this entity.
	 * @param t
	 */
	public void update(T t){
		getMongoDataSource().save(t);
	}
	
	public int update(K id,UpdateOperations<T> ops){
		Query<T> query = getQueryById(id);
		return getMongoDataSource().update(query, ops).getUpdatedCount();
	}
	
	/**
	 * update 单个字段 更新 状态之类的用这个就可以了
	 * @param id
	 * @param filed
	 * @param value
	 * @return
	 */
	public int update(K id,String filed,Object value){
		UpdateOperations<T> ops =  getMongoDataSource().createUpdateOperations(getEntityClass());
		ops.set(filed, value);
		return this.update(id, ops);
	}
	
	/**
	 * 对一些Number 子类类型的字段做自增  操作 比如 int double float bigdecimal 等
	 *  如果做减法 则传负数 类似于 update set a=a+value
	 * @param id
	 * @param filed
	 * @param value
	 * @return
	 */
	public int inc(K id,String filed,Number value){
		UpdateOperations<T> ops =  getMongoDataSource().createUpdateOperations(getEntityClass());
		ops.inc(filed, value);
		return this.update(id, ops);
	}
	
	public long getNextId(){
		return iDGeneratorService.getNextId(getEntityClass().getName());
	}
}
