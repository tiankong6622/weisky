package com.hz.yisheng.nio.orm;
/**
 * 低电压报警
 * @author WeiSky
 *
 */
public class LowPower extends BascDetail{

	private static final long serialVersionUID = 6039867067637850182L;
	
	private String alarmData;//报警数据

	public String getAlarmData() {
		return alarmData;
	}

	public void setAlarmData(String alarmData) {
		this.alarmData = alarmData;
	}
	
}
