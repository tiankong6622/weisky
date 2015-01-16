package com.hz.yisheng.handchain.dto;

import java.util.List;

/**
 * 返回值(带列表)
 * 
 * @author dengnice
 * @param <T>
 *
 */
public class ResultMessageList<T> {

	/** 结果标识 */
	private String result = "1";
	/** 信息 */
	private String message = "OK";
	/** 统计记录总数 */
	private int total = 0;
	/** 返回的信息 */
	private List<T> data;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
}
