package org.itboys.mongodb.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.entity.BaseEntity;
import org.itboys.mongodb.entity.BaseLongIdEntity;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.page.PageQueryParam;
import org.itboys.mongodb.utils.query.PageQueryUtils;
import org.itboys.mongodb.utils.query.QueryParamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.WriteResult;

/**
 * 业务层的父类了
 * 
 * @author 俊哥
 * @param <T>
 *            mongodb的实体对象
 * @param <K>
 *            主键类型
 */
public abstract class BaseService<T, K> implements Serializable
{
	private static final long serialVersionUID = 4206939863089464726L;
	
	protected  Logger logger = LoggerFactory.getLogger(getClass());
	public static final String PAGE_SIZE = "pageSize";
	public static final String PAGE_NO = "pageNo";
	public static final String ORDER_BY_KEY = "orderByKey";
	public static final String CONTAINS = "contains:";
	//public static final String IN = "in:";

	protected abstract MongoDataSource getMongoDataSource();

	protected abstract Class<T> getEntityClass();

	@Autowired
	protected IDGeneratorService iDGeneratorService;

	public T getById(K id)
	{
		return getMongoDataSource().get(getEntityClass(), id);
	}

	public void save(T t)
	{
		if (t instanceof BaseLongIdEntity)
		{
			BaseLongIdEntity entity = (BaseLongIdEntity) t;
			entity.setId(getNextId());
		}
		if (t instanceof BaseEntity)
		{
			BaseEntity entity = (BaseEntity) t;
			entity.setCt(System.currentTimeMillis());
			entity.setUt(System.currentTimeMillis());
		}
		getMongoDataSource().save(t);
	}

	public void batchSave(List<T> list)
	{
		for (T t : list)
		{
			if (t instanceof BaseLongIdEntity)
			{
				BaseLongIdEntity entity = (BaseLongIdEntity) t;
				entity.setId(getNextId());
			}
			if (t instanceof BaseEntity)
			{
				BaseEntity entity = (BaseEntity) t;
				entity.setCt(System.currentTimeMillis());
				entity.setUt(System.currentTimeMillis());
			}
		}
		getMongoDataSource().save(list);
	}

	/*
	 * public List<T> getListByList(List<K> list){ Query<T> query
	 * 
	 * }
	 */
	/**
	 * 判断对象含有filed的值是否存在
	 * 
	 * @param filed
	 * @param value
	 * @return
	 */
	public boolean exists(String filed, Object value)
	{
		return getMongoDataSource().createQuery(getEntityClass()).field(filed)
				.equal(value).get() != null;
	}

	public List<T> getByIds(List<K> ids)
	{
		return getMongoDataSource().getList(getEntityClass(), ids);
	}

	public Query<T> getQueryById(K id)
	{
		return getMongoDataSource().getQueryById(id, getEntityClass());
	}

	/**
	 * 查找字段名为field 的 并且值为 value的 value 可以为null
	 * 
	 * @param filed
	 * @param obj
	 * @return
	 */
	public List<T> findByField(String filed, Object value)
	{
		Query<T> query = getQueryByFiledAndValue(filed, value);

		return query.asList();
	}

	/**
	 * 查找字段名为field 的 并且值为 value的 value 可以为null;OrderBy "asc","-desc"
	 * 
	 * @param filed
	 * @param obj
	 * @return
	 */
	public List<T> findByField(String filed, Object value, String OrderBy,
			Integer pageNo, Integer pageSize)
	{
		Query<T> query = getQueryByFiledAndValue(filed, value);
		if (OrderBy != null && !OrderBy.equals(""))
		{
			query.order(OrderBy);
		}

		return query.asList();
	}

	/**
	 * 查找字段名为field 的 并且值为 value的 value 的唯一对象
	 * 
	 * @param filed
	 * @param obj
	 * @return
	 */
	public T getByField(String filed, Object value)
	{
		Query<T> query = getQueryByFiledAndValue(filed, value);
		return query.get();
	}

	public boolean delete(K id)
	{
		return getMongoDataSource().delete(id, getEntityClass());
	}

	/**
	 * When t contains a exactly Id,update this entity.
	 * 
	 * @param t
	 */
	public void update(T t)
	{
		// getMongoDataSource().update(t, ops);

		if (t instanceof BaseEntity)
		{
			BaseEntity entity = (BaseEntity) t;
			entity.setUt(System.currentTimeMillis());
		}
		getMongoDataSource().save(t);
	}

	public void batchUpdate(List<T> list)
	{
		for (T t : list)
		{
			if (t instanceof BaseEntity)
			{
				BaseEntity entity = (BaseEntity) t;
				entity.setUt(System.currentTimeMillis());
			}
		}
		getMongoDataSource().save(list);
	}

	public int update(K id, UpdateOperations<T> ops)
	{
		Query<T> query = getQueryById(id);
		return getMongoDataSource().update(query, ops).getUpdatedCount();
	}

	/**
	 * update 单个字段 更新 状态之类的用这个就可以了
	 * 
	 * @param id
	 * @param filed
	 * @param value
	 * @return
	 */
	public int update(K id, String filed, Object value)
	{
		UpdateOperations<T> ops = getMongoDataSource().createUpdateOperations(
				getEntityClass());
		ops.set(filed, value);
		return this.update(id, ops);
	}

	/**
	 * 对一些Number 子类类型的字段做自增 操作 比如 int double float bigdecimal 等 如果做减法 则传负数 类似于
	 * update set a=a+value
	 * 
	 * @param id
	 * @param filed
	 * @param value
	 * @return
	 */
	public int inc(K id, String filed, Number value)
	{
		UpdateOperations<T> ops = getMongoDataSource().createUpdateOperations(
				getEntityClass());
		ops.inc(filed, value);
		return this.update(id, ops);
	}

	public long getNextId()
	{
		return iDGeneratorService.getNextId(getEntityClass().getName());
	}
	@SuppressWarnings("unchecked")
	public Page<T> containpageQuery(Map<String, String> containsparam,
			Map<String, Object> param, HttpServletRequest request) {
		/* 统计总数 */
		PageQueryParam<T> page = (PageQueryParam<T>) PageQueryUtils
				.preparePage(request);
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);

		}
		if (containsparam != null && containsparam.size() != 0) {
			Iterator<String> itec = containsparam.keySet().iterator();
			while (itec.hasNext()) {
				String key = itec.next();
				String value = containsparam.get(key);
				query.field(key).contains(value.toString());

			}
		}
		final long count = getMongoDataSource().getCount(query);
		/* 返回列表 */
		final List<T> list = query.order(page.getSortField())
				.offset(page.getRowstart()).limit(page.getPageSize()).asList();

		return new Page<T>(list, page.getPageIndex(), page.getPageSize(), count);
	}
	/**
	 * 带分页的查询
	 * 
	 * @param param
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> pageQuery(HttpServletRequest request)
	{
		Map<String, Object> param = QueryParamUtils.builderQueryMap(request);
		param.put("isDeleted", 0);
		PageQueryParam<T> page = (PageQueryParam<T>) PageQueryUtils
				.preparePage(request);

		/* 统计总数 */
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext())
		{
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);
		}
		final long count = getMongoDataSource().getCount(query);
		/* 返回列表 */
		final List<T> list = query.order(page.getSortField())
				.offset(page.getRowstart()).limit(page.getPageSize()).asList();

		return new Page<T>(list, page.getPageIndex(), page.getPageSize(), count);
	}

	/**
	 * 带分页的查询
	 * 
	 * @param param
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> pageQuery(Map<String, Object> param,
			HttpServletRequest request)
	{
		/* 统计总数 */
		PageQueryParam<T> page = (PageQueryParam<T>) PageQueryUtils
				.preparePage(request);
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext())
		{
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);

		}
		final long count = getMongoDataSource().getCount(query);
		/* 返回列表 */
		final List<T> list = query.order(page.getSortField())
				.offset(page.getRowstart()).limit(page.getPageSize()).asList();

		return new Page<T>(list, page.getPageIndex(), page.getPageSize(), count);
	}

	private Query<T> getQueryByFiledAndValue(String filed, Object value)
	{
		Query<T> query = (value == null ? getMongoDataSource()
				.createQuery(getEntityClass()).field(filed).doesNotExist()
				: getMongoDataSource().createQuery(getEntityClass())
						.field(filed).equal(value));
		return query;
	}

	/**
	 * 根据条件查询前n条数据
	 * 
	 * @param conditions
	 * @param number
	 */
	public List<T> list(Map<String, Object> param, Integer number)
	{
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext())
		{
			query = prepareQuery(param, ite, query);

		}
		query = query.order(StringUtils.isEmpty((String) param
				.get("orderByKey")) ? "id" : (String) param.get("orderByKey"));
		if (number!=null)
		{
			query.limit(number);
		}
		return query.asList();
	}

	/**
	 * 根据条件查询返回列表
	 * 
	 * @param conditions
	 * @param number
	 */
	public List<T> list(Map<String, Object> param)
	{
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext())
		{
			query = prepareQuery(param, ite, query);
		}
		query = query.order(StringUtils.isEmpty((String) param
				.get(ORDER_BY_KEY)) ? "id" : (String) param.get(ORDER_BY_KEY));
		return query.asList();
	}
	/**
	 * 根据条件查询返回列表
	 * 
	 * @param conditions
	 * @param number
	 */
	public List<T> listWithPaging(Map<String, Object> param)
	{
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext())
		{
			query = prepareQuery(param, ite, query);
		}
		query = query.order(StringUtils.isEmpty((String) param
				.get(ORDER_BY_KEY)) ? "id" : (String) param.get(ORDER_BY_KEY));
		Integer pageNo=(Integer)param.get(PAGE_NO);
		Integer pageSize=(Integer)param.get(PAGE_SIZE);
		query.offset(pageNo == null ? 0 : (pageNo - 1)
				* (pageSize == null ? 10 : pageSize));
		query.limit(pageSize == null ? 10 : pageSize);
		return query.asList();
	}
	/**
	 * 
	 */
	public Long count(Map<String, Object> param)
	{
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext())
		{
			query = prepareQuery(param, ite, query);

		}
		return query.countAll();
	}

	// 此方法已被遗弃
	public List<T> contains(Map<String, Object> param)
	{
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext())
		{
			String key = ite.next();
			Object value = param.get(key);
			if (!key.equals("orderByKey"))
			{
				query = query.field(key).contains(value.toString());
			}
		}
		query = query.order(StringUtils.isEmpty((String) param
				.get("orderByKey")) ? "id" : (String) param.get("orderByKey"));
		return query.asList();
	}

	/**
	 * 根据条件查询返回分页
	 * 
	 * @param conditions
	 * @param number
	 */
	public Page<T> page(Map<String, Object> param)
	{
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext())
		{
			query = prepareQuery(param, ite, query);
		}
		Integer pageNo=(Integer)param.get(PAGE_NO);
		Integer pageSize=(Integer)param.get(PAGE_SIZE);
		String orderByKey= (String)param.get(ORDER_BY_KEY);
		return buildPage(query, pageNo, pageSize, orderByKey);
	}
	/**
	 * 
	 * @param query
	 * @param pageNo
	 * @param pageSize
	 * @param orderByKey
	 * @return
	 */
	protected Page<T> buildPage(Query<T> query, Integer pageNo,
			Integer pageSize, String orderByKey)
	{
		final long count = getMongoDataSource().getCount(query);
		query.offset(pageNo == null ? 0 : (pageNo - 1)
				* (pageSize == null ? 10 : pageSize));
		query.limit(pageSize == null ? 10 : pageSize);

		Page<T> page = new Page<T>();
		page.setAutoCount(true);
		page.setData(query.asList());
		page.setOrderBy(StringUtils.isEmpty(orderByKey) ? "id"
				: orderByKey);
		page.setPageNo(pageNo == null ? 1 :pageNo);
		page.setPageSize(pageNo == null ? 10 : pageNo);
		page.setTotal(count);
		return page;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public WriteResult delete(Map<String, Object> param)
	{
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext())
		{
			query = prepareQuery(param, ite, query);
		}
		return getMongoDataSource().delete(query);
	}

	/**
	 * 
	 * @param param
	 * @param ite
	 * @param query
	 * @return
	 */
	protected Query<T> prepareQuery(Map<String, Object> param,
			Iterator<String> ite, Query<T> query)
	{
		String key = ite.next();
		Object value = param.get(key);
		if (!key.equals(ORDER_BY_KEY) && !key.equals(PAGE_NO)
				&& !key.equals(PAGE_SIZE) && !key.startsWith(CONTAINS)
				)
		{
			query = query.filter(key, value);
		}
		if (key.startsWith(CONTAINS))
		{
			key = key.substring(CONTAINS.length(), key.length());
			query = query.field(key).contains(value.toString());
		}
		if (key.startsWith(ORDER_BY_KEY))
		{
			query.order(StringUtils.isEmpty((String) param.get(ORDER_BY_KEY)) ? "id"
					: (String) param.get(ORDER_BY_KEY));
		}
//		if (key.startsWith(IN))
//		{
//			key = key.substring(IN.length(), key.length());
//			query.field(key).in((List<Long>) value);
//			
//		}
		return query;
	}

	// 有重复方法
	// public List<T>getByValues(String field,List<Long>values){
	// Query<T>query=getMongoDataSource().createQuery(getEntityClass());
	// query.field(field).in(values);
	// return query.asList();
	// }

	/**
	 * 根据传入的实体非空字段进行更新.
	 * 
	 * @param t
	 */
	public void updateExceptEmpty(K id, T t)
	{
		try
		{
			if (t instanceof BaseLongIdEntity)
			{
				UpdateOperations<T> ops = getMongoDataSource()
						.createUpdateOperations(getEntityClass());
				Class<? extends Object> c = t.getClass();
				Field[] fields = c.getDeclaredFields();
				for (Field field : fields)
				{
					if (!field.getName().equals("serialVersionUID"))
					{
						field.setAccessible(true);
						Object obj = field.get(t);

						if (obj != null && !obj.toString().equals(""))
						{
							ops.set(field.getName(), obj);
						}

					}
				}
				// ops.set(filed, value);
				this.update(id, ops);
			}
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
