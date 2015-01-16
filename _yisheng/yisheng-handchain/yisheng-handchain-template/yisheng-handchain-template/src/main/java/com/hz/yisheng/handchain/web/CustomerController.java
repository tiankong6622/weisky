package com.hz.yisheng.handchain.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.constants.CommonConstants;
import org.javafans.common.utils.encryption.Digests;
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

import com.google.common.collect.Maps;
import com.hz.yisheng.handchain.bo.CustomerBO;
import com.hz.yisheng.handchain.orm.Customer;
import com.hz.yisheng.webdata.SessionHolder;

/**
 * 母婴信息控制类
 * 
 * @author zfy
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerBO customerBO;

	/**
	 * 分页查询产妇信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		Page<Customer> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = customerBO.pageQuery(page, sqlMap);
		/*List<Customer> list = customerBO.list(sqlMap);
		Long count = customerBO.count(sqlMap);
		JsonPageUtils.renderJsonPage(count, list, response);*/
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 分页查询婴儿信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/childlist")
	public void childList(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		Page<Customer> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = customerBO.pageQuery2(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	/**
	 * 婴儿信息页面，获取婴儿名称
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list2/{id}")
	public void list2(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		List<Customer> customers = customerBO.childList(queryParam);
		JsonPageUtils.renderJsonPage(customers!=null&&customers.size()>0?customers.size():0L, customers, response);
	}
	
	/**
	 * 婴儿信息页面，获取母亲名称，已经存在婴儿信息的母亲排除
	 * @param request
	 * @param response
	 */
	@RequestMapping("/molist")
	public void motherList(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		List<Customer> customers = customerBO.getMotherName(queryParam);
		JsonPageUtils.renderJsonPage(customers!=null&&customers.size()>0?customers.size():0L, customers, response);
	}
	/**
	 * 客户设备信息页面，获取客户名称
	 * @param request
	 * @param response
	 */
	@RequestMapping("/alllist")
	public void allList(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		List<Customer> customers = customerBO.allList(queryParam);
		JsonPageUtils.renderJsonPage(customers!=null&&customers.size()>0?customers.size():0L, customers, response);
	}

	/**
	 * 异步保存
	 * @param customer
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute Customer customer,HttpServletRequest request, HttpServletResponse response) {
		try {
			if(customer.getId()== null){
				if(customer.getParentId() == 0){
					customer.setSex(2);
				}else{}
				customer.setCreator(SessionHolder.getAdminUserId());
				//默认类型为普通，等级为1级
				customer.setType("普通");
				customer.setLeve("1");
				customer.setPassword(Digests.md5(customer.getPassword()));
				System.out.println("save：insert...");
				customerBO.insert(customer);
			}else{
				customer.setUpdater(SessionHolder.getAdminUserId());
				customer.setUpdateTime(new Date());
				customer.setPassword(Digests.md5(customer.getPassword()));
				System.out.println("save：update...");
				customerBO.update(customer);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("save customer error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	@RequestMapping("/updatePwd")
	public void updatePwd(@ModelAttribute Customer customer,HttpServletRequest request, HttpServletResponse response) {
		try {
			customer.setUpdater(SessionHolder.getAdminUserId());
			customer.setUpdateTime(new Date());
			customer.setPassword(Digests.md5(customer.getPassword()));
			System.out.println("updatePwd：update...");
			customerBO.update(customer);
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("save customer error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	@RequestMapping("/getCustomer/{id}")
	public void getCustomer(@PathVariable("id") Long id,HttpServletResponse response){
		Customer customer = customerBO.getCustomerById(id);
		AjaxUtils.renderJson(response, customer);
	}
	
	@RequestMapping("/modifyPassword/{id}")
	public void modifyPassword(@PathVariable("id") Long id,HttpServletResponse response){
		Customer customer = customerBO.getCustomerById(id);
		AjaxUtils.renderJson(response, customer);
	}

}
