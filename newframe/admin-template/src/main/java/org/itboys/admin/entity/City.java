package org.itboys.admin.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 省市县表
 * @author ChenJunhui
 */
public class City {

	private String name;
	private Long parentId;
	private Integer level;
	private String fullName;
	private String code;
	private String initial;
	private Long sort;
	private Long id;
	private Date createTime;
	private Date updateTime;
	
	public boolean getIsLeaf() {
		return level!=3?false:true;
	}
	public Long get_parentId() {
		return parentId;
	}
	public Long getId() {
		return id;
	}
	public boolean getExpanded() {
		return true;
	}
	public boolean getLoaded() {
		return true;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
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
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
}
