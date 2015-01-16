package com.hz.yisheng.nio.orm;

/**
 * 手环脱落报警信息
 * 
 * @author WeiSky
 *
 */
public class DropAlarm extends BascDetail{

	private static final long serialVersionUID = -5457313260039896600L;
	
	public String alarmData;//报警数据

	public String getAlarmData() {
		return alarmData;
	}

	public void setAlarmData(String alarmData) {
		this.alarmData = alarmData;
	}
	
}
