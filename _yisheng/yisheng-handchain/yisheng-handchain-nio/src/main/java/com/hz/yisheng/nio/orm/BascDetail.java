package com.hz.yisheng.nio.orm;
/**
 * 数据包的基本信息
 * @author WeiSky
 *
 */
public class BascDetail extends BascEntity{

	private static final long serialVersionUID = 1014618929103733231L;

	private String seque;//消息序号
	
	private int len;//数据区长度
	
	private String tId;//婴儿手环ID
	
	private String mesType;//婴儿手环信号类型

	public String getSeque() {
		return seque;
	}

	public void setSeque(String seque) {
		this.seque = seque;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
}
