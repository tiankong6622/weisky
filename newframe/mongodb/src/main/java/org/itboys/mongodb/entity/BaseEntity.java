package org.itboys.mongodb.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * mongodb实体相关的父类
 * @author 俊哥
 *
 */
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = -7856313888252598212L;

	/**
	 * 创建时间的毫秒long类型
	 */
	private long ct;
	
	/**
	 * 修改时间的毫秒类型
	 */
	private long ut;

	public long getCt() {
		return ct;
	}

	public void setCt(long ct) {
		this.ct = ct;
	}

	public long getUt() {
		return ut;
	}

	public void setUt(long ut) {
		this.ut = ut;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
