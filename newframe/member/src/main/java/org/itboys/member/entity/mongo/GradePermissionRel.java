package org.itboys.member.entity.mongo;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;
/**
 * 会员等级权限关联
 * @author huml
 *
 */
@Entity(value = "gradePermissionRel", noClassnameStored = true)
public class GradePermissionRel extends MemberBaseEntity {

	private static final long serialVersionUID = -7542939170701066126L;
	private Long gradeId;
	private Long relObjectId;
	private String relType;
	private Integer isDeleted;

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getRelObjectId() {
		return relObjectId;
	}

	public void setRelObjectId(Long relObjectId) {
		this.relObjectId = relObjectId;
	}

	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
