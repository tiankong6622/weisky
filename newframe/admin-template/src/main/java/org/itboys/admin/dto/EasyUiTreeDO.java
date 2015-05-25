package org.itboys.admin.dto;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * easyui 树相关的json 串数据对象
 * @author ChenJunhui
 *
 */
public class EasyUiTreeDO {

	private Long id;
	private String text;
	private boolean checked=false;
	
	private List<EasyUiTreeDO> children = Lists.newArrayList();

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

	public List<EasyUiTreeDO> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUiTreeDO> children) {
		this.children = children;
	}
	
}
