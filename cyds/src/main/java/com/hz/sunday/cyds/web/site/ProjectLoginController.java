package com.hz.sunday.cyds.web.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.javafans.resources.ResourceConfig;
import org.javafans.web.AjaxUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.sunday.cyds.bo.CustomerBO;
import com.hz.sunday.cyds.orm.Customer;

/**
 * 参赛选手登陆
 * @author WeiSky
 *
 */
@Controller
@RequestMapping("/site/plogin")
public class ProjectLoginController extends BaseController{
	
	@Autowired
	private CustomerBO customerBO;

	/**
	 * 前往登陆页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toLoginPage")
	public String toLoginPage(@RequestParam(value="toUrl", required=false) String toUrl,
			Model model, HttpServletRequest request, HttpServletResponse response){
		
		Customer cus = (Customer)request.getSession().getAttribute("cusInfo");
		if(cus != null){//已登录   直接跳到参赛报名页
			return "/cyds/site/signup";
		}
		
		String defaultUrl = "/site/plogin/toLoginPage";//默认登陆成功后，跳转到参赛报名页
		if(!StringUtils.isBlank(toUrl)){
			defaultUrl = toUrl;//设置登陆成功后，将要跳转的页面
		}
		model.addAttribute("toUrl", defaultUrl);
		return "/cyds/site/login";
	}
	
	/**
	 * 登陆
	 * @param customer
	 * @param toUrl
	 * @param yzm
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping("/doLogin")
	public void doLogin(Customer customer, 
			@RequestParam("yzm") String yzm,
			HttpServletRequest request, HttpServletResponse response){
		String _yzm = (String) request.getSession().getAttribute("rand");
		if(!StringUtils.equals(_yzm, yzm)){
			AjaxUtils.renderText(response, "-2");//验证码不正确
			return ;
		}
		
		if(StringUtils.isBlank(customer.getMobile())){
			AjaxUtils.renderText(response, "-3");//登陆用的手机号不能用为空
			return ;
		}
		
		if(StringUtils.isBlank(customer.getPassw())){
			AjaxUtils.renderText(response, "-4");//密码不能为空
			return ;
		}
		
		Customer cus = customerBO.doLogin(customer);
		if(cus == null){
			AjaxUtils.renderText(response, "-1");//用户名或密码不正确
		}
		
		request.getSession().setAttribute("cusInfo", cus);//将参赛选手基本信息放入内存中
		AjaxUtils.renderText(response, "1");//登陆成功
	}
	
	/**
	 * 前往注册页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toRegisPage")
	public String toRegisPage(Model model, HttpServletRequest request, HttpServletResponse response){
		return "/cyds/site/regis";
	}
	
	/**
	 * 注册
	 * @param achmentFiles
	 * @param request
	 * @param response
	 */
	@RequestMapping("/doRegis")
	public void doRegis(Customer cus, HttpServletRequest request, HttpServletResponse response){
		try{
			cus.setCtype("1");
			if(StringUtils.isBlank(cus.getMobile())){
				AjaxUtils.renderText(response, "-2");//手机号不能用为空
				return ;
			}
			if(StringUtils.isBlank(cus.getPassw())){
				AjaxUtils.renderText(response, "-3");//密码不能用为空
				return ;
			}
			if(StringUtils.isBlank(cus.getEmail())){
				AjaxUtils.renderText(response, "-4");//邮箱不能用为空
				return ;
			}
			
			boolean extis= customerBO.extisMobi(cus.getMobile());
			if(extis){
				AjaxUtils.renderText(response, "-5");//该手机号已存在
				return ;
			}
			customerBO.insert(cus);//执行注册操作
			request.getSession().setAttribute("cusInfo", cus);//将参赛选手基本信息放入内存中
			AjaxUtils.renderText(response, "1");//注册成功
		}catch(Exception e){
			e.printStackTrace();
			AjaxUtils.renderText(response, "-1");//注册失败，未知原因
		}
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute("cusInfo", null);
		return "redirect:"+ResourceConfig.getSysConfig("webPath")+"/site/"; 
	}
}
