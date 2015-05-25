package org.itboys.member.entity.mongo;

import com.google.code.morphia.annotations.Entity;
import org.itboys.member.entity.mongo.MemberBaseEntity;
/**
 * 三方应用联合登入用户信息
 * @author huml
 *
 */
@Entity(value = "memberThirdAccount", noClassnameStored = true)
public class MemberThirdAccount extends MemberBaseEntity{

	private static final long serialVersionUID = -3224349412755239378L;
	private Long memberId;
	private String externalNo;
	private String externalName;
	private String from;
	private String token;
	private String field1;
	private String field2;
	private String field3;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getExternalNo() {
		return externalNo;
	}

	public void setExternalNo(String externalNo) {
		this.externalNo = externalNo;
	}

	public String getExternalName() {
		return externalName;
	}

	public void setExternalName(String externalName) {
		this.externalName = externalName;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

}
