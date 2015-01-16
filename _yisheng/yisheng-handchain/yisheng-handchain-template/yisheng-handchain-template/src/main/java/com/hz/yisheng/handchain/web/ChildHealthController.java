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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.yisheng.handchain.bo.ChildHealthBO;
import com.hz.yisheng.handchain.orm.ChildHealth;
import com.hz.yisheng.webdata.SessionHolder;

@Controller
@RequestMapping("/child/health")
public class ChildHealthController extends BaseController{
	@Autowired
	private ChildHealthBO childHealthBO;
	
	/**
	 * 分页查询婴儿健康信息
	 * @param childHealth
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(ChildHealth childHealth, HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		Page<ChildHealth> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = childHealthBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 保存
	 * @param customer
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute ChildHealth childHealth,HttpServletRequest request, HttpServletResponse response) {
		try {
			if(childHealth.getId()==null){
				childHealth.setCreator(SessionHolder.getAdminUserId());
				System.out.println("save：insert...");
				childHealthBO.insert(childHealth);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("save childHealth error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	@RequestMapping("/getChildHealth/{id}")
	public void getChildHealth(@PathVariable("id") Long healthid,HttpServletResponse response){
		ChildHealth childHealth = childHealthBO.getChildHealthById(healthid);
		AjaxUtils.renderJson(response, childHealth);
	}
	
	
	

}
