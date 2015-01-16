package com.hz.yisheng.handchain.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.constants.CommonConstants;
import org.javafans.common.utils.encryption.Digests;
import org.javafans.common.utils.io.FileUtils;
import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.dto.page.Page;
import org.javafans.resources.ResourceConfig;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JSONUtils;
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

import com.google.common.collect.Maps;
import com.hz.yisheng.commondata.bo.AttachementBO;
import com.hz.yisheng.commondata.orm.Attachement;
import com.hz.yisheng.handchain.bo.CustomerBO;
import com.hz.yisheng.handchain.bo.CustomerChildBO;
import com.hz.yisheng.handchain.bo.HospitalBO;
import com.hz.yisheng.handchain.orm.Customer;
import com.hz.yisheng.handchain.orm.CustomerChild;
import com.hz.yisheng.handchain.orm.Hospital;
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
	@Autowired
	private CustomerChildBO customerChildBO;
	@Autowired
	private HospitalBO hospitalBO;
	@Autowired
	private AttachementBO attachementBO;

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
	@RequestMapping("/list2")
	public void list2(HttpServletRequest request,HttpServletResponse response) {
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
	public void save(@ModelAttribute Customer customer,@RequestParam("img") CommonsMultipartFile img,HttpServletRequest request, HttpServletResponse response) {
		CustomerChild customerChild = new CustomerChild();
		try {
			//家长新增信息
			if(request.getParameter("flag").equals("0")){
				if(customer.getId()== null){
					//家长的设备编号
					customerChild.setCustomerDeviceNumber(customer.getCustomDeviceNum());
					customer.setCreator(SessionHolder.getAdminUserId());
					//默认类型为普通，等级为1级
					customer.setType("普通");
					customer.setLeve("1");
					if(customer.getPassword() == null){
						customer.setPassword(Digests.md5("000000"));
					}else{
						customer.setPassword(Digests.md5(customer.getPassword()));
					}
					customer.setParentId(0l);
					System.out.println("save：insert...");
					customerBO.insert(customer);
					//将设备编号添加到设备表
					customerBO.findDeviceNum(customer);
					if(img.getFileItem().getName()!=""){
						//添加头像
						saveAttachement(img,customer);
					}
					customerChild.setCustomerId(customer.getId());
					//将家长的id与设备编号插入中间表
					customerChild.setCreator(SessionHolder.getAdminUserId());
					customerChild.setIfAdd(1);
					System.out.println("save：insert customer_child_device...");
					customerChildBO.insert(customerChild);
					
				}else{
					//家长修改信息
					customer.setUpdater(SessionHolder.getAdminUserId());
					customer.setUpdateTime(new Date());
					customer.setPassword(Digests.md5(customer.getPassword()));
					System.out.println("save：update...");
					customerBO.update(customer);
					//修改前有图片
					if(attachementBO.findAttachementBy(customer.getId(), "customer")!= null){
						if(img.getFileItem().getName()!=""){
							attachementBO.delete(attachementBO.findAttachementBy(customer.getId(), "customer").getId());
							saveAttachement(img,customer);
						}
					}else{
						//修改之前没有图片，则添加
						if(img.getFileItem().getName()!=""){
							saveAttachement(img,customer);
						}
					}
				}
				AjaxUtils.renderText(response,CommonConstants.SUCCESS);
			}else{
				saveChild(customer,request,response);
			}
			
		} catch (Exception e) {
			logger.error("save customer error",e);
			e.printStackTrace();
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 添加头像图片
	 */
	public void saveAttachement(@RequestParam("img") CommonsMultipartFile img,@ModelAttribute Customer customer){
			Attachement attachement = new Attachement();
			attachement.setFileName(img.getFileItem().getName());
			attachement.setObjId(customer.getId().toString());
			attachement.setType("customer");
			attachement.setSize(img.getSize());
			attachement.setCreaterId(SessionHolder.getAdminUserId());
			attachement.setContentType(img.getContentType());
			try {
				attachement.setPath(FileUtils.saveFile(img.getFileItem().getInputStream(), ResourceConfig.getSysConfig("fileUploadPath"), img.getFileItem().getName(), img.getFileItem().getContentType()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			attachementBO.insert(attachement);
	}
	
	/**
	 * 孩子保存
	 * @param customer
	 * @param request
	 * @param response
	 */
	@RequestMapping("/savechild")
	public void saveChild(@ModelAttribute Customer customer,HttpServletRequest request, HttpServletResponse response) {
		CustomerChild customerChild = new CustomerChild();
		try {
			//孩子新增信息
			if(customer.getId()== null){
				
				customer.setCreator(SessionHolder.getAdminUserId());
				//默认类型为普通，等级为1级
				customer.setType("普通");
				customer.setLeve("1");
				customer.setPassword(Digests.md5("000000"));
				customer.setParentId(1l);
				System.out.println("save：insert...");
				customerBO.insert(customer);
				//将设备编号添加到设备表
				customerBO.findDeviceNum(customer);
				//孩子的id
				customerChild.setChildrenId(customer.getId());
				//孩子的设备编号
				customerChild.setChildrenDeviceNumber(customer.getCustomDeviceNum());
				//获取家长的id
				customerChild.setCustomerId(Long.parseLong(request.getParameter("pareId")));
				//获取家长的设备编号
				customerChild.setCustomerDeviceNumber(request.getParameter("customerDevNum"));
				//将家长与孩子的id与设备编号插入中间表
				customerChild.setCreator(SessionHolder.getAdminUserId());
				List<CustomerChild> cc = customerChildBO.queryByCustomerId(Long.parseLong(request.getParameter("pareId")));
				if(!cc.isEmpty()){
					customerChild.setId(cc.get(0).getId());
					System.out.println("save：update customer_child_device...");
					customerChildBO.update(customerChild);
				}else{
					System.out.println("save：insert customer_child_device...");
					customerChildBO.insert(customerChild);
				}
				
				
			}else{
				customer.setUpdater(SessionHolder.getAdminUserId());
				customer.setUpdateTime(new Date());
			//	customer.setPassword(Digests.md5(customer.getPassword()));
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
	
	/**
	 * 重置密码，更新数据库中的密码
	 * @param customer
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd(@ModelAttribute Customer customer,HttpServletRequest request, HttpServletResponse response) {
		try {
			customer.setUpdater(SessionHolder.getAdminUserId());
			customer.setUpdateTime(new Date());
			customer.setPassword(Digests.md5(customer.getPassword()));
			System.out.println("updatePwd：update...");
			customerBO.update(customer);
			return "success";
		} catch (Exception e) {
			logger.error("update password error",e);
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 根据id查询客户信息
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getCustomer/{id}")
	public void getCustomer(@PathVariable("id") Long id,HttpServletResponse response){
		Customer customer = customerBO.getCustomerById(id);
		AjaxUtils.renderJson(response, customer);
	}

	/**
	 * 根据id查询中间表信息
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getCustomerChild/{id}")
	public void getCustomerChild(@PathVariable("id") Long id,HttpServletResponse response){
		Customer customer = customerBO.getCustomerById(id);
		AjaxUtils.renderJson(response, customer);
	}
	
	/**
	 * 根据id查询该客户的密码
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getPassword/{id}")
	public String getPassword(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response,Model model){
		Customer customer = customerBO.getCustomerById(id);
		String n = getRandomPwd();
		customer.setPassword(n);
		model.addAttribute("customer", customer);
		if(updatePwd(customer,request,response).equals("success")){
			customer.setPassword(n);
			return "/customer/modifyPwd";
		}else{
			return "";
		}
	}
	
	/**
	 * 随机产生6位数字的密码
	 * @return
	 */
	public  String getRandomPwd(){
	  Random rd = new Random();
	  String n="";
	  int getNum;
	  do {
	   getNum = Math.abs(rd.nextInt())%10 + 48;//产生数字0-9的随机数
	   System.out.println(getNum);
	   char num1 = (char)getNum;
	   if(num1 == '0'){
		   num1 = '1';
	   }
	   System.out.println(num1);
	   String dn = Character.toString(num1);
	   n += dn;
	  } while (n.length()<6);
	  System.out.println("随机的6位密码是：" + n);
	  return n;
	 }
	
	/**
	 * 得到客户名称及设备编号
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getNameAndDevice")
	public void getNameAndDevice(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		List<Customer> customer = customerBO.queryCustomerNameAndDevice(queryParam);
		AjaxUtils.renderJson(response, customer);
	}
	
	/**
	 * 得到孩子名称及设备编号
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getChildNameAndDevice")
	public void getChildNameAndDevice(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		List<Customer> customer = customerBO.queryChildNameAndDevice(queryParam);
		AjaxUtils.renderJson(response, customer);
	}
	
	/**
	 * 得到医院名称
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getHospitalName")
	public void getHospitalName(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		List<Hospital> hospitalName = hospitalBO.queryHospitalName(queryParam);
		AjaxUtils.renderJson(response, hospitalName);
	}
	
	/**
	 * 检查用户名是否相同
	 * @return
	 */
	@RequestMapping("/getCheckName/{name}")
	public void checkName(@PathVariable("name") String name,HttpServletRequest request,HttpServletResponse response){
		try{
			Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
			queryParam.put("name", name);
			queryParam.put("id", request.getParameter("id"));
			List<Customer>  customer = customerBO.checkList(queryParam);
			if(!customer.isEmpty()){
				JSONUtils.objToJSON(response, 1);
			}else{
				JSONUtils.objToJSON(response, 0);
			}
		}catch(Exception e){
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}
	
	/**
	 * 检查手机号是否相同
	 * @param name
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getCheckMobile/{phone}")
	public void checkMobile(@PathVariable("phone") String phone,HttpServletRequest request,HttpServletResponse response){
		try{
			Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
			queryParam.put("phone", phone);
			queryParam.put("id", request.getParameter("id"));
			List<Customer>  customer = customerBO.checkList(queryParam);
			if(!customer.isEmpty()){
				JSONUtils.objToJSON(response, 1);
			}else{
				JSONUtils.objToJSON(response, 0);
			}
		}catch(Exception e){
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
		
		
	}
	
	


}
