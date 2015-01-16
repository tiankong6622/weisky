package com.hz.yisheng.commondata.orm;

import java.sql.Date;

import org.javafans.orm.entity.base.BaseAdminEntity;

public class TreeCode extends BaseAdminEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3378328757313056734L;
	
	public static final Long DEFAULT_PROJECT_ID = 0l;
	private Long id;//编号
	private Long projectId = DEFAULT_PROJECT_ID;//项目id   默认为0
	private String type;//类型
	private String key;//key值
	private String value;//value值
	private String desc;//描述
	private Long parentId;//对应父类的id

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	

}
