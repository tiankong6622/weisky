package com.hz.sunday.getui.getui;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.PopupTransmissionTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.hz.sunday.getui.constant.TemplateType;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 对指定单个用户推送消息
 * 	 描述：开发者向某一个特定的用户推送消息。 注：个推使用ClientID来标识每个独立的用户
 * 解决思路：
 * 个推使用ClientID来表示用户，因此在这种需求下，开发者需要通过服务端API（pushToSingle）或者使用管理平台中上传特定用户列表的方式，指定某一个特定的ClientID来推送消息。
 * 
 * @author huanglei
 *
 */
public class PushMsgToSingleUtil {

	/**
	 * 对指定单个用户推送消息
	 * @param type 模板类型（1：透传消息模板；2：点击通知打开链接模板；3：点击通知打开应用模板；4：点击通知栏弹框下载模板；5：直接显示弹出框模板）
	 * @param bb 发送信息实体类
	 * @throws Exception
	 */
	public static void sendMsgToSingle(Integer type, BaseBean bb) throws Exception {
		IGtPush push = new IGtPush(bb.getHost(), bb.getAppKey(),
				bb.getMasterSecret());
		push.connect();

		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(72 * 3600 * 1000);

		if (type == TemplateType.TRANSMISSION_TEMPLATE) {
			TransmissionTemplate template = transmissionTemplate(bb);
			message.setData(template);
		} else if (type == TemplateType.LINK_TEMPLATE) {
			LinkTemplate template = linkTemplate(bb);
			message.setData(template);
		} else if (type == TemplateType.NOTIFICATION_TEMPLATE) {
			NotificationTemplate template = notificationTemplate(bb);
			message.setData(template);
		} else if (type == TemplateType.NOTYPOPLOAD_TEMPLATE) {
			NotyPopLoadTemplate template = notyPopLoadTemplate(bb);
			message.setData(template);
		} else if (type == TemplateType.POPUP_TRANSMISSION_TEMPLATE) {
			PopupTransmissionTemplate template = popupTransmissionTemplate(bb);
			message.setData(template);
		} else {
			TransmissionTemplate template = transmissionTemplate(bb);
			message.setData(template);
		}

		Target target = new Target();
		target.setAppId(bb.getAppId());
		target.setClientId(bb.getCid());

		IPushResult ret = push.pushMessageToSingle(message, target);
//		System.out.println(ret.getResponse().toString());
	}

	/**
	 * 透传消息。 描述：开发者向用户推送消息，消息以透传的形式传递给客户端，无显示。
	 * 
	 * @param bb
	 * @return
	 * @throws Exception
	 */
	private static TransmissionTemplate transmissionTemplate(BaseBean bb)
			throws Exception {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(bb.getAppId());
		template.setAppkey(bb.getAppKey());
		template.setTransmissionType(bb.getType());
		template.setTransmissionContent(bb.getContent());
		// IOS消息模板
		template.setPushInfo(bb.getActionLocKey(), bb.getBadge(),
				bb.getMessage(), bb.getSound());

		return template;
	}

	/**
	 * 点击通知打开链接 描述：开发者向用户推送通知，通知以状态栏形式展示，并且点击通知后可打开指定的URL地址。
	 * 
	 * @param bb
	 * @return
	 * @throws Exception
	 */
	private static LinkTemplate linkTemplate(BaseBean bb) throws Exception {
		LinkTemplate template = new LinkTemplate();
		template.setAppId(bb.getAppId());
		template.setAppkey(bb.getAppKey());
		template.setTitle(bb.getTitle());
		template.setText(bb.getText());
		template.setLogo(bb.getLogo());
		template.setLogoUrl(bb.getLogoUrl());
		template.setIsRing(bb.isRing());
		template.setIsVibrate(bb.isVibrate());
		template.setIsClearable(bb.isClearable());
		template.setUrl(bb.getUrl());
		// IOS消息模板
		template.setPushInfo(bb.getActionLocKey(), bb.getBadge(),
				bb.getMessage(), bb.getSound());

		return template;
	}

	/**
	 * 点击通知打开应用 描述：开发者向用户推送消息，消息以状态栏通知的形式传递给客户端，点击通知后可对透传消息内容进行相关处理。
	 * 
	 * @param bb
	 * @return
	 * @throws Exception
	 */
	private static NotificationTemplate notificationTemplate(BaseBean bb)
			throws Exception {
		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(bb.getAppId());
		template.setAppkey(bb.getAppKey());
		template.setTitle(bb.getTitle());
		template.setText(bb.getText());
		template.setLogo(bb.getLogo());
		template.setLogoUrl(bb.getLogoUrl());
		template.setIsRing(bb.isRing());
		template.setIsVibrate(bb.isVibrate());
		template.setIsClearable(bb.isClearable());
		template.setTransmissionType(bb.getType());
		template.setTransmissionContent(bb.getContent());
		// IOS消息模板
		template.setPushInfo(bb.getActionLocKey(), bb.getBadge(),
				bb.getMessage(), bb.getSound());

		return template;
	}

	/**
	 * 点击通知栏弹框下载模板
	 * 描述：在通知栏显示一条含图标、标题等的通知，用户点击后弹出框，用户可以选择直接下载应用或者取消下载应用。（IOS不支持该模板）
	 * 
	 * @param bb
	 * @return
	 */
	private static NotyPopLoadTemplate notyPopLoadTemplate(BaseBean bb) {
		NotyPopLoadTemplate template = new NotyPopLoadTemplate();

		template.setAppId(bb.getAppId());
		template.setAppkey(bb.getAppKey());

		template.setNotyTitle(bb.getNotyTitle());
		template.setNotyContent(bb.getNotyContent());
		template.setNotyIcon(bb.getNotyIcon());

		template.setBelled(bb.isRing());
		template.setVibrationed(bb.isVibrate());
		template.setCleared(bb.isClearable());

		template.setPopTitle(bb.getPopTitle());
		template.setPopContent(bb.getPopContent());
		template.setPopImage(bb.getPopImage());
		template.setPopButton1(bb.getPopBtn1());
		template.setPopButton2(bb.getPopBtn2());

		template.setLoadTitle(bb.getLoadTitle());
		template.setLoadIcon(bb.getLoadIcon());
		template.setLoadUrl(bb.getLoadUrl());

		return template;
	}

	/**
	 * 直接显示弹出框
	 * 
	 * @param bb
	 * @return
	 */
	private static PopupTransmissionTemplate popupTransmissionTemplate(
			BaseBean bb) {
		PopupTransmissionTemplate template = new PopupTransmissionTemplate();
		template.setAppId(bb.getAppId());
		template.setAppkey(bb.getAppKey());
		template.setTitle(bb.getTitle());
		template.setText(bb.getText());
		template.setImg(bb.getImg());
		template.setConfirmButtonText(bb.getPopBtn1());
		template.setCancelButtonText(bb.getPopBtn2());
		template.setTransmissionType(bb.getType());
		template.setTransmissionContent(bb.getContent());

		return template;
	}
}