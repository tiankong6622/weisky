package com.hz.yisheng.handchain.web;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.yisheng.handchain.bo.HospitalBO;
import com.hz.yisheng.handchain.orm.Hospital;
/**
 * 
 * @author loard
 *
 */
@Controller
@RequestMapping("/admin/hospital")
public class HospitalController extends BaseController{

	@Autowired
	private HospitalBO hospitalBO;
	
	
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);
		Page<Hospital> page = WebControllerUtils.preparePage(request, 10);
		page = hospitalBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(),
				response);
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam(value="id",required=false) Long id,
			HttpServletRequest request,HttpServletResponse response,Model model){
		if(id != null){
			Hospital hp = hospitalBO.select(id);
			model.addAttribute("hp", hp);
		}else{
			Hospital hp = new Hospital();
			model.addAttribute("hp", hp);
		}
		return "/handbook/hospital";
	}
	
	@RequestMapping("/save")
	public String save(Hospital hp,HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			if(hp.getId() != null){
				hospitalBO.update(hp);
			}else{
				hospitalBO.insert(hp);
			}
			model.addAttribute("messager", "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/handbook/hospital";
	}
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			hospitalBO.delete(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("Hospital delete error", e);
			e.printStackTrace();
			AjaxUtils.renderText(response, CommonConstants.FAIL);
			e.printStackTrace();
		}
	}
	@RequestMapping("/look")
	public String look(@RequestParam(value="id",required=false) Long id,
						HttpServletRequest request,HttpServletResponse response,Model model){
		if(id != null){
			Hospital hp = hospitalBO.select(id);
			model.addAttribute("hp", hp);
		}else{
			
		}
		return "/handbook/lookhospital";
	}
}
