package com.hz.crf.model.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.hz.crf.model.orm.MobiCode;

/**
 * 手机验证码服务层
 * @author weisky
 *
 * May 28, 2015
 */
@Service
public class MobiCodeService extends BaseService<MobiCode, Long>{
	
	private static final long serialVersionUID = -7214810079858051651L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<MobiCode> getEntityClass() {
		return MobiCode.class;
	}
	
	/**
	 * 根据手机号和验证码类型  获取一条验证码
	 * @param mobi
	 * @param type
	 * @return
	 */
	public MobiCode findByMobiAndType(String mobi, int type){
		Map<String,Object> param = Maps.newHashMapWithExpectedSize(2);
		param.put("mobi", mobi);
		param.put("type", type);
		List<MobiCode> mcList = super.list(param);
		return mcList != null ? mcList.get(0) : null;
	}

}
