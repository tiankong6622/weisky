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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.yisheng.handchain.bo.CustomerDeviceBO;
import com.hz.yisheng.handchain.orm.CustomerDevice;
import com.hz.yisheng.webdata.SessionHolder;
/**
 * 客户设备控制类
 * @author zfy
 *
 */
@Controller
@RequestMapping("/device")
public class CustomerDeviceController extends BaseController {
	@Autowired
	private CustomerDeviceBO customerDeviceBO;
	/**
	 * 分页查询客户设备信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		System.out.println("/device/list");
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		Page<CustomerDevice> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = customerDeviceBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 异步保存
	 * @param customer
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute CustomerDevice customerDevice,HttpServletRequest request, HttpServletResponse response) {
		try {
			if(customerDevice.getId()== null){
				customerDevice.setCreator(SessionHolder.getAdminUserId());
				System.out.println("save：insert...");
				customerDeviceBO.insert(customerDevice);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("save customerDevice error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	/**
	 * 根据设备id查询客户设备信息
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getCustomerDevice/{id}")
	public void getCustomerDevice(@PathVariable("id") Long deviceid,HttpServletResponse response){
		CustomerDevice customerDevice = customerDeviceBO.getCustomerDeviceById(deviceid);
		AjaxUtils.renderJson(response, customerDevice);
	}
	
	/**
	 * 根据客户id查询客户设备信息
	 * @param deviceid
	 * @param response
	 */
	@RequestMapping("/getCustomerDevices/{id}")
	public void getCustomerDevices(@PathVariable("id") Long customerid,HttpServletResponse response){
		List<CustomerDevice> customerDevice = customerDeviceBO.getCustomerDeviceByCustomerId(customerid);
		AjaxUtils.renderJson(response, customerDevice);
	}

}
