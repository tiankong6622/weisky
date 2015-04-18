package com.hz.sunday.xccf.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.dto.page.PageQueryUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.sunday.xccf.bo.MessageInfoBO;
import com.hz.sunday.xccf.constants.ColumnType;
import com.hz.sunday.xccf.orm.MessageInfo;

/**
 * 新闻中心
 * 
 * @author huanglei
 * @date 2015年4月14日
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/xccf/news")
public class NewsController extends BaseController {
	
	private static final String WEBSITE_NEWS = "/website/news";
	
	@Autowired
	private MessageInfoBO messageInfoBO;

	/**
	 * 新闻中心
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("columnValue", ColumnType.NEWS_TYPE);
		
		Map<String, Object> queryMap = PageQueryUtils.preparePage(request);
		queryMap.put("mtype", ColumnType.NEWS_TYPE);
		
		
		List<MessageInfo> newsList = messageInfoBO.getList(queryMap);
		Long count = messageInfoBO.getCount(queryMap);
		
		model.addAttribute("newsList", newsList);
		model.addAttribute("count", count); 

		return WEBSITE_NEWS;
	}

}
