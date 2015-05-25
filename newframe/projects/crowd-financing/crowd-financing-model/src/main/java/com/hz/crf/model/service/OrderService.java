package com.hz.crf.model.service;

import java.util.Map;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.mongodb.utils.page.Page;

import com.hz.crf.model.orm.Order;

public class OrderService extends BaseService<Order, Long>{
	
	private static final long serialVersionUID = 7645085823407004976L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<Order> getEntityClass() {
		return Order.class;
	}

	/**
	 * 带分页的查询
	 * 
	 * @param param
	 * @param pageIndex : 起始下标
	 * @param pageSize ： 偏移量
	 * @return
	 */
	public Page<Order> findPageList(final Map<String, Object> param, Integer pageIndex, Integer pageSize){
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
	 * @param id
	 * @return
	 */
	public Order findById(Long id){
		return super.getById(id);
	}
	
	
}
