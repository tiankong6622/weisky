package com.hz.yisheng.commondata.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 功能模块管理
 * 
 * @author GuoRui
 * 
 */
public class Module extends BaseAdminEntity {

	private static final long serialVersionUID = -5242533551846372190L;
	private String name;
	private String desc;
	private Integer isDeleted;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
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
