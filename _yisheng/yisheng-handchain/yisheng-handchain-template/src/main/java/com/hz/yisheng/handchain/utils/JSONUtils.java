package com.hz.yisheng.handchain.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * java对象转JSON
 * @author WeiSky
 *
 */
public class JSONUtils {

	public static ObjectMapper  mapper= new ObjectMapper();
	public static String jsonString = null;
	
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
	
	/**
	 * 对象转JSON
	 * @param response
	 * @param obj
	 */
	public static String objToJSONReturnString(HttpServletResponse response,final Object obj) {
		try {
			setResponse(response);
			jsonString = mapper.writeValueAsString(obj);
			return jsonString;
		} catch (IOException e) {
			e.printStackTrace();
			return "[]";
		}
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
