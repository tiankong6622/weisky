package org.itboys.member.entity.mongo;

import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 会员激活验证码相关记录
 * @author ChenJunhui
 *
 */
@Entity(value = "memberActiveCode", noClassnameStored = true)
public class MemberActiveCode extends MemberBaseEntity {
	
	private static final long serialVersionUID = 101601885555124355L;
	
	public static final String TYPE_EMAIL="email";
	public static final String TYPE_MOBILE="mobile";
	public static final String TYPE_PASSWORD="psssword";
	
	
	private Long memberId;
	private String type;// 激活类型 mobile:手机 email:邮箱， password:忘记密码
	private String value;// 邮箱地址或手机号
	private String code;// 激活码
	private Date createTime;// 激活码创建时间
	private Date activeTime;// 是靠这个激活码激活的就置上该时间
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
}
