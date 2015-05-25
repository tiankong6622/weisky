package com.hz.crf.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.hz.crf.model.orm.City;

/**
 * 省市县
 * @author weisky
 *
 * May 28, 2015
 */
@Service
public class CityService extends BaseService<City, Long>{

	private static final long serialVersionUID = -7599060074089185789L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<City> getEntityClass() {
		return City.class;
	}
	
	/**
	 * 根据ID 获取一条记录
	 * @param id
	 * @return
	 */
	public City findById(Long id){
		return super.getByField("id", id);
	}
	
	/**
	 * 根据父ID 获取下面的子城市
	 * 
	 * @param parentId
	 * @return
	 */
	public List<City> findByParentId(Long parentId){
		return super.findByField("parentId", parentId);
	}
	
	/**
	 * 获取所有的省份 和 自治区
	 * @return
	 */
	public List<City> findProvince(){
		return super.findByField("level", 1);
	}

}
