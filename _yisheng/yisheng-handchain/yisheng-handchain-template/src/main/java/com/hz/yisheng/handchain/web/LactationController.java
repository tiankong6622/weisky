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

import com.hz.yisheng.handchain.bo.LactationBO;
import com.hz.yisheng.handchain.orm.Lactation;
import com.hz.yisheng.webdata.SessionHolder;

@Controller
@RequestMapping("/lactation")
public class LactationController extends BaseController{
	@Autowired
	private LactationBO lactationBO;
	
	/**
	 * 分页查询哺乳信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		Page<Lactation> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = lactationBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 异步保存
	 * @param lactation
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute Lactation lactation,HttpServletRequest request, HttpServletResponse response) {
		try {
			//新增
			if(lactation.getId()== null){
				lactation.setCreator(SessionHolder.getAdminUserId());
				System.out.println("save：insert...");
				lactationBO.insert(lactation);
			}else{
				//修改
				lactation.setUpdater(SessionHolder.getAdminUserId());
				lactation.setUpdateTime(new Date());
				System.out.println("save：update...");
				lactationBO.update(lactation);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("save lactation error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 根据id查询哺乳记录信息
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getLactationInfo/{id}")
	public void getLactationInfo(@PathVariable("id") Long id,HttpServletResponse response){
		Lactation lactation = lactationBO.getLactationById(id);
		AjaxUtils.renderJson(response, lactation);
	}
	
	/**
	 * 页面根据id查询该客户的历史哺乳记录
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getLactation/{id}")
	public String getLactation(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response,Model model){
		List<Lactation> lactation = lactationBO.getLactationByCustomerId(id);
		model.addAttribute("lactation", lactation);
		return "/customer/historyLactation";
	}
}
