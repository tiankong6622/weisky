package com.hz.yisheng.apptemplate.orm;

import java.io.Serializable;
import java.util.List;
/**
 * tree的模块json格式
 * @author loard
 *
 */
public class TemplatePermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7774015745818539165L;
	private Long id;
	private String text;
	private boolean checked = false;
	private List<TemplatePermission> children;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<TemplatePermission> getChildren() {
		return children;
	}
	public void setChildren(List<TemplatePermission> children) {
		this.children = children;
	}
	
}
