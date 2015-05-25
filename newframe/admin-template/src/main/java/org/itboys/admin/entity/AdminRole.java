package org.itboys.admin.entity;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.code.morphia.annotations.Entity;

/**
 * 角色实体
 * @author WeiSky
 *
 */
@Entity(value="AdminRole", noClassnameStored = true)
public class AdminRole extends BaseAdminEntity{

	private static final long serialVersionUID = -8338482576886338092L;

	private String name;//角色名称
	
	private String desc;//角色描述
	
	private Integer isDeleted = 0;//逻辑删除标记 0有效 1删除
	
	/**
	 * 角色关联的权限ID
	 */
	private List<Long> permissionIds;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getDesc() {
		return desc;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Long> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Long> permissionIds) {
		this.permissionIds = permissionIds;
	}
	
}
