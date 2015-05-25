package org.itboys.member.entity.mongo;

import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 通用会员关联对象表
 * @author huml
 *
 */
@Entity(value = "memberRel", noClassnameStored = true)
public class MemberRel extends MemberBaseEntity{

	private static final long serialVersionUID = 456523351L;
	private Long memberId;
	private String relType;
	private Long relObjId;
	private Date createTime;
	private Integer isDeleted;
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getRelType() {
		return relType;
	}
	public void setRelType(String relType) {
		this.relType = relType;
	}
	public Long getRelObjId() {
		return relObjId;
	}
	public void setRelObjId(Long relObjId) {
		this.relObjId = relObjId;
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
