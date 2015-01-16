package org.javafans.web.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.javafans.common.utils.number.ToNumberUtils;
import org.javafans.dto.page.Page;
import static org.javafans.dto.page.PageQueryUtils.*;

/**
 * controller的基类 通用的方法可以放在这里 用作静态方法
 * @author ChenJunHui
 */
public abstract class WebControllerUtils {

	/**
	 * 分页查询初始话 
	 * @param request
	 * @param pageKey page在页面上的属性名称 比如 Page page1 ,Page page2 在一次提交有多个page的时候 传这个值 否则这个值为空
	 * @return
	 */
	public static <T> Page<T> preparePage(HttpServletRequest request,Integer pageSize,String pageKey){
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
		Page<T> page = new Page<T>(page_size);
		page.setPageNo(pageNo);
		return page;
	}
	
	/**
	 * 将请求转分页对象
	 * @param request
	 * @return
	 */
	public static <T> Page<T> preparePage(HttpServletRequest request){
		return preparePage(request,DEFAULT_PAGE_SIZE,null);
	}
	
	public static <T> Page<T> preparePage(HttpServletRequest request,Integer pageSize){
		return preparePage(request,(pageSize==null || pageSize<1 || pageSize>ALLOW_MAX_PAGE_SIZE)?DEFAULT_PAGE_SIZE:pageSize,null);
	}
	
}
