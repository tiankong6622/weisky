package org.itboys.mongodb.utils.query;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.itboys.commons.utils.number.ToNumberUtils;
import org.itboys.mongodb.utils.page.PageQueryParam;

/**
 * 分页查询封装
 * @author WeiSky
 *
 */
public class PageQueryUtils {
	
	/* 分页默认条数   */
	public static final int DEFAULT_PAGE_SIZE = 20; 
	
	/* 分页开始位置*/
	public static final String ROW_START="pageIndex";
	
	/* 每页条数(分页偏移量) */
	public static final String PAGE_SIZE="pageSize";
	
	/* 排序字段   */
	public static final String SORT_FIELD = "sortField";
	
	/* 排序类型  */
	public static final String SORT_ORDER = "sortOrder";
	
	public static final String PAGE = "page";
	
	public static final String ROWS = "rows";

	
	/**
	 * 组装分页查询参数 jqgrid
	 * @param request
	 * @param pageSize
	 * @param pageKey
	 * @return
	 */
	/*public static PageQueryParam<T>  preparePageQueryParam(HttpServletRequest request){
		PageQueryParam<T> query = new PageQueryParam<T>();
		
		 //从头信息中获取分页的起始值  
		Integer pageIndex = ToNumberUtils.getIntegerValue(request.getParameter(ROW_START));
		if(pageIndex==null|| pageIndex<1){ 
			pageIndex = 1;
		}
		
		// 从头信息中获取分页的偏移量,若为空，则使用默认值   
		Integer pageSize = ToNumberUtils.getIntegerValue(request.getParameter(PAGE_SIZE));
		if(pageSize==null || pageSize<1 ){
			pageSize = DEFAULT_PAGE_SIZE;
		}
		
		//从头信息中获取排序字段  
		String sortField = request.getParameter(SORT_FIELD);
		//从头信息中获取排序类型  
		String sortOrder = request.getParameter(SORT_ORDER);
		
		//设置分页相关参数  
		query.setPageIndex(pageIndex);
		query.setPageSize(pageSize);
		if(!StringUtils.isBlank(sortField)){
			query.setSortField(sortField);
		}
		if(!StringUtils.isBlank(sortField)){
			query.setSortOrder(sortOrder);
		}
		
		return query;
	}*/
	
	/**
	 * 组装分页查询参数 easyUI
	 * @param request
	 * @param pageSize
	 * @param pageKey
	 * @return
	 */
	public static PageQueryParam<T>  preparePageQueryParam(HttpServletRequest request){
		PageQueryParam<T> query = new PageQueryParam<T>();

	    Integer pageNO = ToNumberUtils.getIntegerValue(request.getParameter(PAGE) == null ? Integer.valueOf(1) : request.getParameter(PAGE));

	    Integer pageSize = ToNumberUtils.getIntegerValue(request.getParameter(ROWS));
	    if ((pageSize == null) || (pageSize.intValue() < 1)) {
	      pageSize = Integer.valueOf(DEFAULT_PAGE_SIZE);
	    }

	    int rowStart = (pageNO.intValue() - 1) * pageSize.intValue();

	    String sortField = request.getParameter(SORT_FIELD);
	    if(StringUtils.isEmpty(sortField)){
	    	sortField = "id";
	    }
	    String sortOrder = request.getParameter(SORT_ORDER);

	    query.setPageIndex(Integer.valueOf(rowStart));
	    query.setPageSize(pageSize);
	    if (!StringUtils.isBlank(sortField)) {
	      query.setSortField(sortField);
	    }
	    	
	    if (!StringUtils.isBlank(sortField)) {
	      query.setSortOrder(sortOrder);
	    }

	    return query;
	}
	
	/**
	 * 将请求转分页对象
	 * @param request
	 * @return
	 */
	public static PageQueryParam<T> preparePage(HttpServletRequest request){
		return preparePageQueryParam(request);
	}
	
}
