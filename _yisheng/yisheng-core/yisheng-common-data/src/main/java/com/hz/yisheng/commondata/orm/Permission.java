package com.hz.yisheng.commondata.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 权限实体类
 * 
 * @author GuoRui
 * 
 */
public class Permission extends BaseAdminEntity{


	private static final long serialVersionUID = -6337658657763454417L;
	private Long moduleId;
	private String name;
	private String code;
	private String type;//权限类型 1. 访问权限 access 2.ui元素权限 ui 3. 虚拟权限 vir

	private String desc;
	private Integer isDeleted;

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
