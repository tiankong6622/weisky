package com.hz.yisheng.oa.workflow.dto;

import java.util.List;

public class TypeJson {

	/** ID */
	private Integer id;
	/** 内容 */
	private String text;

	private List<TypeJson> children;

	public TypeJson(Integer id, String text) {
		this.id = id;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TypeJson> getChildren() {
		return children;
	}

	public void setChildren(List<TypeJson> children) {
		this.children = children;
	}

}
