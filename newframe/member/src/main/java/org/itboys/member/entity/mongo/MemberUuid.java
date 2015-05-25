package org.itboys.member.entity.mongo;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 会员UUID实体类
 * @author huml
 *
 */
@Entity(value = "memberUuid", noClassnameStored = true)
public class MemberUuid extends MemberBaseEntity{

	private static final long serialVersionUID = 4218488698302216785L;
	private Long memberId;
	private String uuidIos;
	private String uuidAnd;
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getUuidIos() {
		return uuidIos;
	}
	public void setUuidIos(String uuidIos) {
		this.uuidIos = uuidIos;
	}
	public String getUuidAnd() {
		return uuidAnd;
	}
	public void setUuidAnd(String uuidAnd) {
		this.uuidAnd = uuidAnd;
	}
	
	

}
