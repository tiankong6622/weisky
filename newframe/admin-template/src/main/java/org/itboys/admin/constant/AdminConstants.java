package org.itboys.admin.constant;

import java.io.Serializable;

import org.itboys.admin.entity.AdminMenu;

import com.google.common.base.Function;

/**
 * 管理系统常量
 * @author huml
 *
 */
public class AdminConstants implements Serializable{
	

	private static final long serialVersionUID = -7880179105277614130L;
	public static final Integer TYPE_SUPER = 1;// 超级用户
	public static final Integer TYPE_COMMON = 0;// 普通用户
	public interface AdminPermissionType{
		public static final String TYPE_MENU="menu";//菜单权限
		public static final String TYPE_ACCESS="access";//访问权限
		public static final String TYPE_UI="access";//展示权限
		public static final String TYPE_VIR="vir";//虚拟权限
	}
	
	public interface transform{
		public static Function<AdminMenu, Long> getMenuId = new Function<AdminMenu, Long>() {
			@Override
			public Long apply(AdminMenu input) {
				return input.getId();
			}
		};
	}
}
