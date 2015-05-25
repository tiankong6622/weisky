package org.itboys.admin.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.admin.entity.AdminModule;
import org.itboys.admin.service.AdminModuleService;
import org.itboys.admin.tools.AdminPermissionCheck;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

/**
 * 功能模块管理
 * @author huml
 *
 */
@RestController
@RequestMapping(value = "/admin/module")
public class AdminModuleController extends BaseController {

	@Autowired
	private AdminModuleService adminModuleService;
	
	/**
	 * 分页查询
	 * @return
	 */
	@AdminPermissionCheck("adminModule:list")
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Page<AdminModule> page = adminModuleService.pageQuery(request);
		JsonPageUtils.renderJsonPage(page.getTotal(), page.getData(), response);
	}
	
	
	/**
	 *  异步查询获取所有功能模块信息
	 * @return
	 */
	@AdminPermissionCheck("adminModule:getModuleList")
	@RequestMapping("/getModuleList")
	public void getModuleList(HttpServletRequest request,HttpServletResponse response){
		List<AdminModule> list = adminModuleService.getAll();
		Map<String, Object> result = Maps.newHashMap();
		result.put("list", list);
		AjaxUtils.renderJson(response,result);
	}
	
	/**
	 * 异步获取单个用户对象
	 * @param id
	 * @param response
	 */
	@AdminPermissionCheck("adminModule:getModule")
	@RequestMapping("/getModule/{id}")
	public void getUser(@PathVariable("id") Long id,HttpServletResponse response){
		AdminModule module = adminModuleService.getById(id);
		AjaxUtils.renderJson(response, module);
	}
	
	/**
	 * 异步保存
	 */
	@AdminPermissionCheck("adminModule:save")
	@RequestMapping("/save")
	public void save(@ModelAttribute AdminModule module,HttpServletResponse response){
		try {
			if(module.getId()==0l){
				adminModuleService.insert(module);
			}else{
				adminModuleService.doUpdate(module);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("admn module save error", e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
		
	}
	
	
	/**
	 * 异步删除
	 */
	@AdminPermissionCheck("adminModule:delete")
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			adminModuleService.doDeleted(id);
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("admn module delete error", e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
}
