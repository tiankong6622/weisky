package com.hz.sunday.getui.getui;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.hz.sunday.getui.constant.TemplateType;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 基于单个应用群推消息 
 * 	描述：开发者向某一个特定的应用推送消息。 注：个推使用AppID来标识每个独立的应用。
 * 	解决思路：在这种需求下，开发者需要通过服务端API（pushToApp）或者使用管理平台中对某一应用的所有用户进行推送。
 * 
 * @author huanglei
 *
 */
public class PushMsgToAppUtil {

	/**
	 * 基于单个应用群推消息
	 * 
	 * @param 模板类型（1：透传消息模板；2：点击通知打开链接模板；3：点击通知打开应用模板；4：点击通知栏弹框下载模板；5：直接显示弹出框模板）
	 * @param bb 发送信息实体类
	 * @throws Exception
	 */
	public static void sendMsgToApp(Integer type, BaseBean bb) throws Exception {
		IGtPush push = new IGtPush(bb.getHost(), bb.getAppKey(),
				bb.getMasterSecret());
		// 建立连接，开始鉴权
		push.connect();

		AppMessage message = new AppMessage();
		if (type == TemplateType.TRANSMISSION_TEMPLATE) {// 透传消息
			TransmissionTemplate template = transmissionTemplate(bb);
			message.setData(template);
		} else if (type == TemplateType.LINK_TEMPLATE) {// 点击通知打开链接
			LinkTemplate template = linkTemplate(bb);
			message.setData(template);
		} else if (type == TemplateType.NOTIFICATION_TEMPLATE) {// 点击通知打开应用
			NotificationTemplate template = notificationTemplate(bb);
			message.setData(template);
		} else {
			TransmissionTemplate template = transmissionTemplate(bb);
			message.setData(template);
		}

		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(1 * 1000 * 3600);

		// 设置推送目标条件过滤
		List<String> appIdList = new ArrayList<String>();
		List<String> phoneTypeList = new ArrayList<String>();
		List<String> provinceList = new ArrayList<String>();
		List<String> tagList = new ArrayList<String>();

		// 设置应用
		appIdList.add(bb.getAppId());
		message.setAppIdList(appIdList);
		// 设置机型
		if (null != bb.getPhoneType() && bb.getPhoneType().trim().length() > 0) {
			phoneTypeList.add(bb.getPhoneType());
			message.setPhoneTypeList(phoneTypeList);
		}
		// 设置省份
		if (null != bb.getProvince() && bb.getProvince().trim().length() > 0) {
			provinceList.add(bb.getProvince());
			message.setProvinceList(provinceList);
		}
		// 设置标签内容
		if (null != bb.getTag() && bb.getTag().trim().length() > 0) {
			tagList.add(bb.getTag());
			message.setTagList(tagList);
		}

		IPushResult ret = push.pushMessageToApp(message);
//		System.out.println(ret.getResponse().toString());
	}

	/**
	 * 透传消息 
	 * 	描述：开发者向用户推送消息，消息以透传的形式传递给客户端，无显示。
	 * 
	 * @param bb
	 * @return
	 * @throws Exception
	 */
	public static TransmissionTemplate transmissionTemplate(BaseBean bb)
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
	 * 点击通知打开链接 
	 * 	描述：开发者向用户推送通知，通知以状态栏形式展示，并且点击通知后可打开指定的URL地址。
	 * 
	 * @param bb
	 * @return
	 * @throws Exception
	 */
	public static LinkTemplate linkTemplate(BaseBean bb) throws Exception {
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
	 * 点击通知打开应用模板
	 *  描述：开发者向用户推送消息，消息以状态栏通知的形式传递给客户端，点击通知后可对透传消息内容进行相关处理。
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

}