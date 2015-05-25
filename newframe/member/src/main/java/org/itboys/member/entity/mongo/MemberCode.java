package org.itboys.member.entity.mongo;

import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * @author dhl
 * 
 */
@Entity(value = "memberCode", noClassnameStored = true)
public class MemberCode extends MemberBaseEntity{
	private static final long serialVersionUID = 168414615422L;
	private String mobile;
	private String code;
	private Date time;
	
	public MemberCode() {
		super();
	}

	public MemberCode(String mobile, String code) {
		super();
		this.mobile = mobile;
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
