package org.javafans.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.javafans.resources.ResourceConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * java对象转JSON
 * @author WeiSky
 *
 */
public class JSONUtils {

	public static ObjectMapper  mapper= new ObjectMapper();
	public static String jsonString = null;
	public static final String COUNT_KEY = ResourceConfig.getSysConfig("countKey");
	public static final String COUNT_ROWS = ResourceConfig.getSysConfig("rows");
	
	/**
	 * 对象转JSON
	 * @param response
	 * @param obj
	 */
	public static void objToJSON(HttpServletResponse response,final Object obj) {
		try {
			setResponse(response);
			jsonString = mapper.writeValueAsString(obj);
			PrintWriter out = response.getWriter();
			out.print(jsonString);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static <T> void rendJsonPage(Long count,List<T> list,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>(2);
		map.put(COUNT_KEY, count);
		map.put(COUNT_ROWS, list);
		objToJSON(response,map);
	}
	
	/**
	 * 设置编码格式
	 * @param response
	 */
	public static void setResponse(HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
	}
}
