package com.hz.yisheng.handchain.dto;
/**
 * 返回值
 * 
 * @author WeiSky
 * @param <T>
 *
 */
public class ResultMessage<T> {

	/**
	 * 结果标识
	 */
	private boolean flag = true;
	
	/**
	 * 信息
	 */
	private String msg = "OK";
	
	/**
	 * 返回的信息
	 */
	private Object data;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
