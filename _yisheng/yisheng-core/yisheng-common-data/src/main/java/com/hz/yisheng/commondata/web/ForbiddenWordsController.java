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
import com.hz.yisheng.commondata.bo.ForbiddenWordsBO;
import com.hz.yisheng.commondata.orm.ForbiddenWords;


/**
 * 违禁词控制层
 * @author Liuguanjun
 */
@Controller
@RequestMapping(value = "/common/forbiddenwords")
public class ForbiddenWordsController {

	@Autowired
	private ForbiddenWordsBO forbiddenWordsBO;
	
	/**
	 * 异步获取列表
	 * @param response
	 */
	@RequestMapping(value = {"/admin/list","/project/list"})
	public void list(HttpServletRequest request,HttpServletResponse response){
		final Map<String,Object> sqlMap = QueryParamUtils.builderQueryMap(request);//组装查询参数
		Page<ForbiddenWords> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = forbiddenWordsBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	@RequestMapping("/project")
	public String project(HttpServletRequest request,HttpServletResponse response,Model model){
		Long projectId = ToNumberUtils.getLongValue(request.getParameter("projectId"));
		String appKey = request.getParameter("appKey");
		Integer type = ToNumberUtils.getIntegerValue(request.getParameter("filter_I_type"));
		model.addAttribute("projectId", projectId);
		model.addAttribute("appKey", appKey);
		model.addAttribute("type", type);
		return "/project/forbiddenwords/list";
	}
	
	/**
	 * 异步获取单个对象
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getForbiddenWords/{id}")
	public void getForbiddenWords(@PathVariable("id") Long id,HttpServletResponse response){
		ForbiddenWords entity = forbiddenWordsBO.getById(id);
		AjaxUtils.renderJson(response, entity);
	}
	
	
	/**
	 * 异步保存
	 */
	@RequestMapping("/admin/save")
	@AdminPermissionCheck("words:save")
	public void save(@ModelAttribute ForbiddenWords entity,HttpServletRequest request,HttpServletResponse response){
		try {
			if(entity.getId()==null){
				if(entity.getProjectId()==null || entity.getProjectId()==0L){
					entity.setProjectId(ForbiddenWords.DEFAULT_PROJECTID);
				}
				forbiddenWordsBO.insert(entity);
			}else{
				forbiddenWordsBO.update(entity);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 项目异步保存
	 * @param codes
	 * @param request
	 * @param response
	 */
	@RequestMapping("/project/save")
	@ProjectPermissionCheck("words:save")
	public void saveByProject(@ModelAttribute ForbiddenWords entity,HttpServletRequest request,HttpServletResponse response){
		save(entity, request, response);
	}
	
	
	/**
	 * 异步删除
	 */
	@RequestMapping("/delete/{id}")
	@AdminPermissionCheck("words:delete")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		AjaxUtils.renderText(response,String.valueOf(forbiddenWordsBO.delete(id)));
	}
	
	@RequestMapping("/project/delete/{id}")
	@ProjectPermissionCheck("words:delete")
	public void deleteByProject(@PathVariable("id") Long id,HttpServletResponse response){
		delete(id, response);
	}
}
