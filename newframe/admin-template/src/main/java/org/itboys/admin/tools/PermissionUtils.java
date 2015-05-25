package org.itboys.admin.tools;

import java.util.List;

import org.itboys.admin.constant.AdminConstants;
import org.itboys.admin.entity.AdminUser;

/**
 * 判断一个用户是否拥有某个权限
 * @author huml
 *
 */
public class PermissionUtils {

	/**
	 * 判断当前登入用户是否拥有某一个展示权限
	 * @param code
	 * @return
	 */
	public static boolean hasViewPermission(String code){
		return hasPermission(AdminConstants.AdminPermissionType.TYPE_UI, code);
	}
	
	/**
	 * 判断当前登入用户是否拥有某一个虚拟权限权限
	 * @param code
	 * @return
	 */
	public static boolean hasVirPermission(String code){
		return hasPermission(AdminConstants.AdminPermissionType.TYPE_VIR, code);
	}
	
	/**
	 * 判断当前登入用户是否拥有某一个访问权限
	 * @param code
	 * @return
	 */
	public static boolean hasAccessPermission(String code){
		return hasPermission(AdminConstants.AdminPermissionType.TYPE_ACCESS, code);
	}
	
	/**
	 * 判断当前登入用户是否拥有某一种类型的权限
	 * @param permissionType
	 * @param code
	 * @return
	 */
	public static boolean hasPermission(String type,String code){
		LoginUser user = AdminSessionHolder.getAdminLoginUser();
		if(user==null){
			return false;
		}
		if(user.getType()==AdminConstants.TYPE_SUPER){//超级用户拥有所有权限
			return true;
		}
		List<String> viewPermission = user.getPermissionMap().get(type);
		return viewPermission!=null && viewPermission.contains(code);
	}
	
}
