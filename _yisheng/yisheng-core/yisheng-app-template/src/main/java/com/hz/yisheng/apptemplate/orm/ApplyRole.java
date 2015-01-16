package com.hz.yisheng.apptemplate.orm;

import java.io.Serializable;
/**
 * 应用角色
 * @author loard
 *
 */
public class ApplyRole implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5901073360926407051L;
	/**角色编号*/
	private Long id;
	/**排序*/
	private Integer sort;
	/**角色名称*/
	private String roleName;
	/**角色创建者*/
	private Long creator;
	/**角色最后修改者*/
	private Long updater;
	/**选择框*/
	private boolean checked;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Long getUpdater() {
		return updater;
	}
	public void setUpdater(Long updater) {
		this.updater = updater;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
}
