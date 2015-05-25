package org.itboys.member.entity.mongo;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;

@Entity(value = "memberSnsOper", noClassnameStored = true)
public class MemberSnsOper extends MemberBaseEntity {

	private static final long serialVersionUID = 6806550514935804474L;
	private Long memberId;
	private Long objId;
	private Date operTime;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

}
