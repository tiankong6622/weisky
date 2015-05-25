package org.itboys.admin.entity;

import com.google.code.morphia.annotations.Entity;

/**
 * 管理系统后台 功能模块定义
 * 
 * @author huml
 * 
 */
@Entity(value = "AdminModule", noClassnameStored = true)
public class AdminModule extends BaseAdminEntity {
	private static final long serialVersionUID = -3158225369714368283L;
	private String name;
	private String desc;
	private Integer isDeleted = 0;

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
