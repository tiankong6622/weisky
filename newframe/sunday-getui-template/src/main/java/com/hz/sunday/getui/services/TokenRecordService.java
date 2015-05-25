package com.hz.sunday.getui.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.hz.sunday.getui.entity.TokenRecord;

@Service
public class TokenRecordService extends BaseService<TokenRecord, Long> {

	private static final long serialVersionUID = -4758783558661095198L;
	
	@Resource(name="getuiDS")
	private MongoDataSource tokenDataSource;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return tokenDataSource;
	}

	@Override
	protected Class<TokenRecord> getEntityClass() {
		return TokenRecord.class;
	}
	
	public void save(TokenRecord tokenRecord){
		tokenRecord.setCreateTime(new Date());
		super.save(tokenRecord);
	}
	
	public TokenRecord isExists(TokenRecord tokenRecord){
		Query<TokenRecord> query = getMongoDataSource().createQuery(getEntityClass()).filter("token", tokenRecord.getToken())
				.filter("status", 0);
		List<TokenRecord> list = query.asList();
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	public List<String> getTokens(Map<String, Object> param){
		param.put("status", 0);
		List<TokenRecord> list = super.list(param);
		List<String> tokens = new ArrayList<String>();
		for(TokenRecord tokenRecord : list){
			tokens.add(tokenRecord.getToken());
		}
		return tokens;
	} 

}
