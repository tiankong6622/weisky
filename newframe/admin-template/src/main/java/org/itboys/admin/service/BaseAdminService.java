package org.itboys.admin.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.itboys.admin.entity.BaseAdminEntity;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.entity.BaseLongIdEntity;
import org.itboys.mongodb.service.BaseService;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.page.PageQueryParam;
import org.itboys.mongodb.utils.query.PageQueryUtils;
import org.itboys.mongodb.utils.query.QueryParamUtils;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

public abstract class BaseAdminService<T, K> extends BaseService<T, K> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4363985619968752316L;

	public void save(T t){
		if(t instanceof BaseAdminEntity){
			BaseAdminEntity entity = initBaseAdminEntity(t);
			entity.setCr(AdminSessionHolder.getAdminUserId());
			entity.setUr(AdminSessionHolder.getAdminUserId());
			entity.setCt(System.currentTimeMillis());
			entity.setUt(System.currentTimeMillis());
		}
		super.save(t);
	}
	
	public void batchSave(List<T> list){
		for(T t:list){
			if(t instanceof BaseLongIdEntity){
				BaseAdminEntity entity = initBaseAdminEntity(t);
				entity.setCr(AdminSessionHolder.getAdminUserId());
				entity.setUr(AdminSessionHolder.getAdminUserId());
				entity.setCt(System.currentTimeMillis());
				entity.setUt(System.currentTimeMillis());
			}
		}
		super.batchSave(list);
	}
	
	public List<T> list(Map<String, Object> param){
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);
		}
		return query.asList();
	}
	public Long count(Map<String, Object> param){
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query = query.filter(key, value);
		}
		return getMongoDataSource().getCount(query);
	}
	
	/**
	 * 不需要考虑登入信息的保存
	 * @param t
	 */
	public void saveWithoutLogin(T t){
		if(t instanceof BaseAdminEntity){
			BaseAdminEntity entity = initBaseAdminEntity(t);
			entity.setCr(1L);
			entity.setUr(1L);
			entity.setCt(System.currentTimeMillis());
			entity.setUt(System.currentTimeMillis());
		}
		super.save(t);
	}
	
	/**
	 * 不需要考虑登入信息的批量保存
	 * @param t
	 */
	public void saveWithoutLogin(List<T> list){
		for(T t:list){
			if(t instanceof BaseLongIdEntity){
				BaseAdminEntity entity = initBaseAdminEntity(t);
				entity.setCr(1L);
				entity.setUr(1L);
				entity.setCt(System.currentTimeMillis());
				entity.setUt(System.currentTimeMillis());
			}
		}
		super.batchSave(list);
	}
	
	public int update(K id,UpdateOperations<T> ops){
		ops.set("ur", AdminSessionHolder.getAdminUserId());
		ops.set("ut", System.currentTimeMillis());
		return super.update(id, ops);
	}
	

	private BaseAdminEntity initBaseAdminEntity(T t) {
		BaseAdminEntity entity = (BaseAdminEntity)t;
		long now = System.currentTimeMillis();
		entity.setCt(now);
		entity.setUt(now);
		return entity;
	}
	
	/**
	 * 带分页的查询
	 * @param param
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> pageQuery(HttpServletRequest request){
		Map<String,Object> param = QueryParamUtils.builderQueryMap(request);
		PageQueryParam<T> page = (PageQueryParam<T>) PageQueryUtils.preparePage(request);
		
		/* 统计总数  */
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);
		}
		final long count = getMongoDataSource().getCount(query);
		/* 返回列表  */
		final List<T> list = query
							.order(page.getSortField())
							.offset(page.getPageIndex())
							.limit(page.getPageSize())
							.asList();
		
		return new Page<T>(list,page.getPageIndex(),page.getPageSize(),count);
	}
	
	/**
	 * 带分页的查询
	 * @param param
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> pageQuery2(HttpServletRequest request , Map<String,Object> param){
		PageQueryParam<T> page = (PageQueryParam<T>) PageQueryUtils.preparePage(request);
		
		/* 统计总数  */
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query = query.filter(key, value);
		}
		final long count = getMongoDataSource().getCount(query);
		/* 返回列表  */
		query = query
				.order(page.getSortField())
				.offset(page.getPageIndex())
				.limit(page.getPageSize());
		final List<T> list = query
							.asList();
		
		return new Page<T>(list, page.getPageIndex(), page.getPageSize(), count);
	}
	
	public void doDelete(K id){
		update(id, "isDeleted", 1);
	}
	
}
