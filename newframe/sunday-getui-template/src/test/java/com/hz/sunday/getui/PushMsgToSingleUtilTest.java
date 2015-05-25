package com.hz.sunday.getui;

import junit.framework.TestCase;

import org.junit.Test;

import com.hz.sunday.getui.constant.TemplateType;
import com.hz.sunday.getui.getui.PushMsgToSingleUtil;
import com.hz.sunday.getui.pojo.BaseBean;
import com.hz.sunday.getui.utils.SendMessageUtil;

/**
 * 对指定单个用户推送消息
 * 
 * @author huanglei
 * 
 */
public class PushMsgToSingleUtilTest extends TestCase {

	public static String APP_ID = "IKerZXhvhu7rlF8L6nu6i"; // 设定接收的应用
	public static String APP_KEY = "YBF4V08Uwc6LeFkTjS0X63"; // 用于鉴定身份是否合法
	public static String MASTER_SECRET = "GwqXzW5M7u9DXtjcgwJGH8"; // 第三方客户端个推集成鉴权码，用于验证第三方合法性。在客户端集成SDK时需要提供
	public static String CID = "20becd13a12907d441a9152e1b377b67"; // 用户标识
	public static String HOST = "http://sdk.open.api.igexin.com/apiex.htm"; // 接口地址

	/**
	 * 透传消息
	 */
	@Test
	public void testTransmissionTemplate() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setCid(CID);
			bb.setType(1);
			bb.setContent("您好，这是一个透传测试信息，收到了，可以忽略...");

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("您好，这是一个透传测试信息，收到了，可以忽略...");
			bb.setSound("com.gexin.ios.silence");

			PushMsgToSingleUtil.sendMsgToSingle(
					TemplateType.TRANSMISSION_TEMPLATE, bb);

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
			bb.setCid(CID);
			bb.setTitle("射雕侠侣");
			bb.setText("杨过、小龙女、郭襄、黄蓉...");
			bb.setLogo("");
			bb.setLogoUrl("");
			bb.setRing(true);
			bb.setVibrate(true);
			bb.setClearable(true);
			bb.setUrl("http://www.baidu.com");

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("您好，这是一个测试信息，收到了，可以忽略...");
			bb.setSound("com.gexin.ios.silence");

			PushMsgToSingleUtil.sendMsgToSingle(TemplateType.LINK_TEMPLATE, bb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点击通知打开应用
	 */
	@Test
	public void testNotificationTemplate() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setCid(CID);
			bb.setTitle("新购房消息");
			bb.setText("杭州房价大跌，你还在等什么...");
			bb.setLogo("");
			bb.setLogoUrl("");
			bb.setRing(true);
			bb.setVibrate(true);
			bb.setClearable(true);
			bb.setType(1);
			bb.setContent("百姓人家，购房首选...");

			bb.setActionLocKey("");
			bb.setBadge(2);
			bb.setMessage("您好，这是一个测试信息，收到了，可以忽略...");
			bb.setSound("com.gexin.ios.silence");

			PushMsgToSingleUtil.sendMsgToSingle(
					TemplateType.NOTIFICATION_TEMPLATE, bb);

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
			bb.setCid(CID);
			bb.setNotyTitle("魅力杭州");
			bb.setNotyContent("美好的生活，从身边的小事开始，从细心处发现...");
			bb.setNotyIcon("icon.png");
			bb.setRing(true);
			bb.setVibrate(true);
			bb.setClearable(true);
			bb.setPopTitle("我是弹出框标题");
			bb.setPopContent("我是弹出框内容：浙江省杭州市西湖区文三西路...");
			bb.setPopImage("http://www-igexin.qiniudn.com/wp-content/uploads/2013/08/logo_getui1.png");
			bb.setPopBtn1("打开");
			bb.setPopBtn2("取消");
			bb.setLoadTitle("我是下载标题");
			bb.setLoadIcon("file://icon.png");
			bb.setLoadUrl("http://wap.igexin.com/android_download/Gexin_android_2.0.apk");

			PushMsgToSingleUtil.sendMsgToSingle(
					TemplateType.NOTYPOPLOAD_TEMPLATE, bb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 直接显示弹出框
	 */
	@Test
	public void testPopupTransmissionTemplate() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setCid(CID);
			bb.setTitle("天下第一山庄");
			bb.setText("铸剑山庄");
			bb.setImg("");
			bb.setPopBtn1("确认");
			bb.setPopBtn2("取消");
			bb.setType(1);
			bb.setContent("我想拥有一把属于自己的宝剑...");

			PushMsgToSingleUtil.sendMsgToSingle(
					TemplateType.POPUP_TRANSMISSION_TEMPLATE, bb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOne(){
		BaseBean bb = new BaseBean("IKerZXhvhu7rlF8L6nu6i","YBF4V08Uwc6LeFkTjS0X63","GwqXzW5M7u9DXtjcgwJGH8","http://sdk.open.api.igexin.com/apiex.htm");
		bb.setCid("20becd13a12907d441a9152e1b377b67");
		bb.setTitle("E国寿");
		bb.setText("手机号为1861069805的用户成功兑换了综合意外险、保单号为pp281615162646、保费为30、扣除积分600");
		try {
			//PushMsgToListUtil.sendMsgToList(TemplateType.NOTIFICATION_TEMPLATE, bb, targets);
			SendMessageUtil.sendMsg(bb, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
