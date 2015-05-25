package org.itboys.admin.dto;

import java.util.List;


public class AdminMenuDto {

	private long id;
	
	private String menuName;//菜单名称
	
	private String url;//菜单连接地址
	
	private long pid;//父菜单ID
	
	private boolean expanded = false;//是否展开，默认不展开
	
	private List<AdminMenuDto> children;

	
	
	
	public List<AdminMenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<AdminMenuDto> children) {
		this.children = children;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	
}
