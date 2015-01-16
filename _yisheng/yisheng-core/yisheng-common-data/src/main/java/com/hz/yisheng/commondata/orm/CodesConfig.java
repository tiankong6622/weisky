package com.hz.yisheng.commondata.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

public class CodesConfig extends BaseAdminEntity {

	private static final long serialVersionUID = -6706786742822213458L;
	private String name;
	private String type;
	private Long projectId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
