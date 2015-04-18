package com.hz.sunday.xccf.web;

import java.util.Date;
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

import com.google.common.collect.Maps;
import com.hz.sunday.xccf.bo.MessageInfoBO;
import com.hz.sunday.xccf.constants.ColumnType;
import com.hz.sunday.xccf.orm.MessageInfo;

/**
 * 首页
 * 
 * @author huanglei
 * @date 2015年4月14日
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/xccf")
public class WebIndexController extends BaseController {
	
	private static final String WEBSITE_INDEX = "/website/index";
	
	@Autowired
	private MessageInfoBO messageInfoBO;

	/**
	 * 首页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("columnValue", ColumnType.INDEX_TYPE);
		
		// 新闻中心信息
		Map<String, Object> newsMap = PageQueryUtils.preparePage(request);
		newsMap.put("mtype", ColumnType.NEWS_TYPE);
		newsMap.put("pageSize", 5);
		List<MessageInfo> newsList = messageInfoBO.getList(newsMap);
		Long newsCount = messageInfoBO.getCount(newsMap);
		model.addAttribute("newsList", newsList);
		model.addAttribute("newsCount", newsCount);
		
		// 论坛日程
		Map<String,Object> proMap = Maps.newHashMap();
		proMap.put("menuType",  ColumnType.PROGRAM_TYPE);
		proMap.put("startTime", new Date());
		proMap.put("mtype", ColumnType.PROGRAM_TYPE);
		List<MessageInfo> programList = messageInfoBO.getList(proMap);
		Long proCount = messageInfoBO.getCount(proMap);
		model.addAttribute("programList", programList);
		model.addAttribute("proCount", proCount);
		
		// 轮番图片
		Map<String,Object> turnMap = Maps.newHashMap();
		turnMap.put("mtype", ColumnType.TURN_IMAGE);
		List<MessageInfo> imgList = messageInfoBO.getList(turnMap);
		model.addAttribute("imgList", imgList);
		
		return WEBSITE_INDEX;
	}

}
