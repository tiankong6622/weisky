package com.hz.yisheng.nio.conf;
/**
 * netty通讯的常量
 * 
 * @author WeiSky
 *
 */
public class NettyRFIDConstant {

	
	public static final String CMD_HB = ">HB";//功能代码，心跳检测命令
	public static final String CMD_DA = ">DA";//功能代码,终端数据传输命令
	
	public static final String HANDCHAIN_HEART_BEAT = "A0";//婴儿手环心跳
	public static final String HANDCHAIN_POSITION = "A1"; //婴儿手环位置
	public static final String HANDCHAIN_TEMPER = "A2";//婴儿手环体温
	public static final String HANDCHAIN_DROP_ALARM = "A3";//剪断或脱落报警
	public static final String HANDCHAIN_LOW_POWER_ALARM = "A4";//低电压报警数据
	
	public static final String LOCATION = "10";//定位
	public static final String PAIRING = "20";//配对
	public static final String BINDING = "30";//绑定 
	
	public static final String ALARM_TYPE = "01";//报警数据类型（皮肤感应脱落、低电压）
	
	public static final String MongoDB = "handchain";//mongodb数据库名
	
	/*
	 * 服务端IP地址
	 */
	private String nioIp;
	
	/*
	 * 服务端监听的端口号
	 */
	private int nioPort;
	
	public String getNioIp() {
		return nioIp;
	}

	public void setNioIp(String nioIp) {
		this.nioIp = nioIp;
	}

	public int getNioPort() {
		return nioPort;
	}

	public void setNioPort(int nioPort) {
		this.nioPort = nioPort;
	}

}
