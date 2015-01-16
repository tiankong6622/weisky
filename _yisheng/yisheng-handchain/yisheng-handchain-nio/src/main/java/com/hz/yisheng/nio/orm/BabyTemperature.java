package com.hz.yisheng.nio.orm;
/**
 * 婴儿手环体温
 * @author WeiSky
 *
 */
public class BabyTemperature extends BascDetail{

	private static final long serialVersionUID = 8705030136638191680L;
	
	public String temper;//体温值

	public String getTemper() {
		return temper;
	}

	public void setTemper(String temper) {
		this.temper = temper;
	}
	
}
