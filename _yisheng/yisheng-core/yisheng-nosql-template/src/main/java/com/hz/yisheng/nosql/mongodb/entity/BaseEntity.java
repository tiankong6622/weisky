package com.hz.yisheng.nosql.mongodb.entity;

import java.io.Serializable;

/**
 * mongodb实体相关的父类
 * @author WeiSky
 *
 */
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = -8411839231735632149L;

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
	
	
}
