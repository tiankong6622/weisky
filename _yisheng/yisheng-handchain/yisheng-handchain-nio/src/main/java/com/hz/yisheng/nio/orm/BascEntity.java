package com.hz.yisheng.nio.orm;

import java.io.Serializable;

import com.google.code.morphia.annotations.Id;

/**
 * 数据包的基本信息
 * 注：数据包中的数据是16进制
 * 
 * @author WeiSky
 *
 */
public class BascEntity implements Serializable{

	private static final long serialVersionUID = 2459945460632704685L;

	@Id
	private long id;
	
	/**
	 * 创建时间的毫秒long类型
	 */
	private long ct;
	
	private String cmd;//数据包中的功能代码
	private String addr;//设备地址
	private int dataLength;//数据长度
	private String checkCode;//校验码
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCt() {
		return ct;
	}
	public void setCt(long ct) {
		this.ct = ct;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getDataLength() {
		return dataLength;
	}
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
}
