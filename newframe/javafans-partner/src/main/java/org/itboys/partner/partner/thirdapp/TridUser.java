package org.itboys.partner.partner.thirdapp;

/**
 * 三方用户的信息
 * @author ChenJunhui
 *
 */
public class TridUser {
	
	public final static String SOURCE_WEIBO="sina";//新浪微博
	
	public final static  String SOURCE_TENCENT="qq";//腾讯网友
	
	public final static  String SOURCE_ALIPAY="alipay";//支付宝网友

	/**
	 * 	三方用户的ID
	 */
	private String userId;
	/**
	 * 三方用户的用户名
	 */
	private String name;
	
	/*
	 * 扩展字段
	 */
	private String field;
	
	/**
	 * 来源哪个开放平台 比如 微博 腾讯 支付宝 等等
	 */
	private String source;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
