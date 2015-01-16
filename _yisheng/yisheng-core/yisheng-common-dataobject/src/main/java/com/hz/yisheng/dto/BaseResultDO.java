package com.hz.yisheng.dto;

import java.io.Serializable;

/**
 * 一些接口塞很多对象的用这个
 * @author ChenJunhui
 *
 */
public abstract class BaseResultDO implements Serializable{

	private static final long serialVersionUID = -5101127751015485603L;
	private boolean ok=true;
	private int code;
	private String message;
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
