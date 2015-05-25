package org.itboys.framework.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.itboys.commons.utils.servlet.ServletContextHolder;

/**
 * 把servletApi 存储在线程变量的拦截器 一般放在spring 3 MVC 拦截器的第一个位置
 * @author ChenJunhui
 *
 */
public class ServletContextInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		ServletContextHolder.prepare(request, response);
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		ServletContextHolder.clear();
	}
}
