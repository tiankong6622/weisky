package com.hz.crf.model.orm;

import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;
/**
 * 手机验证码
 * 
 * @author weisky
 *
 * May 28, 2015
 */
@Entity(value = "MobiCode", noClassnameStored = true)
public class MobiCode extends BaseLongIdEntity{

	private static final long serialVersionUID = 2047256764306101183L;
	
	private String mobi;//手机号
	private int type = 1;//验证码类型  1：注册验证  2：密码找回验证  3：修改密码验证
	private String code;//验证码
	
	public String getMobi() {
		return mobi;
	}
	public void setMobi(String mobi) {
		this.mobi = mobi;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
