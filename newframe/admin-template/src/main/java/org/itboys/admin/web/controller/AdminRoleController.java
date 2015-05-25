package org.itboys.admin.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.admin.dto.EasyUiTreeDO;
import org.itboys.admin.entity.AdminMenu;
import org.itboys.admin.entity.AdminModule;
import org.itboys.admin.entity.AdminPermission;
import org.itboys.admin.entity.AdminRole;
import org.itboys.admin.service.AdminMenuService;
import org.itboys.admin.service.AdminModuleService;
import org.itboys.admin.service.AdminPermissionService;
import org.itboys.admin.service.AdminRoleService;
import org.itboys.admin.tools.AdminPermissionCheck;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 角色
 * @author huml
 *
 */
@Controller
@RequestMapping(value = "/admin/role")
public class AdminRoleController extends BaseController {

	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private AdminModuleService adminModuleService;
	@Autowired
	private AdminPermissionService adminPermissionService;
	@Autowired
	private AdminMenuService adminMenuService;
	
	/**
	 * 分页查询管理用户
	 * @returns
	 */
	@RequestMapping("/list")
	@AdminPermissionCheck("adminRole:list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Page<AdminRole> page = adminRoleService.pageQuery(request);
		JsonPageUtils.renderJsonPage(page.getTotal(), page.getData(), response);
	}
	
	/**
	 * 异步获取单个用户对象
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getRole/{id}")
	@AdminPermissionCheck("adminRole:getRole")
	public void getUser(@PathVariable("id") Long id,HttpServletResponse response){
		AdminRole role = adminRoleService.getById(id);
		AjaxUtils.renderJson(response, role);
	}
	
	/**
	 * 异步保存
	 */
	@RequestMapping("/save")
	@AdminPermissionCheck("adminRole:save")
	public void save(@ModelAttribute AdminRole role,HttpServletResponse response){
		try {
			if(role.getId()==0l){
				adminRoleService.insert(role);
			}else{
				adminRoleService.doUpdate(role);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
		
	}
	
	/**
	 * 加载所有权限和模块信息 并将角色已经关联的设置成选中
	 * @param id
	 * @param response
	 */
	@RequestMapping("getRolePermission/{id}")
	@AdminPermissionCheck("adminRole:getRolePermission")
	public void getRolePermission(@PathVariable("id") Long id,HttpServletResponse response){
		List<AdminModule> modules = adminModuleService.getModule();//获取具有权限的模板
		List<AdminPermission> permissions = adminPermissionService.list();
		List<Long> roleSelPermissionIds = adminPermissionService.getSelectedPermissionIdsByRoleId(Lists.newArrayList(id),"access","ui","vir");
		List<EasyUiTreeDO> tree = Lists.newArrayListWithCapacity(modules.size());
		for(AdminModule am:modules){
			EasyUiTreeDO module = new EasyUiTreeDO();
			module.setId(am.getId());
			module.setText(am.getName());
			for(AdminPermission ap:permissions){
				if(ap.getModuleId()==am.getId()){
					EasyUiTreeDO permission = new EasyUiTreeDO();
					permission.setId(ap.getId());
					permission.setText(ap.getName());
					if(roleSelPermissionIds.contains(ap.getId())){
						permission.setChecked(true);
					}
					module.getChildren().add(permission);
				}
			}
			tree.add(module);
		}
		AjaxUtils.renderJson(response, tree);
	}
	
	@RequestMapping("getMenuPermission/{id}")
	@AdminPermissionCheck("adminRole:getMenuPermission")
	public void getMenuPermission(@PathVariable("id") Long id,HttpServletResponse response){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(0);
		List<AdminMenu> menuList = adminMenuService.list(param);
		List<Long> menuIds = adminMenuService.getAdminMenuByRoleIds(id);
		List<AdminMenu> roots = adminMenuService.getRootsMenusIgnorePermission(menuList);
		List<EasyUiTreeDO> tree = Lists.newArrayListWithCapacity(roots.size());
		for(AdminMenu menu:roots){
			EasyUiTreeDO menuTree = new EasyUiTreeDO();
			menuTree.setId(menu.getId());
			menuTree.setText(menu.getMenuName());
			if(menuIds.contains(menu.getId())){
				menuTree.setChecked(true);
			}
			tree.add(menuTree);
		}
		for(EasyUiTreeDO uitree:tree){
			for(AdminMenu menu:menuList){
				if(menu.getParentId().equals(uitree.getId())){
					EasyUiTreeDO menuTree = new EasyUiTreeDO();
					menuTree.setId(menu.getId());
					menuTree.setText(menu.getMenuName());
					if(menuIds.contains(menu.getId())){
						menuTree.setChecked(true);
					}
					uitree.getChildren().add(menuTree);
				}
			}
		}
		AjaxUtils.renderJson(response, tree);
	}
	
	@RequestMapping("/relPermissionAndMenu")
	@AdminPermissionCheck("adminRole:relPermissionAndMenu")
	public void relPermissionAndMenu(HttpServletResponse response,HttpServletRequest request){
		try {
			String str = request.getParameter("permissionIds");
			Long roleId = Long.valueOf(request.getParameter("roleId"));	
			String types = request.getParameter("types");
			List<Long> permissionIds = CommonCollectionUtils.splictToLongList(str, ",");
			if(types.equals("menu")){
				adminRoleService.insertRoleMenu(roleId, permissionIds,types);
			}else{
				adminRoleService.insertRolePermission(roleId, permissionIds,types);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("relPermissionAndMenu error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	/**
	 * 异步删除
	 */
	@RequestMapping("/delete/{id}")
	@AdminPermissionCheck("adminRole:delete")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			adminRoleService.doDelete(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("admin role delete error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
}
