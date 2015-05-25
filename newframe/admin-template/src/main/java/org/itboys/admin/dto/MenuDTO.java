package org.itboys.admin.dto;

import java.util.List;

import org.itboys.admin.entity.AdminMenu;

import com.google.common.collect.Lists;

/**
 * 菜单DTO
 * @author Administrator
 *
 */
public class MenuDTO {

	private AdminMenu menu;
	private List<AdminMenu> children=Lists.newArrayList();
	
	public AdminMenu getMenu() {
		return menu;
	}
	public void setMenu(AdminMenu menu) {
		this.menu = menu;
	}
	public List<AdminMenu> getChildren() {
		return children;
	}
	public void setChildren(List<AdminMenu> children) {
		this.children = children;
	}
	
}
