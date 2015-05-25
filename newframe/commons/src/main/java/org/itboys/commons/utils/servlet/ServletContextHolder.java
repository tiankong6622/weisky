package org.itboys.commons.utils.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 保存servlet 相关对象
 * @author ChenJunhui
 */
public class ServletContextHolder {

	private static final ThreadLocal<ServletData> manager = new ThreadLocal<ServletData>();
	
	public static void prepare(HttpServletRequest request,HttpServletResponse response){
		ServletData sd = new ServletData();
		sd.setRequest(request);
		sd.setResponse(response);
		manager.set(sd);
	}
	
	/**
	 * 获取request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return manager.get().getRequest();
	}
	
	/**
	 * 获取response
	 * @return
	 */
	public static HttpServletResponse getResponse(){
		return manager.get().getResponse();
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession(){
		return manager.get().getRequest().getSession();
	}
	
	/**
	 * 获取servletContext
	 * @return
	 */
	public static ServletContext getServletContext(){
		return manager.get().getRequest().getSession().getServletContext();
	}
	
	public static void clear() {
		manager.remove();
	}
	
	public static ServletData getServletData(){
		return manager.get();
	}
}
