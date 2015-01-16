package com.hz.yisheng.handchain.web;

import java.util.Date;
import java.util.List;
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

import com.hz.yisheng.handchain.bo.MedicationBO;
import com.hz.yisheng.handchain.orm.Medication;
import com.hz.yisheng.webdata.SessionHolder;

@Controller
@RequestMapping("/medication")
public class MedicationController extends BaseController {
	@Autowired
	private MedicationBO medicationBO;
	
	/**
	 * 分页查询用药信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response) {
		System.out.println(request.getParameter("filter_S_medicationName"));
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		Page<Medication> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = medicationBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}	
	
	/**
	 * 异步保存
	 * @param medication
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute Medication medication,HttpServletRequest request, HttpServletResponse response) {
		try {
			//新增
			if(medication.getId()== null){
				medication.setCreator(SessionHolder.getAdminUserId());
				System.out.println("save：insert...");
				medicationBO.insert(medication);
			}else{
				//修改
				medication.setUpdater(SessionHolder.getAdminUserId());
				medication.setUpdateTime(new Date());
				System.out.println("save：update...");
				medicationBO.update(medication);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("save medication error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 根据id查询用药记录信息
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getMedicationInfo/{id}")
	public void getMedicationInfo(@PathVariable("id") Long id,HttpServletResponse response){
		Medication medication = medicationBO.getMedicationById(id);
		AjaxUtils.renderJson(response, medication);
	}
	
	/**
	 * 页面根据id查询该客户的历史用药记录
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getMedication/{id}")
	public String getMedication(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response,Model model){
		List<Medication> medication = medicationBO.getMedicationByCustomerId(id);
		model.addAttribute("medication", medication);
		return "/customer/historyMedication";
	}

}
