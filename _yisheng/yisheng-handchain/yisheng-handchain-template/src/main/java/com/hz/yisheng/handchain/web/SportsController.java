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

import com.hz.yisheng.handchain.bo.SportsBO;
import com.hz.yisheng.handchain.orm.Sports;
import com.hz.yisheng.webdata.SessionHolder;
@Controller
@RequestMapping("/sports")
public class SportsController extends BaseController{
	@Autowired
	private SportsBO sportsBO;
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		Page<Sports> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = sportsBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 异步保存
	 * @param trajectory
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute Sports sports,HttpServletRequest request, HttpServletResponse response) {
		try {
			//新增信息
			if(sports.getId() == null){
				sports.setCreator(SessionHolder.getAdminUserId());
				sportsBO.insert(sports);
			}else{
				sports.setUpdater(SessionHolder.getAdminUserId());
				sports.setUpdateTime(new Date());
				sportsBO.update(sports);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("save customer error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 根据customerId查询运动记录
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getSports/{id}")
	public String getSports(@PathVariable("id") Long id,HttpServletResponse response,Model model){
		List<Sports> sports = sportsBO.getSportsByCustomerId(id);
		model.addAttribute("sports", sports);
		return "/customer/historySports";
	}
}
