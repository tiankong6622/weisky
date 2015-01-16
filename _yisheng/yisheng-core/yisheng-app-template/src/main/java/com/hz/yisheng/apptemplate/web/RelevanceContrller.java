package com.hz.yisheng.apptemplate.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.yisheng.admin.utils.JSONUtils;
import com.hz.yisheng.apptemplate.bo.RelevanceBO;
import com.hz.yisheng.apptemplate.dto.Relevance;

@Controller
@RequestMapping("/admin/relevance")
public class RelevanceContrller extends BaseController{

	@Autowired
	private RelevanceBO relevanceBO;
	
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		List<Relevance> list = relevanceBO.getAll();
		JSONUtils.rendJsonPage(list != null && list.size()>0 ? Long.parseLong(list.size()+""):0L, list, response);
	}
}
