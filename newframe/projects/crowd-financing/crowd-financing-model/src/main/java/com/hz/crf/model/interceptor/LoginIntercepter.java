package com.hz.crf.model.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.framework.spring.interceptor.ServletContextInterceptor;

/**
 * 登陆验证拦截器
 * 
 * @author huml
 * 
 */
public class LoginIntercepter extends ServletContextInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		if(url.equals("/admin/doLogin") || url.equals("/yzm") || url.contains("/") ){
			return super.preHandle(request, response, handler);
		}
		try {
			Long id = (Long)request.getSession().getAttribute("au");
			if (id == null) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return super.preHandle(request, response, handler);

	}

}
