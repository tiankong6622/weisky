package org.itboys.admin.entity;

import java.io.UnsupportedEncodingException;

import com.google.code.morphia.annotations.Entity;

/**
 * 权限实体
 * @author WeiSky
 *
 */
@Entity(value = "AdminPermission", noClassnameStored = true)
public class AdminPermission extends BaseAdminEntity{
	private static final long serialVersionUID = 7330572753999558966L;

	private long moduleId;//权限所属模块的ID
	
	private  String name;//权限名称
	
	private String code;//权限code 视type定 为1的时候 为访问权限地址 为2 的时候为 ui权限 的code 为3的时候为虚拟权限的key 等等
	
	private String type;//access访问权限 ui权限 vir虚拟权限 menu 菜单 see AdminConstant.PERMISSION_TYPE_*
	
	private String desc;//描述
	
	private Integer isDeleted = 0;//逻辑删除标记 0有效 1删除

	public String getDesc() {
		return desc;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
