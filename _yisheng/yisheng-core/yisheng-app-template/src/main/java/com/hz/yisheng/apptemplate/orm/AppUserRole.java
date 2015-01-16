package com.hz.yisheng.apptemplate.orm;

import java.io.Serializable;

public class AppUserRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -523396823003327237L;
	/**用户id*/
	private Long userId;
	/**应用角色id*/
	private Long roleId;
	/**用户姓名*/
	private String userName;
	/**部门编号*/
	private Long departId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	

}
