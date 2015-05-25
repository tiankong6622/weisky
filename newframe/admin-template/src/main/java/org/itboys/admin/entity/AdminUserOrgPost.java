package org.itboys.admin.entity;

import com.google.code.morphia.annotations.Entity;

/**
 * 用户-部门-职务 对照关系表
 * 
 * @author huml
 */
@Entity(value="AdminUserOrgPost", noClassnameStored = true)
public class AdminUserOrgPost extends BaseAdminEntity {
	private static final long serialVersionUID = -4974909298473723315L;
	private Long orgId;
	private Long postId;
	private Integer isManager;
	private Integer isDeleted=0;
	private Long userId;
	private String deptName;
	private String postName;
	private Integer isArea;

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsArea() {
		return isArea;
	}

	public void setIsArea(Integer isArea) {
		this.isArea = isArea;
	}

}
