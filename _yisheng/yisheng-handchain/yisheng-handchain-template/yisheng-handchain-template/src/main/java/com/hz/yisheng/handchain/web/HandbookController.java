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

import com.hz.yisheng.handchain.bo.AdminHandBookBO;
import com.hz.yisheng.handchain.orm.HandBook;
import com.hz.yisheng.webdata.SessionHolder;

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
	public void list(HandBook hb,HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> sqlMap = QueryParamUtils.builderQueryMap(request);
		Page<HandBook> page = WebControllerUtils.preparePage(request,10);
		page = adminHandbookBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	} 
	@RequestMapping("/save")
	public void save(@ModelAttribute HandBook hb,HttpServletRequest request, HttpServletResponse response) {
		try {
			if(hb.getId() == null){
				hb.setCreator(SessionHolder.getAdminUserId());
				adminHandbookBO.insert(hb);
			}else{
				adminHandbookBO.update(hb);
			}
		/*	HandBook hbsave=new HandBook();
		    hbsave.setTitle(request.getParameter("title"));
		    hbsave.setSubtitle(request.getParameter("subtitle"));
		    hbsave.setSummary(request.getParameter("summary"));*/
			
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		}catch (Exception e) {
			logger.error("Handbook save  error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
		
	}

	@RequestMapping("/update/{id}")
	public void update(@PathVariable Long id,HttpServletRequest request, HttpServletResponse response) {
		try {
			HandBook hb = adminHandbookBO.select(id);
			AjaxUtils.renderJson(response, hb);
		}catch (Exception e) {
			logger.error("Handbook update error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,null);
		}
	
}

@RequestMapping("/delete/{id}")
public void delete(@PathVariable("id") Long id,HttpServletResponse response){
	try{
		adminHandbookBO.delete(id);
		AjaxUtils.renderText(response,CommonConstants.SUCCESS);
	}catch(Exception e){
		logger.error("Handbook delete error",e);
		e.printStackTrace();
		AjaxUtils.renderText(response,CommonConstants.FAIL);
	}
}
}
