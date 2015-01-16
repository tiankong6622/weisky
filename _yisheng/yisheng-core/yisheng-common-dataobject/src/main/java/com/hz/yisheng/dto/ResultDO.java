package com.hz.yisheng.dto;

import java.io.Serializable;

/**
 * @author ChenJunhui
 *
 * @param <T>
 */
public class ResultDO<T> implements Serializable{
	
	private static final long serialVersionUID = -1467576157657126613L;
	
	private boolean ok;
	private int code;
	private String message;
	private T result;
	
	
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
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	

	
}
