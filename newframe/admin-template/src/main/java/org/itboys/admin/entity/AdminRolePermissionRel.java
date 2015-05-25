package org.itboys.admin.entity;

import com.google.code.morphia.annotations.Entity;

/**
 * 角色和各种权限的关联关系表
 * 
 * @author huml
 * 
 */
@Entity(value="AdminRolePermissionRel", noClassnameStored = true)
public class AdminRolePermissionRel extends BaseAdminEntity {

	private static final long serialVersionUID = -4383999224595714189L;
	private Long roleId;
	private Long relObjectId;
	private String relType;
	private Integer isDeleted = 0;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRelObjectId() {
		return relObjectId;
	}

	public void setRelObjectId(Long relObjectId) {
		this.relObjectId = relObjectId;
	}

	/*
	 * 关联类型 比如 菜单:menu 访问权限:access ui:ui 对比权限:compare
	 */
	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
