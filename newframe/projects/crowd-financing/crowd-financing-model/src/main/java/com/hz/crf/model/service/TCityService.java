package com.hz.crf.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.hz.crf.model.orm.TCity;

/**
 * 省市县
 * @author weisky
 *
 * May 28, 2015
 */
@Service
public class TCityService extends BaseService<TCity, Long>{

	private static final long serialVersionUID = -7599060074089185789L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<TCity> getEntityClass() {
		return TCity.class;
	}
	
	/**
	 * 根据ID 获取一条记录
	 * @param id
	 * @return
	 */
	public TCity findById(Long id){
		return super.getByField("id", id);
	}
	
	/**
	 * 根据父ID 获取下面的子城市
	 * 
	 * @param parentId
	 * @return
	 */
	public List<TCity> findByParentId(Long parentId){
		return super.findByField("parentId", parentId);
	}
	
	/**
	 * 获取所有的省份 和 自治区
	 * @return
	 */
	public List<TCity> findProvince(){
		return super.findByField("level", 1);
	}

}
