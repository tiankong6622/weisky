package org.itboys.admin.dto;

import java.util.List;

import org.itboys.admin.entity.AdminMenu;

/**
 * 异步登入完后 返回给前台的
 * @author chenjunhui
 *
 */
public class AdminUserData {
	
	private boolean logined;//是否成功登入的
	
	private String username;
	
	private String name;
	
	private Long orgId;
	
	private String orgName;
	
	private Long postId;
	
	private String postName;
	
	private List<Long> rolesList;
	
	private List<AdminMenu> menus;
	
	private List<AdminMenuDto> adminUserDato;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public List<Long> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Long> rolesList) {
		this.rolesList = rolesList;
	}

	public List<AdminMenuDto> getAdminUserDato() {
		return adminUserDato;
	}

	public void setAdminUserDato(List<AdminMenuDto> adminUserDato) {
		this.adminUserDato = adminUserDato;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<AdminMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<AdminMenu> menus) {
		this.menus = menus;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}
}
