package com.hz.sunday.getui;

import junit.framework.TestCase;

import org.junit.Test;

import com.hz.sunday.getui.constant.TemplateType;
import com.hz.sunday.getui.getui.PushMsgToAppUtil;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 基于单个应用群推消息
 * 
 * @author huanglei
 * 
 */
public class PushMsgToAppUtilTest extends TestCase {

	public static String APP_ID = "IKerZXhvhu7rlF8L6nu6i"; // 设定接收的应用
	public static String APP_KEY = "YBF4V08Uwc6LeFkTjS0X63"; // 用于鉴定身份是否合法
	public static String MASTER_SECRET = "GwqXzW5M7u9DXtjcgwJGH8"; // 第三方客户端个推集成鉴权码，用于验证第三方合法性。在客户端集成SDK时需要提供
	public static String HOST = "http://sdk.open.api.igexin.com/apiex.htm"; // 接口地址

	/**
	 * 透传消息
	 */
	@Test
	public void testTransmissionTemplate() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setType(1);
			bb.setContent("勿以善小而不为，勿以恶小而为之...");
			// bb.setPhoneType("MI2S");
			bb.setProvince("浙江省");
			// bb.setTag("开心");

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("您好，这是一个透传测试信息，收到了，可以忽略...");
			// 当设置的sound内容为com.gexin.ios.silence时，发给APPLE的消息则会静音
			bb.setSound("com.gexin.ios.silence");

			PushMsgToAppUtil.sendMsgToApp(TemplateType.TRANSMISSION_TEMPLATE,
					bb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点击通知打开链接
	 */
	@Test
	public void testLinkTemplate() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setTitle("好人好事");
			bb.setText("张三、李四都是好孩子...");
			bb.setLogo("");
			bb.setLogoUrl("");
			bb.setRing(true);
			bb.setVibrate(true);
			bb.setClearable(true);
			bb.setUrl("http://www.baidu.com");
			// bb.setPhoneType("MI2S");
			bb.setProvince("浙江省");
			// bb.setTag("开心");

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("您好，这是一个测试信息，收到了，可以忽略...");
			// 当设置的sound内容为com.gexin.ios.silence时，发给APPLE的消息则会静音
			bb.setSound("com.gexin.ios.silence");

			PushMsgToAppUtil.sendMsgToApp(TemplateType.LINK_TEMPLATE, bb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点击通知打开应用模板
	 */
	@Test
	public void testNotificationTemplate() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setTitle("杭州市西湖区");
			bb.setText("得力西大厦10楼");
			bb.setLogo("");
			bb.setLogoUrl("");
			bb.setRing(true);
			bb.setVibrate(true);
			bb.setClearable(true);
			bb.setType(1);
			bb.setContent("天行健，君子以自强不息，地势坤，君子以厚德载物...");
			// bb.setPhoneType("MI2S");
			bb.setProvince("浙江省");
			// bb.setTag("开心");

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("您好，这是一个测试信息，收到了，可以忽略...");
			// 当设置的sound内容为com.gexin.ios.silence时，发给APPLE的消息则会静音
			bb.setSound("com.gexin.ios.silence");

			PushMsgToAppUtil.sendMsgToApp(TemplateType.NOTIFICATION_TEMPLATE,
					bb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
