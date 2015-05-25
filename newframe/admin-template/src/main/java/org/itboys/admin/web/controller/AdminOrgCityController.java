package org.itboys.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.itboys.admin.service.AdminOrgCityService;
import org.itboys.admin.service.AdminOrgService;
import org.itboys.admin.service.AdminUserOrgPostService;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.dto.IdValueOption;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.commons.utils.collection.FetchCondition;
import org.itboys.framework.spring.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/org/city")
public class AdminOrgCityController extends BaseController {
	@Autowired
	private AdminOrgCityService adminOrgCityService;
	@Autowired
	private AdminUserOrgPostService adminUserOrgPostService;
	@Autowired
	private AdminOrgService adminOrgService;
	
	/*@RequestMapping("getOrgRealCitys")
	public void getOrgRealCitys(@RequestParam Long orgId,HttpServletResponse response){
		List<Long> cityIds = adminOrgCityService.getAdminOrgCitys(orgId);
		List<EasyUiTreeDO> tree = cityService.getCityTree(cityIds,adminOrgService.getById(orgId).getProvinceId(),adminOrgService.getById(orgId).getDistrictId());
		AjaxUtils.renderJson(response,tree);
	}*/
	
	/*@RequestMapping("getUserRealCitys")
	public void getUserRealCitys(@RequestParam Long userId,HttpServletResponse response){
		AdminUserOrgPost auop = adminUserOrgPostService.findByUserId(userId);
		List<EasyUiTreeDO> tree = adminOrgCityService.getUserCityTree(auop.getOrgId(), userId);
		AjaxUtils.renderJson(response,tree);
	}*/
	
	@RequestMapping("realOrgCitys")
	public void realOrgCitys(@RequestParam Long orgId,@RequestParam String cityIds, HttpServletResponse response){
		List<Long> cityIdList = CommonCollectionUtils.splictToLongList(cityIds, ",");
		try{
			adminOrgCityService.doRealOrg(orgId, cityIdList);
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		}catch(Exception e){
			logger.error("realOrgCitys error", e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/*@RequestMapping("realUserCitys")
	public void realUserCitys(@RequestParam Long userId,@RequestParam String cityIds, HttpServletResponse response){
		List<Long> cityIdList = CommonCollectionUtils.splictToLongList(cityIds, ",");
		try{
			adminOrgCityService.doRealUser(userId, cityIdList);
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		}catch(Exception e){
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	
	*//**
	 * 异步获取用户分配的所有的省份
	 * @param response
	 *//*
	@RequestMapping("/getProvinces")
	public void getProvinces(HttpServletResponse response){
		AjaxUtils.renderJson(response, getFilters(cityService.getProvinces()));
	}
	
	*//**
	 * 异步根据省份获取地区
	 * @param response
	 *//*
	@RequestMapping("/getDistricts")
	public void getDistricts(@RequestParam Long provinceId, HttpServletResponse response){
		AjaxUtils.renderJson(response, getFilters(cityService.getDistricts(provinceId)));
	}
	
	*//**
	 * 异步根据省份获取地区
	 * @param response
	 *//*
	@RequestMapping("/getCitys")
	public void getCitys(@RequestParam Long districtId, HttpServletResponse response){
		AjaxUtils.renderJson(response, getFilters(cityService.getCitys(districtId)));
	}*/
	

	private List<IdValueOption> getFilters(List<IdValueOption> list) {
		final List<Long> userCitys = adminOrgCityService.getAdminUserCitys(AdminSessionHolder.getAdminUserId());
		List<IdValueOption> filters = CommonCollectionUtils.filterCollection(list, new FetchCondition<IdValueOption>() {
			public boolean needFetch(IdValueOption t){
				return userCitys.contains(t.getId());
			}
		});
		return filters;
	}
}
