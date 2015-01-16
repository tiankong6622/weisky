package com.hz.yisheng.commondata.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class TreeCodesDTO {

	private Long id;
	private String type;
	private String key;
	private String value;
	private Long parentId;
	private Long projectId;
	private boolean root=false;
	
	private List<TreeCodesDTO> children = Lists.newArrayList();
	
	public Long get_parentId(){
		return this.parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public List<TreeCodesDTO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeCodesDTO> children) {
		this.children = children;
	}
	
	
}
