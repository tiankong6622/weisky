package com.hz.yisheng.handchain.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.constants.CommonConstants;
import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.dto.page.Page;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JSONUtils;
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

import com.hz.yisheng.handchain.bo.AdminHandBookBO;
import com.hz.yisheng.handchain.orm.HandBook;
import com.hz.yisheng.handchain.orm.Hospital;

/**
 * 
 * @author WeiSky
 *
 */
@Controller
@RequestMapping("/admin/handbook")
public class HandbookController extends BaseController {

	@Autowired
	private AdminHandBookBO adminHandbookBO;

	@RequestMapping("/test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		AjaxUtils.renderText(response, "nihao");
	}

	/**/
	@RequestMapping("/list")
	public void list(HandBook hb, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);
		Page<HandBook> page = WebControllerUtils.preparePage(request, 10);
		page = adminHandbookBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(),
				response);
	}

	@RequestMapping("/update")
	public String save(@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (id != null) {
			HandBook hb = adminHandbookBO.select(id);
			model.addAttribute("hb", hb);
		} else {
			HandBook hb = new HandBook();
			model.addAttribute("hb", hb);
		}
		return "/handbook/handbook";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute HandBook hb, HttpServletRequest request,
			HttpServletResponse response,Model model) {
		try {
			if (hb.getId() == null) {
				adminHandbookBO.insert(hb);
			} else {
				adminHandbookBO.update(hb);
			}
			model.addAttribute("messager", "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "handbook/handbook";
	}

	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		try {
			adminHandbookBO.delete(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("Handbook delete error", e);
			e.printStackTrace();
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	@RequestMapping("/look")
	public String look(@RequestParam(value="id",required=false) Long id,HttpServletRequest request,HttpServletResponse response,Model model){
		if(id != null){
			HandBook hb = adminHandbookBO.queryById(id);
			model.addAttribute("hb", hb);
		}else{
			
		}
		return "handbook/lookhandbook";
	}
	
	@RequestMapping("/choiceHospital")
	public void choice(HttpServletRequest request,HttpServletResponse response){
		try {
			List<Hospital> list = adminHandbookBO.getAll();
			for(Hospital hb:list){
				hb.setValue(hb.getId());
				hb.setText(hb.getHospitalName());
			}
			JSONUtils.objToJSON(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
