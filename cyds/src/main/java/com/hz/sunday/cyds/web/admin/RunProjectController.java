package com.hz.sunday.cyds.web.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.dto.page.PageQueryUtils;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.sunday.commondata.bo.AttachementBO;
import com.hz.sunday.commondata.orm.Attachement;
import com.hz.sunday.cyds.bo.RunProjectBO;
import com.hz.sunday.cyds.orm.RunProject;

/**
 * 
 * @author WeiSky
 *
 */
@Controller
@RequestMapping("/admin/runproject")
public class RunProjectController extends BaseController{

	@Autowired
	private RunProjectBO runProjectBO;
	@Autowired
	private AttachementBO attachementBO;
	
	/**
	 * 客户基本信息列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void getList(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> param = PageQueryUtils.preparePage(request);
		List<RunProject> list = runProjectBO.getList(param);
		Long count = runProjectBO.getCount(param);
		JsonPageUtils.renderJsonPage(count, list, response);
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param RunProject
	 */
	@RequestMapping("/save")
	public String save(Model model, HttpServletRequest request, HttpServletResponse response, RunProject runProject){
		try{
			
			if(runProject.getId() != null){
				runProjectBO.update(runProject);
			}else{
				runProjectBO.insert(runProject);
			}
			
			model.addAttribute("message", "success");//保存成功
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "failed");//保存失败
		}
		return "/cyds/runproject/admin-single-detail";
	}
	
	/**
	 * 获取单条信息
	 * @return
	 */
	@RequestMapping("/getSingleDetail")
	public String getSingleDetail(@RequestParam("id") Long id, Model model,
			HttpServletRequest request, HttpServletResponse response){
		RunProject rp = runProjectBO.getSingleDetail(id);
		if(rp != null){
			String mtype = "runproject";
			List<Attachement> list = attachementBO.findBy(String.valueOf(id), mtype);
			model.addAttribute("mnattach", list);
			model.addAttribute("runproject", rp);
		}
		return "/cyds/runproject/admin-single-detail";
	}
	
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response){
		try{
			runProjectBO.delete(id);
			AjaxUtils.renderText(response, "1");//删除成功
		}catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText(response, "0");//删除失败
		}
	}
}
