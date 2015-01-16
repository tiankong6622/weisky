package org.javafans.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.javafans.resources.ResourceConfig;

public class JsonPageUtils {

	public static final String COUNT_KEY = ResourceConfig.getSysConfig("countKey");
	public static final String COUNT_ROWS = ResourceConfig.getSysConfig("rows");
	
	/**
	 * 异步render分页的json结果
	 * @param count
	 * @param result
	 * @param response
	 */
	public  static <T>  void renderJsonPage(Long count,List<T> result,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>(2);
		map.put(COUNT_KEY, count);
		map.put(COUNT_ROWS, result);
		AjaxUtils.renderJson(response, map);
	}
	
	/**
	 * 异步render分页的json结果
	 * @param count
	 * @param result
	 * @param response
	 */
	public  static <T>  void renderJsonPage(Long count,T[] result,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>(2);
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
