package org.itboys.admin.entity;

import java.io.UnsupportedEncodingException;

import com.google.code.morphia.annotations.Entity;

/**
 * 职务实体
 * @author WeiSky
 *
 */
@Entity(value = "AdminPost", noClassnameStored = true)
public class AdminPost extends BaseAdminEntity{

	private static final long serialVersionUID = -6581827318352983305L;

	private String name;//职务名称
	
	private String desc;//描述
	
	private long parentId;//直属上级领导ID
	
	private Integer level;//职务级别,值越小职务级别越大
	
	private Integer isDeleted = 0;//逻辑删除标记 0有效 1删除

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Long get_parentId(){
		return this.parentId;
	}
	
}
