package com.hz.yisheng.handchain.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.yisheng.handchain.bo.ChildHealthBO;
import com.hz.yisheng.handchain.orm.ChildHealth;
import com.hz.yisheng.webdata.SessionHolder;

@Controller
@RequestMapping("/health")
public class ChildHealthController extends BaseController{
	@Autowired
	private ChildHealthBO childHealthBO;
	/*@Autowired
	private AttachementBO attachementBO;*/
	
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
				/*System.out.println(childHealth.getId());
				if(childHealth.getId()!=null){
					Attachement attachement = new Attachement();
					attachement.setFileName(img.getFileItem().getFieldName());
					attachement.setObjId(childHealth.getId().toString());
					attachement.setType("image");
					attachement.setSize(img.getSize());
					attachement.setCreaterId(SessionHolder.getAdminUserId());
					attachement.setContentType(img.getContentType());
					attachement.setPath(FileUtils.saveFile(img.getFileItem().getInputStream(), ResourceConfig.getSysConfig("fileUploadPath"), img.getFileItem().getName(), img.getFileItem().getContentType()));
					attachementBO.insert(attachement);
				}*/
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
			
		} catch (Exception e) {
			logger.error("save childHealth error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 页面根据id查询身体健康信息
	 * @param healthid
	 * @param response
	 */
	@RequestMapping("/getChildHealth/{id}")
	public void getChildHealth(@PathVariable("id") Long healthid,HttpServletResponse response){
		ChildHealth childHealth = childHealthBO.getHealthById(healthid);
		AjaxUtils.renderJson(response, childHealth);
	}
	
	/**
	 * 页面根据id查询身体健康信息，用于查询该客户的健康历史记录
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getHealth/{id}")
	public String getHealth(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response,Model model){
		List<ChildHealth> childHealth = childHealthBO.getHealthByCustomerId(id);
		model.addAttribute("childHealth", childHealth);
		return "/customer/healthHistory";
	}
	
	/**
	 * 页面根据id查询身体健康信息，用于查询该客户的第一次与最近一次的健康信息
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getHealthInfo/{id}")
	public String getCustomerAndChild(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response,Model model){
		ChildHealth childHealth = childHealthBO.getHealthById(id);
		model.addAttribute("childHealth", childHealth);
		return "/customer/healthInfo";
	}
	
}
