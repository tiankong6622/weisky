package org.itboys.admin.tools;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.itboys.admin.constant.AdminConstants;
import org.itboys.admin.dto.AdminUserDTO;
import org.itboys.admin.entity.AdminPermission;
import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.service.AdminMenuService;
import org.itboys.admin.service.AdminPermissionService;
import org.itboys.admin.service.AdminRoleService;
import org.itboys.admin.service.AdminUserService;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.framework.spring.context.SpringContextHolder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class AdminHolder {

	public static AdminUserDTO getAdminUser(Long userId){
		 AdminUserService adminUserService = SpringContextHolder.getBean("adminUserService");
		return adminUserService.getUserById(userId);
	}
	

	/**
	 * 组装登入信息
	 * @param request
	 * @param proUser
	 */
	@SuppressWarnings("unchecked")
	public static  void initLoginUser(HttpServletRequest request, AdminUser proUser) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUserid(proUser.getId());
		boolean isSuper = AdminConstants.TYPE_SUPER.equals(proUser.getType());
		if(!isSuper){//不是超级用户才分配
			//获取权限ID
			AdminRoleService adminRoleService = SpringContextHolder.getBean("adminRoleService");
			List<Long> roleIds = adminRoleService.getAdminRoleByUserIds(proUser.getId());
			loginUser.setRoleIds(roleIds);
			AdminMenuService adminMenuService = SpringContextHolder.getBean("adminMenuService");
			loginUser.setMenuIds(adminMenuService.getAdminMenuByRoleIds(roleIds));
			//获得相关权限集合
			AdminPermissionService adminPermissionBO = SpringContextHolder.getBean("adminPermissionService");
			List<AdminPermission> permissions = CommonCollectionUtils.isEmpty(roleIds)?Collections.EMPTY_LIST:adminPermissionBO.getSelectedPermissionByRoleIds(roleIds);
			Map<String,List<String>> permissionMap = Maps.newHashMap();
			for(AdminPermission ap:permissions){
				if(StringUtils.isBlank(ap.getCode()) || StringUtils.isBlank(ap.getType())){
					continue;
				}
				if(permissionMap.containsKey(ap.getType())){
					permissionMap.get(ap.getType()).add(ap.getCode());
				}else{
					permissionMap.put(ap.getType(), Lists.newArrayList(ap.getCode()));
				}
			}
			loginUser.setPermissionMap(permissionMap);
		}else{
			loginUser.setType(AdminConstants.TYPE_SUPER);
		}
		request.getSession().setAttribute(WebConstants.SessionKey.ADMIN_USER, loginUser);
	}
}
