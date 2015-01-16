package com.hz.yisheng.commondata;

import org.javafans.common.utils.random.RandomUtils;

public class CommonDataConstants {

	public static final String TMP_SIGN=RandomUtils.getRandomStr(8);
	
	public interface Map{
		//--默认经纬度 北京的
		public static final String SHOP_BEIJING_LNG = "116.395645"; //经度
		public static final String SHOP_BEIJING_LAT = "39.929986"; //纬度
	}
}
