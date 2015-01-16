package org.javafans.dto.page;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.javafans.common.utils.number.ToNumberUtils;
import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.resources.ResourceConfig;

/**
 * 分页查询封装
 * @author ChenJunHui
 *
 */
public class PageQueryUtils {
	

	public static final int DEFAULT_PAGE_SIZE = 20; //分页默认条数 
	
	public static final int ALLOW_MAX_PAGE_SIZE = 100;//允许的最大分页条数
	
	/**
	 * 分页页码的key
	 * 从 resource-config.xml里取
	 */
	public static String PAGE_NO_KEY = null;
	/**
	 * 分页条数的key
	 * 从 resource-config.xml里取
	 */
	public static String PAGE_SIZE_KEY = null;
	
	static{
		try{
			PAGE_NO_KEY = ResourceConfig.getSysConfig("pageNoKey");
			PAGE_SIZE_KEY = ResourceConfig.getSysConfig("pageSizeKey");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static final String ROW_START="rowStart";
	public static final String PAGE_SIZE="pageSize";
	public static final String PAGE_NO="pageNo";

	/**
	 * 分页查询封装
	 * @param page
	 * @param queryMap
	 * @param pageQuery
	 */
	public static <T> Page<T> pageQuery(Page<T> page,Map<String,Object> queryMap,PageQuery<T> pageQuery){
		Long count = pageQuery.count();
		Long first = page.getFirst();
		if(first!=null && first>=count){
			page.setResult(new ArrayList<T>());
		}
		if(first==null || first<=1){
			first = 1L;
		}
		queryMap.put(ROW_START, first-1L);
		queryMap.put(PAGE_SIZE, page.getPageSize());
		page.setTotalCount(count);
		page.setResult(pageQuery.list());
		return page;
	}
	
	/**
	 * 已经调用过 preparePageQueryParam(HttpServletRequest request,Integer pageSize,String pageKey) 方法的map可以走这个
	 * @param queryMap
	 * @param pageQuery
	 * @return
	 */
	public static <T> Page<T> pageQuery(Map<String,Object> queryMap,PageQuery<T> pageQuery){
		Page<T> page = new Page<T>();
		Long count = pageQuery.count();
		Long first = (Long) queryMap.get(ROW_START);
		if(first!=null && first>=count){
			page.setResult(new ArrayList<T>());
		}
		if(first==null || first<=1){
			first = 1L;
		}
		page.setTotalCount(count);
		page.setResult(pageQuery.list());
		page.setPageNo((Integer) queryMap.get(PAGE_NO));
		page.setPageSize((Integer) queryMap.get(PAGE_SIZE));
		return page;
	}
	
	/**
	 * 组装分页查询参数
	 * @param request
	 * @param pageSize
	 * @param pageKey
	 * @return
	 */
	public static Map<String,Object>  preparePageQueryParam(HttpServletRequest request,Integer pageSize,String pageKey){
		//分页页码的key 不传分页key的时候 是 pageNo 传了的话 是 page.pageNo page1.pageNo之类的格式
		String pageNoKey = (StringUtils.isBlank(pageKey)?PAGE_NO_KEY:pageKey+"."+PAGE_NO_KEY);
		Integer pageNo = ToNumberUtils.getIntegerValue(request.getParameter(pageNoKey));
		if(pageNo==null || pageNo<1){
			pageNo = 1;
		}
		//分页size的key 不传分页key的时候 是 pageSize 传了的话 是  page.pageSize page1.pageSizeeNo之类的格式
		String pageSizeKey = (StringUtils.isBlank(pageKey)?PAGE_SIZE_KEY:pageKey+"."+PAGE_SIZE_KEY);
		Integer page_size = ToNumberUtils.getIntegerValue(request.getParameter(pageSizeKey));
		if(page_size==null || page_size<1 || page_size>ALLOW_MAX_PAGE_SIZE){
			page_size = pageSize;
		}
		long rowStart = (pageNo-1)*page_size;
		//组装查询参数
		Map<String,Object> sqlMap = QueryParamUtils.builderQueryMap(request);
		sqlMap.put(ROW_START, rowStart);
		sqlMap.put(PAGE_NO, pageNo);
		sqlMap.put(PAGE_SIZE, page_size);
		return sqlMap;
	}
	
	/**
	 * 将请求转分页对象
	 * @param request
	 * @return
	 */
	public static Map<String,Object> preparePage(HttpServletRequest request){
		return preparePageQueryParam(request,DEFAULT_PAGE_SIZE,null);
	}
	
	public static Map<String,Object> preparePage(HttpServletRequest request,Integer pageSize){
		return preparePageQueryParam(request,(pageSize==null || pageSize<1 || pageSize>ALLOW_MAX_PAGE_SIZE)?DEFAULT_PAGE_SIZE:pageSize,null);
	}
}
