package org.javafans.dto.login;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 登入用户基本信息DTO 一般管理后台用
 * @author ChenJunHui
 *
 */
public class LoginUser implements Serializable{
	
	private static final long serialVersionUID = -3656807123289405682L;
	private Long userid;
	//登入用户的类型
	private int type = 0;
	//用户拥有的角色ID
	private List<Long> roleIds;
	//用户拥有的菜单ID
	private List<Long> menuIds;
	//用户拥有的权限类型 和改权限类型
	private Map<String,List<String>> permissionMap;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public List<Long> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Long> menuIds) {
		this.menuIds = menuIds;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Map<String, List<String>> getPermissionMap() {
		return permissionMap;
	}

	public void setPermissionMap(Map<String, List<String>> permissionMap) {
		this.permissionMap = permissionMap;
	}
}
