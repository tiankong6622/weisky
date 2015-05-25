package org.itboys.member.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.itboys.framework.query.QueryParamUtils;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.entity.BaseLongIdEntity;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;

@Service
public abstract class BaseMemberService<T, K> extends BaseService<T, K> {
	@Resource(name="memberDS")
	private MongoDataSource memberrelAuthDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberrelAuthDataSource;
	}
	/**
	 * 带分页的查询(framwork)
	 * @param param
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public org.itboys.framework.query.Page<T> pageQuery_framwork(HttpServletRequest request){
		Map<String,Object> param = QueryParamUtils.builderQueryMap(request);
		param.put("isDeleted", 1);
		org.itboys.framework.query.Page<T> page = (org.itboys.framework.query.Page<T>) org.itboys.framework.query.PageQueryUtils.preparePage(request);
		
		 //统计总数  
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);
		}
		final long count = getMongoDataSource().getCount(query);
		 //返回列表  
		final List<T> list = query
							.asList();
		
		return new org.itboys.framework.query.Page<T>(list,page.getPageNo(),page.getPageSize(),count);
	}
//	public List<T> list(Map<String, Object> param)
//	{
//		Iterator<String> ite = param.keySet().iterator();
//		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
//		while (ite.hasNext()) {
//			String key = ite.next();
//			Object value = param.get(key);
//			if(!key.equals("orderByKey")&& !key.equals("rowStart")&& !key.equals("pageSize")&& !key.equals("pageNo")){
//				query = query.filter(key, value);
//			}
//		}
//		query = query
//				.order(StringUtils.isEmpty((String)param.get("orderByKey"))?"id":(String)param.get("orderByKey"));
//		/*if(StringUtils.isEmpty((String)param.get("rowStart"))){
//			query = query.offset(ToNumberUtils.getIntegerValue("rowStart"));
//		}
//		if(StringUtils.isEmpty((String)param.get("pageSize"))){
//			query = query.limit((Integer)param.get("pageSize"));
//		}*/
//		return query.asList();
//	}
}
