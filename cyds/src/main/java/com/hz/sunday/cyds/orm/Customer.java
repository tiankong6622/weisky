package com.hz.sunday.cyds.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 客户基本信息
 * @author WeiSky
 *
 */
public class Customer extends BaseAdminEntity{

	private static final long serialVersionUID = 2200903998231027713L;
	
	private String mobile;//手机号
	private String passw;//密码
	private String email;//邮箱
	private String ctype;//客户类型  1：普通参赛客户  2：导师
	private String imgPath;//头像地址
	private String comment;//简介或者寄语
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassw() {
		return passw;
	}
	public void setPassw(String passw) {
		this.passw = passw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
