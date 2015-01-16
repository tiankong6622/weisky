package com.hz.yisheng.commondata.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.constants.CommonConstants;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQueryUtils;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.yisheng.commondata.bo.PermissionBO;
import com.hz.yisheng.commondata.orm.Permission;

/**
 * 权限控制器
 * @author GuoRui
 */
@Controller
@RequestMapping(value = "/permission")
public class PermissionController extends BaseController{

	@Autowired
	private PermissionBO permissionBO;
	/**
	 * 分页查询权限
	 * @return
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		final Map<String,Object> sqlMap = PageQueryUtils.preparePage(request,10);//组装查询参数
		Page<Permission> page = permissionBO.pageQuery(sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 异步获取单个用户对象
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getPermission/{id}")
	public void getUser(@PathVariable("id") Long id,HttpServletResponse response){
		Permission permission = permissionBO.findById(id);
		AjaxUtils.renderJson(response, permission);
	}
	
	/**
	 * 异步保存
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute Permission permission,HttpServletResponse response){
		try {
			if(permission.getId()==null){
				permissionBO.insert(permission);
			}else{
				permissionBO.update(permission);
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
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		AjaxUtils.renderText(response,String.valueOf(permissionBO.delete(id)));
	}
	
}
