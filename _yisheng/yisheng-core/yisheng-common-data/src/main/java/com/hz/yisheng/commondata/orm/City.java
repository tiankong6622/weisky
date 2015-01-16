package com.hz.yisheng.commondata.orm;

import org.javafans.orm.entity.base.BaseEntity;

/**
 * 省市县表
 * @author ChenJunhui
 */
public class City extends BaseEntity {

	private static final long serialVersionUID = -3712533166237860098L;
	
	private String name;
	private Long parentId;
	private Integer level;
	private String fullName;
	private String code;
	private String initial;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
}
