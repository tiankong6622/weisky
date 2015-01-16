package com.hz.yisheng.commondata.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.constants.CommonConstants;
import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.dto.page.Page;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.javafans.web.springmvc.controller.WebControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.yisheng.commondata.bo.KVConfigBO;
import com.hz.yisheng.commondata.orm.KVConfig;


@Controller
@RequestMapping(value="/common/kvconfig")
public class KVConfigController extends BaseController{

	@Autowired
	private KVConfigBO kVConfigBO;
	
	/**
	 * 异步获取列表
	 * @param response
	 */
	@RequestMapping(value = {"/admin/list","/project/list"})
	public void list(HttpServletRequest request,HttpServletResponse response){
		final Map<String,Object> sqlMap = QueryParamUtils.builderQueryMap(request);//组装查询参数
		sqlMap.put("appKey", request.getParameter("appKey"));
		Page<KVConfig> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = kVConfigBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	@RequestMapping("/getConfig/{id}")
	public void getConfig(@PathVariable("id") Long id,HttpServletResponse response){
		AjaxUtils.renderJson(response, kVConfigBO.findById(id));
	}
	
	
	@RequestMapping("/save")
	public void save(@ModelAttribute KVConfig config,HttpServletResponse response){
		try {
			if(config.getId()==null){	
				kVConfigBO.insert(config);	
			}else{
				kVConfigBO.update(config);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("kvconfig sav fail", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			kVConfigBO.delete(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	
	@RequestMapping("/project")
	public String project(HttpServletRequest request,HttpServletResponse response,Model model){
		String appKey = request.getParameter("appKey");
		model.addAttribute("appKey", appKey);
		return "/project/kvconfig/list";
	}
	

}
