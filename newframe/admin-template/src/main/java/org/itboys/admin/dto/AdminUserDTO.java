package org.itboys.admin.dto;

import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.entity.AdminUserOrgPost;
import org.itboys.admin.entity.BaseAdminEntity;

public class AdminUserDTO extends BaseAdminEntity{
	
	private static final long serialVersionUID = -558786922549087592L;

	private AdminUser adminUser;
	
	private AdminUserOrgPost userOrgPost;
	
	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public AdminUserOrgPost getUserOrgPost() {
		return userOrgPost;
	}

	public void setUserOrgPost(AdminUserOrgPost userOrgPost) {
		this.userOrgPost = userOrgPost;
	}
}
