package com.hz.crf.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.hz.crf.model.orm.ProductSupport;
/**
 * 众筹项目的支持/回报档次相关服务层
 * 
 * @author weisky
 *
 * May 28, 2015
 */
@Service
public class ProductSupportService extends BaseService<ProductSupport, Long>{
	
	private static final long serialVersionUID = -6039096326256176009L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<ProductSupport> getEntityClass() {
		return ProductSupport.class;
	}

	/**
	 * 根据众筹项目的ID 获取回报列表
	 * @param productId
	 * @return
	 */
	public List<ProductSupport> findByProductId(Long productId){
		return super.findByField("productId", productId);
	}
}
