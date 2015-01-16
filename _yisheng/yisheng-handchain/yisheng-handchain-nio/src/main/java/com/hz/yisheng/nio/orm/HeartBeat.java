package com.hz.yisheng.nio.orm;
/**
 * 心跳检测信息
 * 
 * @author WeiSky
 *
 */
public class HeartBeat extends BascEntity{

	private static final long serialVersionUID = 6144939297740071022L;
	
	private String load;//系统负荷
	
	public String getLoad() {
		return load;
	}

	public void setLoad(String load) {
		this.load = load;
	}

}
