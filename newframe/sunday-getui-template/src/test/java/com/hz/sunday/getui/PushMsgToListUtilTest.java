package com.hz.sunday.getui;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.gexin.rp.sdk.base.impl.Target;
import com.hz.sunday.getui.constant.TemplateType;
import com.hz.sunday.getui.getui.PushMsgToListUtil;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 基于用户列表群推消息
 * 
 * @author huanglei
 * 
 */
public class PushMsgToListUtilTest extends TestCase {

	public static String APP_ID = "IKerZXhvhu7rlF8L6nu6i"; // 设定接收的应用
	public static String APP_KEY = "YBF4V08Uwc6LeFkTjS0X63"; // 用于鉴定身份是否合法
	public static String MASTER_SECRET = "GwqXzW5M7u9DXtjcgwJGH8"; // 第三方客户端个推集成鉴权码，用于验证第三方合法性。在客户端集成SDK时需要提供
	public static String CID = "20becd13a12907d441a9152e1b377b67";// 用户标识
	public static String CID1 = "4800d5a5da7931909147f944a0ccfcc6";// 用户标识
	public static String CID2 = "fc5d4e96954c39911263df716ebd0bb1";// 用户标识
	public static String CID3 = "47b51832d5a247a62ac9b992acb83505";// 用户标识
	public static String HOST = "http://sdk.open.api.igexin.com/apiex.htm"; // 接口地址

	/**
	 * 透传消息
	 */
	@Test
	public void testTransmissionTemplate() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setType(1);
			bb.setContent("我们还是好朋友，这是真的吗？");

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("您好，这是一个透传测试信息，收到了，可以忽略...");
			// 当设置的sound内容为com.gexin.ios.silence时，发给APPLE的消息则会静音
			bb.setSound("com.gexin.ios.silence");

			// 配置推送目标
			List<Target> targets = new ArrayList<Target>();
			Target target = new Target();
			target.setAppId(APP_ID);
			target.setClientId(CID);
			targets.add(target);

			Target target1 = new Target();
			target1.setAppId(APP_ID);
			target1.setClientId(CID1);
			targets.add(target1);

			Target target2 = new Target();
			target2.setAppId(APP_ID);
			target2.setClientId(CID2);
			targets.add(target2);

			Target target3 = new Target();
			target3.setAppId(APP_ID);
			target3.setClientId(CID3);
			targets.add(target3);

			PushMsgToListUtil.sendMsgToList(TemplateType.TRANSMISSION_TEMPLATE,
					bb, targets);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点击通知打开网页模板
	 */
	@Test
	public void testLinkTemplate() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setTitle("百度标题");
			bb.setText("百度一下，就知道了...");
			bb.setLogo("icon.png");
			bb.setLogoUrl("");
			bb.setRing(true);
			bb.setVibrate(true);
			bb.setClearable(true);
			bb.setUrl("http://www.hao123.com");

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("您好，这是一个测试信息，收到了，可以忽略...");
			bb.setSound("com.gexin.ios.silence");

			// 配置推送目标
			List<Target> targets = new ArrayList<Target>();
			Target target = new Target();
			target.setAppId(APP_ID);
			target.setClientId(CID);
			targets.add(target);

			Target target1 = new Target();
			target1.setAppId(APP_ID);
			target1.setClientId(CID1);
			targets.add(target1);

			PushMsgToListUtil.sendMsgToList(TemplateType.LINK_TEMPLATE, bb,
					targets);

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

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("您好，这是一个测试信息，收到了，可以忽略...");
			bb.setSound("com.gexin.ios.silence");

			// 配置推送目标
			List<Target> targets = new ArrayList<Target>();
			Target target = new Target();
			target.setAppId(APP_ID);
			target.setClientId(CID);
			targets.add(target);

			Target target1 = new Target();
			target1.setAppId(APP_ID);
			target1.setClientId(CID1);
			targets.add(target1);

			PushMsgToListUtil.sendMsgToList(TemplateType.NOTIFICATION_TEMPLATE,
					bb, targets);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点击通知栏弹框下载模板
	 */
	@Test
	public void testNotyPopLoadTemplate() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setNotyTitle("新短消息");
			bb.setNotyContent("您有新的下载，请注意查收...");
			bb.setNotyIcon("icon.png");
			bb.setRing(true);
			bb.setVibrate(true);
			bb.setClearable(true);
			bb.setPopTitle("下载");
			bb.setPopContent("个推安装包...");
			bb.setPopImage("http://www-igexin.qiniudn.com/wp-content/uploads/2013/08/logo_getui1.png");
			bb.setPopBtn1("下载");
			bb.setPopBtn2("取消");
			bb.setLoadTitle("popTitle");
			bb.setLoadIcon("file://icon.png");
			bb.setLoadUrl("http://wap.igexin.com/android_download/Gexin_android_2.0.apk");

			// 配置推送目标
			List<Target> targets = new ArrayList<Target>();
			Target target = new Target();
			target.setAppId(APP_ID);
			target.setClientId(CID);
			targets.add(target);

			Target target1 = new Target();
			target1.setAppId(APP_ID);
			target1.setClientId(CID1);
			targets.add(target1);

			PushMsgToListUtil.sendMsgToList(TemplateType.NOTYPOPLOAD_TEMPLATE,
					bb, targets);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
