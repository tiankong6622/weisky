package com.hz.sunday.getui.entity;

import java.util.Date;

import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;
/**
 * token记录
 * @author ok
 *
 */
@Entity(value = "TokenRecord", noClassnameStored = true)
public class TokenRecord extends BaseLongIdEntity{

	private static final long serialVersionUID = 7017593838714528147L;
	
	private Long userId;//关联的用户id 必须
	private String token;//token 必须
	private String uutype;//终端类型  Android/IOS 必须
	private String type;//某些项目中分类推送的标记  如房贝贝中的A类  B类
	private Integer status=0;//该条记录是否已被冻结  0否  1是 
	
	private Date createTime;
	private Date updateTime;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUutype() {
		return uutype;
	}
	public void setUutype(String uutype) {
		this.uutype = uutype;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
