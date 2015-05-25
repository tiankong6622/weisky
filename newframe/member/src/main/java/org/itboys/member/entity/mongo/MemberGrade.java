package org.itboys.member.entity.mongo;

import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 会员等级
 * @author huml
 *
 */
@Entity(value = "memberGrade", noClassnameStored = true)
public class MemberGrade extends MemberBaseEntity{

	
	private static final long serialVersionUID = -2461071384845269797L;
	private String name;
	private String desc;
	private Date createTime;
	private Integer isDeleted;
	private String moduleId; //模板id

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
