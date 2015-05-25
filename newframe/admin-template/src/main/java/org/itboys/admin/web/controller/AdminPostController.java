package org.itboys.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.admin.entity.AdminPost;
import org.itboys.admin.service.AdminPostService;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/post")
public class AdminPostController extends BaseController{
	@Autowired
	private AdminPostService adminPostService;
	
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Page<AdminPost> page = adminPostService.pageQuery(request);
		JsonPageUtils.renderJsonPage(page.getTotal(), page.getData(), response);
	}
	
	@ResponseBody
	@RequestMapping("/getAll")
	public void getAll(HttpServletResponse response){
		List<AdminPost> list = adminPostService.getAll();
		JsonPageUtils.renderJsonPage(Long.valueOf(list.size()), list, response);
	}
	
	@ResponseBody
	@RequestMapping(value="/getPost/{id}", method=RequestMethod.POST)
	public void getById(@PathVariable("id")Long id , HttpServletResponse response){
		AjaxUtils.renderJson(response, adminPostService.getById(id));
	}
	
	@ResponseBody
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(@ModelAttribute AdminPost adminPost,HttpServletResponse response){
		try {
			if(adminPost.getId() == 0l){
				adminPostService.save(adminPost);
			}else {
				adminPostService.update(adminPost);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("AdminPost save error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public void delete(@PathVariable("id")Long id , HttpServletResponse response){
		try {
			adminPostService.deletePost(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("AdminPost delete error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

}
