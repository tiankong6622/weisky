package com.hz.yisheng.commondata.web;

import javax.servlet.http.HttpServletResponse;

import org.javafans.web.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.yisheng.commondata.bo.CityBO;


/**
 * 城市相关控制器
 * @author ChenJunhui
 *
 */
@Controller
@RequestMapping(value = "/common/city")
public class CityController {

	@Autowired
	private CityBO cityBO;
	
	/**
	 * 异步获取所有的省份
	 * @param response
	 */
	@RequestMapping("/getProvinces")
	public void getProvinces(HttpServletResponse response){
		AjaxUtils.renderJson(response, cityBO.getProvinces());
	}
	
	/**
	 * 异步根据省份获取地区
	 * @param response
	 */
	@RequestMapping("/getDistricts")
	public void getDistricts(@RequestParam Long provinceId, HttpServletResponse response){
		AjaxUtils.renderJson(response, cityBO.getDistricts(provinceId));
	}
	
	/**
	 * 异步根据省份获取地区
	 * @param response
	 */
	@RequestMapping("/getCitys")
	public void getCitys(@RequestParam Long districtId, HttpServletResponse response){
		AjaxUtils.renderJson(response, cityBO.getCitys(districtId));
	}
}
