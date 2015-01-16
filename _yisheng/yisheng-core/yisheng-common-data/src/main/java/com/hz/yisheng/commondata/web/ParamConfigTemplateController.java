package com.hz.yisheng.commondata.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.constants.CommonConstants;
import org.javafans.common.utils.number.ToNumberUtils;
import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.dto.page.Page;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.springmvc.controller.WebControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.yisheng.commondata.annotation.AdminPermissionCheck;
import com.hz.yisheng.commondata.annotation.ProjectPermissionCheck;
import com.hz.yisheng.commondata.bo.ParamConfigTemplateBO;
import com.hz.yisheng.commondata.orm.ParamConfigTemplate;

@Controller
@RequestMapping(value="/config/param")
public class ParamConfigTemplateController {

	@Autowired
	private ParamConfigTemplateBO paramConfigTemplateBO;
	
	@RequestMapping(value = "/admin/list")
	@AdminPermissionCheck("param:list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		final Map<String,Object> sqlMap = QueryParamUtils.builderQueryMap(request);//组装查询参数
		Page<ParamConfigTemplate> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = paramConfigTemplateBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	@RequestMapping(value = "/project/list")
	@ProjectPermissionCheck("param:list")
	public void Prolist(HttpServletRequest request,HttpServletResponse response){
		final Map<String,Object> sqlMap = QueryParamUtils.builderQueryMap(request);//组装查询参数
		Page<ParamConfigTemplate> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = paramConfigTemplateBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	
	@RequestMapping("/project")
	@ProjectPermissionCheck("param:toList")
	public String project(HttpServletRequest request,HttpServletResponse response,Model model){
		Long projectId = ToNumberUtils.getLongValue(request.getParameter("projectId"));
		String appKey = request.getParameter("appKey");
		Integer type = ToNumberUtils.getIntegerValue(request.getParameter("filter_I_type"));
		model.addAttribute("projectId", projectId);
		model.addAttribute("appKey", appKey);
		model.addAttribute("type", type);
		return "/project/configtemplate/list";
	}
	
	@RequestMapping("/getConfig/{id}")
	public void getConfig(@PathVariable("id") Long id,HttpServletResponse response){
		ParamConfigTemplate configCodes = paramConfigTemplateBO.findById(id);
		paramConfigTemplateBO.findById(id);
		AjaxUtils.renderJson(response, configCodes);
	}
	
	/**
	 * 异步保存
	 */
	@RequestMapping("/admin/save")
	@AdminPermissionCheck("param:save")
	public void save(@ModelAttribute ParamConfigTemplate pconfig,HttpServletRequest request,HttpServletResponse response){
		try {
			if(pconfig.getId()==null){
				paramConfigTemplateBO.insert(pconfig);
			}else{
				paramConfigTemplateBO.update(pconfig);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	
	/**
	 * project异步保存
	 * @param pconfig
	 * @param request
	 * @param response
	 */
	@RequestMapping("/project/save")
	@ProjectPermissionCheck("param:save")
	public void saveByProject(@ModelAttribute ParamConfigTemplate pconfig,HttpServletRequest request,HttpServletResponse response){
		save(pconfig, request, response);
	}
	
	
	/**
	 * 异步删除
	 */
	@RequestMapping("/delete/{id}")
	@AdminPermissionCheck("param:delete")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		AjaxUtils.renderText(response,String.valueOf(paramConfigTemplateBO.delete(id)));
	}
	
	/**
	 * 异步删除
	 */
	@RequestMapping("/project/delete/{id}")
	@ProjectPermissionCheck("param:delete")
	public void deleteByProject(@PathVariable("id") Long id,HttpServletResponse response){
		delete(id, response);
	}
}
