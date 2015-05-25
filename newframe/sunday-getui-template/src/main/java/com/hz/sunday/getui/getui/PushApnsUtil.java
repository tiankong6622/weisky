package com.hz.sunday.getui.getui;

import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * IOS推送方式接口
 * 
 * @author huanglei
 * 
 */
public class PushApnsUtil {

	/**
	 * 单推
	 * 
	 * @param bb
	 */
	public static void apnSinglePush(BaseBean bb) {
		IGtPush p = new IGtPush(bb.getHost(), bb.getAppKey(),
				bb.getMasterSecret());
		APNTemplate template = new APNTemplate();
		// IOS消息模板
		template.setPushInfo(bb.getActionLocKey(), bb.getBadge(),
				bb.getMessage(), bb.getSound());

		SingleMessage SingleMessage = new SingleMessage();
		SingleMessage.setData(template);
		// 单推
		IPushResult ret = p.pushAPNMessageToSingle(bb.getAppId(),
				bb.getDeviceToken(), SingleMessage);
//		System.out.println(ret.getResponse());
	}

	/**
	 * 群推
	 * @param bb
	 * @param deviceList IOS设备唯一识别号
	 */
	public static void apnAllPush(BaseBean bb,List<String> deviceList) {
		IGtPush p = new IGtPush(bb.getHost(), bb.getAppKey(),
				bb.getMasterSecret());
		APNTemplate template = new APNTemplate();
		// IOS消息模板
		template.setPushInfo(bb.getActionLocKey(), bb.getBadge(),
				bb.getMessage(), bb.getSound());

		SingleMessage SingleMessage = new SingleMessage();
		SingleMessage.setData(template);

		ListMessage listMessage = new ListMessage();
		listMessage.setData(template);
		String contentId = p.getAPNContentId(bb.getAppId(), listMessage);

		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		// 群推
		IPushResult ret = p.pushAPNMessageToList(bb.getAppId(), contentId, deviceList);
//		System.out.println(ret.getResponse());
	}
}
