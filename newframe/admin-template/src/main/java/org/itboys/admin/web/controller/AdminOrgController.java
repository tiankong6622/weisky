package org.itboys.admin.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.admin.entity.AdminOrg;
import org.itboys.admin.service.AdminOrgService;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.query.QueryParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/org")
public class AdminOrgController extends BaseController {
	@Autowired
	private AdminOrgService adminOrgService;
	
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = QueryParamUtils.builderQueryMap(request);
		List<AdminOrg> list = adminOrgService.list(param);
		Long count = adminOrgService.count(param);
		JsonPageUtils.renderJsonPage(count, list, response);
	}
	
	@ResponseBody
	@RequestMapping("/getOrg/{id}")
	public void getOrg(@PathVariable("id")Long id,HttpServletResponse response){
		AjaxUtils.renderJson(response, adminOrgService.getById(id));
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public void save(@ModelAttribute AdminOrg adminOrg , HttpServletResponse response){
		try {
			if(adminOrg.getId() == 0l){
				adminOrgService.doSave(adminOrg);
			}else {
				adminOrgService.doUpdate(adminOrg);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("adminOrg save error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	@ResponseBody
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id")Long id,HttpServletResponse response){
		try {
			adminOrgService.deleteOrg(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("adminOrg delete error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

}
