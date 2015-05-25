/*package org.itboys.admin.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.itboys.admin.service.CityService;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

*//**
 * 城市相关控制器
 * @author huml
 *
 *//*
@Controller
@RequestMapping(value = "/common/city")
public class CityController {

	@Autowired
	private CityService cityService;
	
	*//**
	 * 异步获取所有的省份
	 * @param response
	 *//*
	@RequestMapping("/getProvinces")
	public void getProvinces(HttpServletResponse response){
		AjaxUtils.renderJson(response, cityService.getProvinces());
	}
	
	*//**
	 * 异步根据省份获取地区
	 * @param response
	 *//*
	@RequestMapping("/getDistricts")
	public void getDistricts(@RequestParam Long provinceId, HttpServletResponse response){
		AjaxUtils.renderJson(response, cityService.getDistricts(provinceId));
	}
	
	*//**
	 * 异步根据省份获取地区
	 * @param response
	 *//*
	@RequestMapping("/getCitys")
	public void getCitys(@RequestParam Long districtId, HttpServletResponse response){
		AjaxUtils.renderJson(response, cityService.getCitys(districtId));
	}
}
*/