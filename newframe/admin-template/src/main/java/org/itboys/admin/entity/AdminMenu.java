package org.itboys.admin.entity;

import com.google.code.morphia.annotations.Entity;

@Entity(value = "AdminMenu", noClassnameStored = true)
public class AdminMenu extends BaseAdminEntity{

	private static final long serialVersionUID = 3569918421333922181L;
	private String menuName;
	private String url;
	private Long parentId=0L;
	private Integer level;
	private Integer sort;
	private Integer isDeleted=0;
	private String fullPath;
	private String fullNamePath;
	
	public Long get_parentId(){
		return this.parentId;
	}

	public Long getParentId() {
		return parentId;
	}
	public boolean getExpanded() {
		return true;
	}
	public boolean getLoaded() {
		return true;
	}
	public boolean getIsLeaf() {
		return parentId==null||parentId==0?false:true;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getFullNamePath() {
		return fullNamePath;
	}

	public void setFullNamePath(String fullNamePath) {
		this.fullNamePath = fullNamePath;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
}
