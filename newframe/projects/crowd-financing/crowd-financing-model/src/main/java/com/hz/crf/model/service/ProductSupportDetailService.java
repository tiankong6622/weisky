package com.hz.crf.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.hz.crf.model.orm.ProductSupportDetail;
/**
 * 支持众筹项目的详情
 * @author weisky
 *
 * Jun 1, 2015
 */
@Service
public class ProductSupportDetailService extends BaseService<ProductSupportDetail, Long>{
	
	private static final long serialVersionUID = -6984695735276250309L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<ProductSupportDetail> getEntityClass() {
		return ProductSupportDetail.class;
	}
	
	/**
	 * 根据众筹项目ID  获取列表
	 * @param productId
	 * @return
	 */
	public List<ProductSupportDetail> findByProductId(Long productId){
		return super.findByField("productId", productId);
	}
	
	/**
	 * 根据会员ID  获取列表
	 * @param memeberId
	 * @return
	 */
	public List<ProductSupportDetail> findByMemeberId(Long memberId){
		return super.findByField("memberId", memberId);
	}
	

}
