package com.hz.crf.model.orm;

import java.io.Serializable;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * 省市县
 * @author weisky
 *
 * May 28, 2015
 */
@Entity(value = "t_city", noClassnameStored = true)
public class TCity implements Serializable{

	private static final long serialVersionUID = 8187933089313841172L;

	@Id
	private long id;
	
	private String name;
	private Long parentId;
	private Integer level;//等级
	private String fullName;// 名称全路径
	private String code;//省份代码
	private String initial;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
