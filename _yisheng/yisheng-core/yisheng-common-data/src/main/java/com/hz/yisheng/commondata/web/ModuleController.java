package com.hz.yisheng.commondata.web;

import java.util.List;
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

import com.google.common.collect.Maps;
import com.hz.yisheng.commondata.bo.ModuleBO;
import com.hz.yisheng.commondata.orm.Module;

/**
 * 功能模块控制器
 * 
 * @author GuoRui
 * 
 */
@Controller
@RequestMapping(value = "/module")
public class ModuleController extends BaseController{
	
	
	@Autowired
	private ModuleBO moduleBO;
	
	/**
	 * 分页查询
	 * @return
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		final Map<String,Object> sqlMap = PageQueryUtils.preparePage(request,10);
		 Page<Module> page = moduleBO.pageQuery(sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	
	/**
	 *  异步查询获取所有功能模块信息
	 * @return
	 */
	@RequestMapping("/getModuleList")
	public void getModuleList(HttpServletRequest request,HttpServletResponse response){
		List<Module> list = moduleBO.getAll();
		Map<String, Object>result = Maps.newHashMap();
		result.put("list", list);
		AjaxUtils.renderJson(response,result);
	}
	
	/**
	 * 异步获取单个用户对象
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getModule/{id}")
	public void getUser(@PathVariable("id") Long id,HttpServletResponse response){
		Module module = moduleBO.findById(id);
		AjaxUtils.renderJson(response, module);
	}
	
	/**
	 * 异步保存
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute Module module,HttpServletResponse response){
		try {
			if(module.getId()==null){
				moduleBO.insert(module);
			}else{
				moduleBO.update(module);
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
		AjaxUtils.renderText(response,String.valueOf(moduleBO.delete(id)));
	}
	
	
}
