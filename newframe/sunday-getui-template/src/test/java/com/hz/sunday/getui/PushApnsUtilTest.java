package com.hz.sunday.getui;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.hz.sunday.getui.getui.PushApnsUtil;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * IOS推送方式接口
 * 
 * @author huanglei
 * 
 */
public class PushApnsUtilTest extends TestCase {

	public static String APP_ID = "IKerZXhvhu7rlF8L6nu6i"; // 设定接收的应用
	public static String APP_KEY = "YBF4V08Uwc6LeFkTjS0X63"; // 用于鉴定身份是否合法
	public static String MASTER_SECRET = "GwqXzW5M7u9DXtjcgwJGH8"; // 第三方客户端个推集成鉴权码，用于验证第三方合法性。在客户端集成SDK时需要提供
	public static String HOST = "http://sdk.open.api.igexin.com/apiex.htm"; // 接口地址
	// deviceToken length must be 64
	public static String DEVICE_TOKEN = "";// IOS设备唯一识别号
	public static String DEVICE_TOKEN1 = "";// IOS设备唯一标识号

	/**
	 * 单推
	 */
	@Test
	public void testApnSinglePush() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setDeviceToken(DEVICE_TOKEN);

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("这是一条个推测试短信，收到了请勿介意...");
			bb.setSound("com.gexin.ios.silence");

			PushApnsUtil.apnSinglePush(bb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 群推
	 */
	@Test
	public void testApnAllPush() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("这是一条个推测试短信，收到了请勿介意...");
			bb.setSound("com.gexin.ios.silence");

			List<String> deviceList = new ArrayList<String>();
			deviceList.add(DEVICE_TOKEN);
			deviceList.add(DEVICE_TOKEN1);

			PushApnsUtil.apnAllPush(bb, deviceList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
