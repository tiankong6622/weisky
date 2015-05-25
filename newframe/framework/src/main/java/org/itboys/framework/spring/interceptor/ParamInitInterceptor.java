package org.itboys.framework.spring.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.framework.resource.ResourceConfig;


/**
 * 参数初始化拦截器 继承 ServletContextInterceptor 同时拥有将servlet API 放置全局变量中的功能  
 * 将拦截器的第一个位置
 * @author ChenJunHui
 *
 */
public class ParamInitInterceptor extends ServletContextInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//将全局资源配置放置于request中 页面上可以直接通过 $!{key} 获取变量
		Map<String,String> sysResourceMap = ResourceConfig.getSysResourceMap();
		Iterator<Map.Entry<String,String>> iter = sysResourceMap.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String,String> entry = iter.next();
			request.setAttribute(entry.getKey(), entry.getValue());
		}
		return super.preHandle(request, response, handler);
	}
}
