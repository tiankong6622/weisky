package org.javafans.cache;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TestCategory implements Serializable {
	private static final long serialVersionUID = -4643072859378322129L;
	private Long id;
	private String name;
	private Long parentId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
