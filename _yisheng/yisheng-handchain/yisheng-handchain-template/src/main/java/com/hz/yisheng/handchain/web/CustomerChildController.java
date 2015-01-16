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

import com.google.common.collect.Maps;
import com.hz.yisheng.handchain.bo.CustomerBO;
import com.hz.yisheng.handchain.bo.CustomerChildBO;
import com.hz.yisheng.handchain.dto.CustomerDto;
import com.hz.yisheng.handchain.orm.Customer;
import com.hz.yisheng.handchain.orm.CustomerChild;

@Controller
@RequestMapping("/customerchild")
public class CustomerChildController extends BaseController{
	@Autowired
	private CustomerChildBO customerChildBO;
	@Autowired
	private CustomerBO customerBO;
	
	/**
	 * 查询中间表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		List<CustomerChild> customerChild = customerChildBO.list(queryParam);
		JsonPageUtils.renderJsonPage(customerChild!=null&&customerChild.size()>0?customerChild.size():0L, customerChild, response);
	}
	
	/**
	 * 查询家长信息，根据家长id查找到孩子id，将孩子parentid=家长id，存入CustomerDto里的list<Customer>
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list2")
	public void list2(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		Page<CustomerDto> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = customerChildBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
		
		/*Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		List<CustomerDto> list = customerChildBO.getParentAndChild(sqlMap);
		JsonPageUtils.renderJsonPage(list!=null&&list.size()>0?list.size():0L, list, response);*/
	}
	
	/**
	 * 保存
	 * @param customer
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute Customer customer,HttpServletRequest request, HttpServletResponse response) {
		try {
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
			
		} catch (Exception e) {
			logger.error("save customer error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 根据id查询中间表信息
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getCustomerAndChild/{id}")
	public String getCustomerAndChild(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response,Model model){
		CustomerDto customerDto = customerChildBO.getCustomerAndChildById(id);
		model.addAttribute("customerDto", customerDto);
		return "/customer/customerInfo";
	}
}
