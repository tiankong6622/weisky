package com.hz.crf.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.hz.crf.model.orm.ProductType;
/**
 * 众筹项目的分类
 * @author weisky
 *
 * May 28, 2015
 */
@Service
public class ProductTypeService extends BaseService<ProductType, Long>{
	
	private static final long serialVersionUID = -4191233913065783466L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<ProductType> getEntityClass() {
		// TODO Auto-generated method stub
		return ProductType.class;
	}
	
	/**
	 * 获取所有的分类
	 * @return
	 */
	public List<ProductType> findAll(){
		Query<ProductType> result = ds.find(getEntityClass());
		return result.asList();
	}
	
	/**
	 * 获取单条分类
	 * @param id
	 * @return
	 */
	public ProductType findById(Long id){
		return super.getByField("id", id);
	}

}
