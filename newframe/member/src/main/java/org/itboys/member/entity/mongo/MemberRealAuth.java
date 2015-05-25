package org.itboys.member.entity.mongo;

import java.io.Serializable;
import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;
/**
 * 会员真实信息认证信息 也可以当做会员的一些基本真实信息
 * @author huml
 *
 */
@Entity(value = "memberRealAuto", noClassnameStored = true)
public class MemberRealAuth extends MemberBaseEntity {

	private static final long serialVersionUID = 4142323460700548011L;
	private Long memberId;//会员ID
	private String card;//证件号码
	private String cardImg;//证件号码图片
	private Integer cardType;//证件类型
	private String realName;//真实姓名
	private Integer realAuthPass;//是否全部认证通过 1:实名认证通过 0:不通过
	private String alipayNo;//支付宝账号
	private String tencentNo;//财付通账号
	private Date authPassTime;//全部认证通过时间
	private String mobile;//手机号
	private String email;//真实邮箱
	private Integer emailAuth=0;//1邮件认证通过 0:待认证 2:不通过
	private Date emailAuthTime;//1邮件认证通过 0:待认证 2:不通过
	private Integer mobileAuth=0;//1手机认证通过 0:待认证 2:不通过
	private Date mobileAuthTime;//1手机认证通过 0:待认证 2:不通过
	
	private String lng;//经度
	private String lat;//纬度
	
	private Integer moduletype;//信息模板类型 1,2.... 默认为1
	
	public Integer getModuletype() {
		return moduletype;
	}

	public void setModuletype(Integer moduletype) {
		this.moduletype = moduletype;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCardImg() {
		return cardImg;
	}

	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getRealAuthPass() {
		return realAuthPass;
	}

	public void setRealAuthPass(Integer realAuthPass) {
		this.realAuthPass = realAuthPass;
	}

	public String getAlipayNo() {
		return alipayNo;
	}

	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}

	public String getTencentNo() {
		return tencentNo;
	}

	public void setTencentNo(String tencentNo) {
		this.tencentNo = tencentNo;
	}

	public Date getAuthPassTime() {
		return authPassTime;
	}

	public void setAuthPassTime(Date authPassTime) {
		this.authPassTime = authPassTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailAuth() {
		return emailAuth;
	}

	public void setEmailAuth(Integer emailAuth) {
		this.emailAuth = emailAuth;
	}

	public Date getEmailAuthTime() {
		return emailAuthTime;
	}

	public void setEmailAuthTime(Date emailAuthTime) {
		this.emailAuthTime = emailAuthTime;
	}

	public Integer getMobileAuth() {
		return mobileAuth;
	}

	public void setMobileAuth(Integer mobileAuth) {
		this.mobileAuth = mobileAuth;
	}

	public Date getMobileAuthTime() {
		return mobileAuthTime;
	}

	public void setMobileAuthTime(Date mobileAuthTime) {
		this.mobileAuthTime = mobileAuthTime;
	}

}
