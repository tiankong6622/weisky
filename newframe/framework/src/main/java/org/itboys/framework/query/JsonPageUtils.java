package org.itboys.framework.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.resource.ResourceConfig;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.context.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonPageUtils {
	
	public static final String COUNT_KEY = "total";
	public static final String COUNT_ROWS = "rows";
	public static final String COUNT_PAGES = "pages";
	
	/**
	 * 异步render分页的json结果(easyui)
	 * @param count
	 * @param result
	 * @param response
	 */
	public  static <T>  void renderJsonPage(Long count,List<T> result,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>(3);
		map.put(COUNT_KEY, count);
		map.put(COUNT_ROWS, result);
		AjaxUtils.renderJson(response, map);
	}
	
	/**
	 * 异步render分页的json结果(jqueryui)
	 * @param count
	 * @param result
	 * @param response
	 */
	public  static <T>  void renderJsonPage(Long total,Long pages,List<T> result,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>(3);
		map.put(COUNT_KEY, total);
		map.put(COUNT_ROWS, result);
		map.put(COUNT_PAGES, pages);
		AjaxUtils.renderJson(response, map);
	}
	
	/**
	 * 异步render分页的json结果
	 * @param count
	 * @param result
	 * @param response
	 */
	public  static <T>  void renderJsonPage(Long count,T[] result,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>(3);
		map.put(COUNT_KEY, count);
		int length = result==null?0:result.length;
		List<T> list = new ArrayList<T>(length);
		if(result!=null){
			for(int i = 0; i < length; i++){
				list.add(result[i]);
			}
		}
		map.put(COUNT_ROWS, list);
		AjaxUtils.renderJson(response, map);
	}
}
