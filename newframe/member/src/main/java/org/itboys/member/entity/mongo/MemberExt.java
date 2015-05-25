package org.itboys.member.entity.mongo;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;


/**
 * 会员扩展信息
 * @author huml
 *
 */
@Entity(value = "memberExt", noClassnameStored = true)
public class MemberExt extends MemberBaseEntity {
	
	private static final long serialVersionUID = 1695800597406862382L;
	private Long memberId;
	private String key;
	private String value;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
