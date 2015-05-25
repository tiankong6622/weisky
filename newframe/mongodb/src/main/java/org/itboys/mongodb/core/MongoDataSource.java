package org.itboys.mongodb.core;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.commons.utils.collection.FetchCondition;

import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

/**
 * mongodb数据源
 * @author 俊哥
 *
 */
public class MongoDataSource extends DatastoreImpl {

	public MongoDataSource(BaseMorphiaMapper morphia, MongoClient mongo, String dbName){
		super(morphia, mongo, dbName);
		setDefaultWriteConcern(WriteConcern.SAFE);
		morphia.mapEntities(this.getDB());
		this.getDB().setReadPreference(ReadPreference.primaryPreferred());
		this.ensureIndexes();
	}
			
			
	public MongoDataSource(BaseMorphiaMapper morphia, MongoClient mongo, String dbName, String username, String password) {
		super(morphia, mongo, dbName);
		if(!this.getDB().authenticate(username, password.toCharArray())){
			throw new RuntimeException("mongodb auth failed dbName="+dbName+",username="+username+",password="+password);
		}
		setDefaultWriteConcern(WriteConcern.SAFE);
		morphia.mapEntities(this.getDB());
		this.getDB().setReadPreference(ReadPreference.primaryPreferred());
		this.ensureIndexes();
	}
	
	
	public  <K,T> T get(K id,Class<T> clazz){
		return getQueryById(id, clazz).get();	
	}
	
	public <K,T> Query<T> getQueryById(K id,Class<T> clazz){
		Query<T> query = find(clazz);
		query.field(Mapper.ID_KEY).equal(id);
		return query;
	}
	
	public <T,K> List<T> getList(Class<T> type, List<K> keys) {
		Query<T> query = this.createQuery(type).field(Mapper.ID_KEY).in(keys);
		List<T> list = query.asList();
		return CommonCollectionUtils.filterCollection(list, new FetchCondition<T>() {
			@Override
			public boolean needFetch(T t) {
				return t!=null;
			}
		});
	}
	
	public <T> boolean delete(String id,Class<T> clazz){
		return delete(new ObjectId(),clazz);
	}
	
	public <T> boolean delete(Object id,Class<T> clazz){
		Query<T> query = createQuery(clazz);
		query.field(Mapper.ID_KEY).equal(id);
		WriteResult result = delete(query);
		return StringUtils.isBlank(result.getError());
	}
}