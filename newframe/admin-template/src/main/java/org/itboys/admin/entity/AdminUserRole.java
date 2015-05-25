package org.itboys.admin.entity;

import com.google.code.morphia.annotations.Entity;

/**
 * 用户-角色 关联关系表
 * 
 * @author huml
 * 
 */
@Entity(value="AdminUserRole", noClassnameStored = true)
public class AdminUserRole extends BaseAdminEntity {
	private static final long serialVersionUID = 6307505089100952772L;
	private Long userId;
	private Long roleId;
	private Integer type;
	private Integer isDeleted=0;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
