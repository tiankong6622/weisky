package org.itboys.admin.service;

import java.util.List;

import org.itboys.admin.entity.AdminMenu;
import org.itboys.admin.entity.AdminUser;
import org.itboys.commons.utils.encryption.Digests;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

public class InitAdminDataService implements InitializingBean {
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminMenuService adminMenuService;
	
	public static String username;//用户名
	private String adminMenuUrl; //系统管理url
	private String menuManageUrl;//菜单管理url
	private String userManageUrl;//用户管理url
	private String roleManageUrl;//角色管理url
	private String permissionManageUrl;//权限管理url
	private String orgManageUrl;//组织管理url
	private String postManageUrl;//职务管理url
	private String moduleManagerUrl;//功能模块管理url
	
	/* 超级用户名  */
	private static final String SYSTEM_ROLE_SUPER = username;
	/* 顶级菜单名  */
	private static final String SYSTEM_MENU_SUPER = "系统管理";
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		/* 若数据库中没有admin超级用户，自动添加超级管理员   */
		if(!adminUserService.exists("username", username)){
			AdminUser au = new AdminUser();
			au.setUsername(username);
			au.setName(username);
			au.setPassword(Digests.md5("111111"));
			au.setType(1);
			adminUserService.saveWithoutLogin(au);
		}
		
		/* 若没有顶级菜单，则自动创建   */
		if(!adminMenuService.exists("menuName", SYSTEM_MENU_SUPER)){
			List<AdminMenu> menuList = Lists.newArrayListWithExpectedSize(8);
			/* 创建顶级菜单  */
			AdminMenu am = new AdminMenu();
			am.setMenuName(SYSTEM_MENU_SUPER);
			am.setUrl(adminMenuUrl);
			am.setFullPath(adminMenuUrl);
			am.setParentId(0l);
			am.setLevel(1);
			adminMenuService.saveWithoutLogin(am);
			
			/* 子菜单:菜单管理   */
			AdminMenu am2 = new AdminMenu();
			am2.setMenuName("菜单管理");
			am2.setUrl(menuManageUrl);
			am2.setFullPath(menuManageUrl);
			am2.setParentId(am.getId());
			am2.setLevel(2);
			
			/* 子菜单:用户管理   */
			AdminMenu am3 = new AdminMenu();
			am3.setMenuName("用户管理");
			am3.setUrl(userManageUrl);
			am3.setFullPath(userManageUrl);
			am3.setParentId(am.getId());
			am3.setLevel(2);
			
			/* 子菜单:角色管理   */
			AdminMenu am4 = new AdminMenu();
			am4.setMenuName("角色管理");
			am4.setUrl(roleManageUrl);
			am4.setFullPath(roleManageUrl);
			am4.setParentId(am.getId());
			am4.setLevel(2);
			
			/* 子菜单:权限管理   */
			AdminMenu am5 = new AdminMenu();
			am5.setMenuName("权限管理");
			am5.setUrl(permissionManageUrl);
			am5.setFullPath(permissionManageUrl);
			am5.setParentId(am.getId());
			am5.setLevel(2);
			
			/* 子菜单:组织管理   */
			AdminMenu am6 = new AdminMenu();
			am6.setMenuName("组织管理");
			am6.setUrl(orgManageUrl);
			am6.setFullPath(orgManageUrl);
			am6.setParentId(am.getId());
			am6.setLevel(2);
			
			/* 子菜单:职务管理   */
			AdminMenu am7 = new AdminMenu();
			am7.setMenuName("职务管理");
			am7.setUrl(postManageUrl);
			am7.setFullPath(postManageUrl);
			am7.setParentId(am.getId());
			am7.setLevel(2);
			
			/* 子菜单:职务管理   */
			AdminMenu am8 = new AdminMenu();
			am8.setMenuName("功能模块管理");
			am8.setUrl(moduleManagerUrl);
			am8.setFullPath(moduleManagerUrl);
			am8.setParentId(am.getId());
			am8.setLevel(2);
			
			menuList.add(am2);
			menuList.add(am3);
			menuList.add(am4);
			menuList.add(am5);
			menuList.add(am6);
			menuList.add(am7);
			menuList.add(am8);
			
			adminMenuService.saveWithoutLogin(menuList);
		}
		
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	public void setAdminMenuService(AdminMenuService adminMenuService) {
		this.adminMenuService = adminMenuService;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAdminMenuUrl(String adminMenuUrl) {
		this.adminMenuUrl = adminMenuUrl;
	}

	public void setMenuManageUrl(String menuManageUrl) {
		this.menuManageUrl = menuManageUrl;
	}

	public void setUserManageUrl(String userManageUrl) {
		this.userManageUrl = userManageUrl;
	}

	public void setRoleManageUrl(String roleManageUrl) {
		this.roleManageUrl = roleManageUrl;
	}

	public void setPermissionManageUrl(String permissionManageUrl) {
		this.permissionManageUrl = permissionManageUrl;
	}

	public void setModuleManagerUrl(String moduleManagerUrl) {
		this.moduleManagerUrl = moduleManagerUrl;
	}

	public void setOrgManageUrl(String orgManageUrl) {
		this.orgManageUrl = orgManageUrl;
	}

	public void setPostManageUrl(String postManageUrl) {
		this.postManageUrl = postManageUrl;
	}

}
