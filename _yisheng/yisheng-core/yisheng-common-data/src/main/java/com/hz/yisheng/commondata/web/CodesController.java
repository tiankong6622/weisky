package com.hz.yisheng.commondata.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.constants.CommonConstants;
import org.javafans.common.dto.Option;
import org.javafans.common.utils.number.ToNumberUtils;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.yisheng.commondata.annotation.AdminPermissionCheck;
import com.hz.yisheng.commondata.bo.CodesBO;
import com.hz.yisheng.commondata.bo.CodesConfigBO;
import com.hz.yisheng.commondata.dto.CodesDTO;
import com.hz.yisheng.commondata.orm.Codes;
import com.hz.yisheng.commondata.orm.CodesConfig;


/**
 * 数据字典控制器
 * @author Liuguanjun
 */
@Controller
@RequestMapping(value = "/common/codes")
public class CodesController  extends BaseController{

	@Autowired
	private CodesBO codesBO;
	@Autowired
	private CodesConfigBO codesConfigBO;
	
	/**
	 * 异步获取列表
	 * @param response
	 */
	@RequestMapping(value = {"/admin/list","/project/list"})
	public void list(HttpServletRequest request,HttpServletResponse response){
		final Map<String,Object> sqlMap = QueryParamUtils.builderQueryMap(request);//组装查询参数
		Page<Codes> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = codesBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 异步获取列表
	 * @param response
	 */
	@RequestMapping("/tree")
	public void tree(@RequestParam("projectId") Long projectId, HttpServletResponse response){
		List<CodesDTO> tree =  codesConfigBO.getTree(projectId);
		JsonPageUtils.renderJsonPage(Long.valueOf(tree.size()), tree, response);
	}
	
	@RequestMapping("/codesConfig")
	public void codesConfig(@RequestParam("projectId") Long projectId, @RequestParam(required=false) String type, HttpServletResponse response){
		CodesConfig cc = codesConfigBO.getByProjectAndType(projectId, type);
	    if(cc==null){
	    	cc = new CodesConfig();
	    	cc.setProjectId(projectId);
	    }
	    AjaxUtils.renderJson(response, cc);
	}
	
	@RequestMapping(value = {"/admin/saveCodesConfig","/project/saveCodesConfig"})
	public void saveCodesConfig(@ModelAttribute CodesConfig cc, HttpServletResponse response){
		try{
			CodesConfig exist = codesConfigBO.getByProjectAndType(cc.getProjectId(), cc.getType());
			if(exist==null){
				codesConfigBO.insert(cc);
			}else{
				codesConfigBO.update(cc);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		}catch(Exception e){
			logger.error("saveCodesConfig", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
		
	}
	
	@RequestMapping("/project")
	public String project(HttpServletRequest request,HttpServletResponse response,Model model){
		Long projectId = ToNumberUtils.getLongValue(request.getParameter("projectId"));
		String appKey = request.getParameter("appKey");
		Integer type = ToNumberUtils.getIntegerValue(request.getParameter("filter_I_type"));
		model.addAttribute("projectId", projectId);
		model.addAttribute("appKey", appKey);
		model.addAttribute("type", type);
		return "/project/codes/list";
	}
	
	/**
	 * 异步获取单个对象
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getCodes/{id}")
	public void getCodes(@PathVariable("id") Long id,HttpServletResponse response){
		Codes codes = codesBO.getById(id);
		AjaxUtils.renderJson(response, codes);
	}
	
	@RequestMapping("/getConfigCodes")
	public void getProjectConfigCodes(@RequestParam String type, @RequestParam String relObjectId,
			HttpServletRequest request,HttpServletResponse response){
		List<Option> codes = codesBO.getCodes(relObjectId.toString(), type);
	    AjaxUtils.renderJson(response, codes);
	}
	
	/**
	 * 异步保存
	 */
	@RequestMapping("/admin/save")
	@AdminPermissionCheck("codes:save")
	public void save(@ModelAttribute Codes codes,HttpServletRequest request,HttpServletResponse response){
		try {
			if(codes.getId()==null){
				codesBO.insert(codes);
			}else{
				codesBO.update(codes);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
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
	@AdminPermissionCheck("codes:save")
	public void saveByProject(@ModelAttribute Codes codes,HttpServletRequest request,HttpServletResponse response){
		save(codes, request, response);
	}
	
	
	/**
	 * 异步删除
	 */
	@RequestMapping("/delete/{id}")
	@AdminPermissionCheck("codes:delete")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		AjaxUtils.renderText(response,String.valueOf(codesBO.delete(id)));
	}
	
	@RequestMapping("/project/delete/{id}")
	@AdminPermissionCheck("codes:delete")
	public void deleteByProject(@PathVariable("id") Long id,HttpServletResponse response){
		delete(id, response);
	}
}
