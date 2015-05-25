package com.hz.crf.model.service;

import java.util.Map;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.mongodb.utils.page.Page;
import org.springframework.stereotype.Service;

import com.hz.crf.model.orm.Product;

/**
 * 后台编辑或者会员发起的众筹项目
 * @author weisky
 *
 * May 28, 2015
 */
@Service
public class ProductService extends BaseService<Product, Long>{
	
	private static final long serialVersionUID = -6582726038690959754L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}

	/**
	 * 带分页的查询
	 * 
	 * @param param
	 * @param pageIndex : 起始下标
	 * @param pageSize ： 偏移量
	 * @return
	 */
	public Page<Product> findPageList(final Map<String, Object> param, Integer pageIndex, Integer pageSize){
		if (pageIndex==null || pageIndex == 0){
			pageIndex=1;
		}
		if (pageSize==null){
			pageSize=10;
		}
		long rowStart = (pageIndex-1)*pageSize;
		param.put("rowStart", rowStart);
		param.put("pageNo", pageIndex);
		param.put("pageSize", pageSize);
		return super.page(param);
	}
	
	/**
	 * 根据ID 获取一条详细信息
	 * 
	 * @param id
	 * @return
	 */
	public Product findById(Long id){
		return super.getById(id);
	}
	
}
