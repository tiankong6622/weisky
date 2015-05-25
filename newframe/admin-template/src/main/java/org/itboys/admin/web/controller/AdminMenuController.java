package org.itboys.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.admin.dto.AdminMenuDto;
import org.itboys.admin.entity.AdminMenu;
import org.itboys.admin.service.AdminMenuService;
import org.itboys.admin.tools.AdminMenuSortUtil;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.spring.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * 菜单管理
 * @author WeiSky
 *
 */
@RestController 
@RequestMapping("/admin/menu") 
public class AdminMenuController extends BaseController{

	@Autowired
	private AdminMenuService adminMenuService;
	
	/**
	 * 获取菜单列表，并按一定的数据格式返回
	 * @param parentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllMenu")
	public List<AdminMenuDto> findAllMenu(){
		return adminMenuService.findAllMenu2();
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public void list(HttpServletRequest request , HttpServletResponse response){
		List<AdminMenu> menus = adminMenuService.list(request);
		
		JsonPageUtils.renderJsonPage(menus!=null&&menus.size()>0?menus.size():0L, AdminMenuSortUtil.sortbyParam(menus), response);
	}
	
	/**
	 * 新增/修改菜单
	 * @param am
	 */
	@ResponseBody
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void add(@ModelAttribute AdminMenu am , HttpServletResponse response){
		try{
			
			if(am.getId() == 0l){
				adminMenuService.save(am);
				if(am.getParentId()==0){
					am.setFullPath("/"+am.getId()+"/");
					am.setFullNamePath("/"+am.getMenuName()+"/");
				}else {
					AdminMenu parentMenu = adminMenuService.getById(am.getParentId());
					am.setFullPath("/"+parentMenu.getId()+"/"+am.getId()+"/");
					am.setFullNamePath("/"+parentMenu.getMenuName()+"/"+am.getMenuName()+"/");
				}
				adminMenuService.update(am);
			}else {
				if(am.getParentId()==0){
					AdminMenu temp =am;
					am = adminMenuService.getById(am.getId());
					am.setMenuName(temp.getMenuName());
					am.setSort(temp.getSort());
					am.setUrl(temp.getUrl());
					am.setFullPath("/"+am.getId()+"/");
					am.setFullNamePath("/"+am.getMenuName()+"/");
				}else {
					AdminMenu parentMenu = adminMenuService.getById(am.getParentId());
					am.setFullPath("/"+parentMenu.getId()+"/"+am.getId()+"/");
					am.setFullNamePath("/"+parentMenu.getMenuName()+"/"+am.getMenuName()+"/");
				}
				adminMenuService.update(am);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		}catch (Exception e) {
			logger.error("AdminMenu save error",e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	/**
	 * 根据菜单ID，删除一条数据
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public void delete(@PathVariable("id")Long id,HttpServletResponse response){
		try {
			adminMenuService.deleteMenu(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		}catch (Exception e) {
			logger.error("AdminMenu delete error",e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	/**
	 * 根据ID，获取一条数据
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMenu/{id}")
	public AdminMenu getById(@PathVariable("id")Long id){
		return adminMenuService.getById(id);
	}
	
}
