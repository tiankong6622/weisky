package org.itboys.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.admin.entity.AdminPermission;
import org.itboys.admin.service.AdminPermissionService;
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

import org.itboys.admin.tools.AdminPermissionCheck;
/**
 * 权限
 * @author huml
 */
@RestController
@RequestMapping(value = "/admin/permission")
public class AdminPermissionController extends BaseController {

	@Autowired
	private AdminPermissionService adminPermissionService;
	/**
	 * 分页查询权限
	 * @return
	 */
	@RequestMapping("/list")
	@AdminPermissionCheck("adminPermission:list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Page<AdminPermission> page = adminPermissionService.pageQuery(request);
		JsonPageUtils.renderJsonPage(page.getTotal(),page.getTotalPages(), page.getData(), response);
	}
	
	/**
	 * 异步获取单个用户对象
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getPermission/{id}")
	@AdminPermissionCheck("adminPermission:getPermission")
	public void getUser(@PathVariable("id") Long id,HttpServletResponse response){
		AdminPermission permission = adminPermissionService.getById(id);
		AjaxUtils.renderJson(response, permission);
	}
	
	/**
	 * 异步保存
	 */
	@RequestMapping("/save")
	@AdminPermissionCheck("adminPermission:save")
	public void save(@ModelAttribute AdminPermission permission,HttpServletResponse response){
		try {
			if(permission.getId()==0l){
				adminPermissionService.insert(permission);
			}else{
				adminPermissionService.doUpdate(permission);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
		
	}
	
	
	/**
	 * 异步删除
	 */
	@RequestMapping("/delete/{id}")
	@AdminPermissionCheck("adminPermission:delete")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			adminPermissionService.doDelete(id);
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("admin permission delete error", e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	
	
}
