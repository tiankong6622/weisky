package com.hz.sunday.xccf.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * 论坛日程
 * 
 * @author huanglei
 * @date 2015年4月14日
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/xccf/program")
public class ProgramController extends BaseController {
	
	private static final String WEBSIETE_PROGRAM = "/website/program";
	
	@Autowired
	private MessageInfoBO messageInfoBO;

	/**
	 * 论坛日程
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("columnValue", ColumnType.PROGRAM_TYPE);

		Map<String, Object> queryMap = Maps.newHashMap();
		queryMap.put("menuType",  ColumnType.PROGRAM_TYPE);
		queryMap.put("startTime", new Date());
		queryMap.put("mtype", ColumnType.PROGRAM_TYPE);
		
		List<MessageInfo> programList = messageInfoBO.getList(queryMap);
		Long count = messageInfoBO.getCount(queryMap);
		
		model.addAttribute("programList", programList);
		model.addAttribute("count", count);
		
		return WEBSIETE_PROGRAM;
	}

}
