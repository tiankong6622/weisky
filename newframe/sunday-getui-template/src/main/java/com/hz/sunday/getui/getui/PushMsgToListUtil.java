package com.hz.sunday.getui.getui;

import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.hz.sunday.getui.constant.TemplateType;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 基于用户列表群推消息
 * 描述：
 * 	群推消息大致可分为基于list推送和基于用户信息检索推送。基于list推送是指上传ClientID列表，对列表中所有ClientID进行消息推送。
 * 	基于用户信息检索推送是指开发者向符合特定条件的若干用户的集合进行消息推送。
 * 解决思路： 
 * 	1、基于list推送
 * 		个推推送通过ClientID列表的方式实现对某些特定用户进行消息推送。例如，对于抽奖活动的应用，需要对已知的某些用户推送中奖消息，就可以通过ClientID列表方式推送消息。 
 * 	2、基于用户信息检索推送
 * 		个推推送通过省份、平台、Tag（标签）这几种技术方式来实现用户分组的功能。服务端通过客户端addphoneinfo汇报的信息获取ClientID对应的省份和平台信息。
 * 
 * @author huanglei
 *
 */
public class PushMsgToListUtil {

	/**
	 * 基于用户列表群推消息
	 * 
	 * @param type 模板类型（1：透传消息模板；2：点击通知打开链接模板；3：点击通知打开应用模板；4：点击通知栏弹框下载模板；5：直接显示弹出框模板）
	 * @param bb 发送信息实体类
	 * @param targets 目标集合
	 * @throws Exception
	 */
	public static void sendMsgToList(Integer type, BaseBean bb, List<Target> targets)
			throws Exception {
		// 配置返回每个用户返回用户状态
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");

		final IGtPush push = new IGtPush(bb.getHost(), bb.getAppKey(),
				bb.getMasterSecret());
		// 建立连接，开始鉴权
		push.connect();

		ListMessage message = new ListMessage();
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
		} else {
			TransmissionTemplate template = transmissionTemplate(bb);
			message.setData(template);
		}

		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(0 * 1000 * 3600);
		// 获取taskID
		String taskId = push.getContentId(message);
		// 使用taskID对目标进行推送
		IPushResult ret = push.pushMessageToList(taskId, targets);
		// 打印服务器返回信息
		System.out.println(ret.getResponse().toString());
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
		/*template.setPushInfo(bb.getActionLocKey(), bb.getBadge(),
				bb.getMessage(), bb.getSound());*/
		 template.setPushInfo(bb.getActionLocKey(), bb.getBadge(), bb.getMessage(), "default", bb.getMessage(), "", "", "");
		return template;
	}

	/**
	 * 点击通知打开网页模板 描述：开发者向用户推送通知，通知以状态栏形式展示，并且点击通知后可打开指定的URL地址。
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
	 * 点击通知打开应用模板 描述：开发者向用户推送消息，消息以状态栏通知的形式传递给客户端，点击通知后可对透传消息内容进行相关处理。
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
}