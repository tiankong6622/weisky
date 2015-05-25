package org.itboys.partner.wxpay;

/**
 * 微信支付参数
 * @author ChenJunhui
 *
 */
public class WxPayParam {
	
	private String appid; //appid 微信appid
	private String appkey;//appkey 微信appkey
	private String partnerId;//partnerId 微信支付商户ID
	private String partnerKey;//partnerKey 微信支付商户key
	private String ip;//request获取的IP地址
	private String notifyUrl;//异步通知地址
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerKey() {
		return partnerKey;
	}
	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
}
