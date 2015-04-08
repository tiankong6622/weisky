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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.sunday.commondata.bo.AttachementBO;
import com.hz.sunday.commondata.orm.Attachement;
import com.hz.sunday.cyds.bo.CustomerBO;
import com.hz.sunday.cyds.orm.Customer;

/**
 * 客户基本信息-对应后台
 * @author WeiSky
 *
 */
@Controller
@RequestMapping("/admin/customer")
public class CustomerController extends BaseController{

	@Autowired
	private CustomerBO customerBO;
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
		List<Customer> list = customerBO.getList(param);
		Long count = customerBO.getCount(param);
		JsonPageUtils.renderJsonPage(count, list, response);
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param customer
	 */
	@RequestMapping("/save")
	public String save(@RequestParam("img") CommonsMultipartFile achmentFile, Model model,
			HttpServletRequest request, HttpServletResponse response, Customer customer){
		try{
			List<Attachement> files = attachementBO.prepareAttachement(achmentFile);
			if(files != null && files.size() > 0){
				customer.setImgPath(files.get(0).getPath());//设置头像地址
			}
			
			if(customer.getId() != null){
				customerBO.update(customer);
			}else{
				customerBO.insert(customer);
			}
			model.addAttribute("message", "success");//保存成功
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "failed");//保存失败
		}
		return "/cyds/customer/admin-single-detail";
	}
	
	/**
	 * 获取单条信息
	 * @return
	 */
	@RequestMapping("/getSingleDetail")
	public String getSingleDetail(@RequestParam("id") Long id, Model model,
			HttpServletRequest request, HttpServletResponse response){
		Customer c = customerBO.getSingleDetail(id);
		model.addAttribute("customer", c);
		return "/cyds/customer/admin-single-detail";
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
			customerBO.delete(id);
			AjaxUtils.renderText(response, "1");//删除成功
		}catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText(response, "0");//删除失败
		}
	}
}
