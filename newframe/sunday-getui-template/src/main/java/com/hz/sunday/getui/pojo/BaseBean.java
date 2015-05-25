package com.hz.sunday.getui.pojo;

import java.io.Serializable;

/**
 * 
 * @author huanglei
 * 
 */
public class BaseBean implements Serializable {

	private static final long serialVersionUID = 7558522641999615625L;

	public BaseBean(String appId, String appKey, String masterSecret,
			String host) {
		super();
		this.appId = appId;
		this.appKey = appKey;
		this.masterSecret = masterSecret;
		this.host = host;
	}

	/** 设定接收的应用 */
	private String appId = "";
	/** 用于鉴定身份是否合法 */
	private String appKey = "";
	/** 第三方客户端个推集成鉴权码，用于验证第三方合法性。在客户端集成SDK时需要提供 */
	private String masterSecret = "";
	/** 用户标识 */
	private String cid;
	/** 接口地址 */
	private String host = "http://sdk.open.api.igexin.com/apiex.htm";
	/** IOS设备唯一识别号 */
	private String deviceToken = "";
	/** 收到消息是否立即启动应用（1：为立即启动；2：广播等待客户端自启动） */
	private int type = 1;
	/** 内容，不支持转义字符 */
	private String content = "";
	/** 通知标题 */
	private String title = "";
	/** 通知内容 */
	private String text = "";
	/** 通知的图标名称，包含后缀名（需要在客户端开发时嵌入），如“push.png” */
	private String logo = "";
	/** 通知图标URL地址 */
	private String logoUrl = "";
	/** 收到通知是否响铃：true响铃，false不响铃。默认响铃。 */
	private boolean ring = true;
	/** 收到通知是否振动：true振动，false不振动。默认振动。 */
	private boolean vibrate = true;
	/** 通知是否可清除：true可清除，false不可清除。默认可清除。 */
	private boolean clearable = true;
	/** 点击通知后打开的网页地址 */
	private String url = "";
	/** 通知栏标题 */
	private String notyTitle = "";
	/** 通知栏内容 */
	private String notyContent = "";
	/** 通知栏图标 */
	private String notyIcon = "";
	/** 弹出框标题 */
	private String popTitle = "";
	/** 弹出框内容 */
	private String popContent = "";
	/** 弹出框图标 */
	private String popImage = "";
	/** 弹出框左边按钮名称 */
	private String popBtn1 = "";
	/** 弹出框右边按钮名称 */
	private String popBtn2 = "";
	/** 下载标题 */
	private String loadTitle = "";
	/** 下载图标 */
	private String loadIcon = "";
	/** 下载地址 */
	private String loadUrl = "";
	/** 图片Url */
	private String img;
	/** 机型 */
	private String phoneType;
	/** 省份 */
	private String province;
	/** 标签 */
	private String tag;
	/** 任务唯一识别号 */
	private String taskId = "";

	/**
	 * IOS模板说明: setPushInfo有两个版本 一个用于简单的APNS通知： setPushInfo(actionKey,
	 * badge,message, sound) 一个用于完整功能的APNS通知： setPushInfo(actionLocKey,
	 * badge,message, sound, payload, locKey, locArgs, launchImage)
	 */
	/** （用于多语言支持）指定执行按钮所使用的Localizable.strings */
	private String actionLocKey = "";
	/** 应用icon上显示的数字 */
	private int badge = 1;
	/** 通知文本消息字符串 */
	private String message = "";
	/** 通知铃声文件名 */
	private String sound = "";
	/** 需要发送给客户端的透传数据 */
	private String payload = "";
	/** （用于多语言支持）指定Localizable.strings文件中相应的key */
	private String locKey = "";
	/** 如果loc-key中使用的占位符，则在loc-args中指定各参数 */
	private String locArgs = "";
	/** 指定启动界面图片名 */
	private String launchImage = "";

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public boolean isRing() {
		return ring;
	}

	public void setRing(boolean ring) {
		this.ring = ring;
	}

	public boolean isVibrate() {
		return vibrate;
	}

	public void setVibrate(boolean vibrate) {
		this.vibrate = vibrate;
	}

	public boolean isClearable() {
		return clearable;
	}

	public void setClearable(boolean clearable) {
		this.clearable = clearable;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNotyTitle() {
		return notyTitle;
	}

	public void setNotyTitle(String notyTitle) {
		this.notyTitle = notyTitle;
	}

	public String getNotyContent() {
		return notyContent;
	}

	public void setNotyContent(String notyContent) {
		this.notyContent = notyContent;
	}

	public String getNotyIcon() {
		return notyIcon;
	}

	public void setNotyIcon(String notyIcon) {
		this.notyIcon = notyIcon;
	}

	public String getPopTitle() {
		return popTitle;
	}

	public void setPopTitle(String popTitle) {
		this.popTitle = popTitle;
	}

	public String getPopContent() {
		return popContent;
	}

	public void setPopContent(String popContent) {
		this.popContent = popContent;
	}

	public String getPopImage() {
		return popImage;
	}

	public void setPopImage(String popImage) {
		this.popImage = popImage;
	}

	public String getPopBtn1() {
		return popBtn1;
	}

	public void setPopBtn1(String popBtn1) {
		this.popBtn1 = popBtn1;
	}

	public String getPopBtn2() {
		return popBtn2;
	}

	public void setPopBtn2(String popBtn2) {
		this.popBtn2 = popBtn2;
	}

	public String getLoadTitle() {
		return loadTitle;
	}

	public void setLoadTitle(String loadTitle) {
		this.loadTitle = loadTitle;
	}

	public String getLoadIcon() {
		return loadIcon;
	}

	public void setLoadIcon(String loadIcon) {
		this.loadIcon = loadIcon;
	}

	public String getLoadUrl() {
		return loadUrl;
	}

	public void setLoadUrl(String loadUrl) {
		this.loadUrl = loadUrl;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getActionLocKey() {
		return actionLocKey;
	}

	public void setActionLocKey(String actionLocKey) {
		this.actionLocKey = actionLocKey;
	}

	public int getBadge() {
		return badge;
	}

	public void setBadge(int badge) {
		this.badge = badge;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getLocKey() {
		return locKey;
	}

	public void setLocKey(String locKey) {
		this.locKey = locKey;
	}

	public String getLocArgs() {
		return locArgs;
	}

	public void setLocArgs(String locArgs) {
		this.locArgs = locArgs;
	}

	public String getLaunchImage() {
		return launchImage;
	}

	public void setLaunchImage(String launchImage) {
		this.launchImage = launchImage;
	}

}
