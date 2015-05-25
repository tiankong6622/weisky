package org.itboys.webdata;

import org.apache.commons.lang3.math.NumberUtils;
import org.itboys.commons.utils.cookie.CookieUtils;
import org.itboys.commons.utils.servlet.ServletContextHolder;

/**
 * 获得cookie中一些数据的方法
 * @author ChenJunhui
 *
 */
public class CookieHolder {
	
	/**
	 * 获取cookie中登入会员的名称
	 * @return
	 */
	public static String getMemberUserName(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.COOKIE_KEY_MEMBER_USER_NAME);
	}
	
	/**
	 * 获取登入会员姓名
	 * @return
	 */
	public static String getMemberName(){
		return CookieUtils.getDecryptBase64ValueFromCookie(ServletContextHolder.getRequest(),  WebConstants.CookieKey.MEMBER_NAME);
	}
	/**
	 * 获取cookie中的登入密码
	 * @return
	 */
	public static String getPassword(){
		return CookieUtils.getDecryptBase64ValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.PASSWORD);
	}
	/**
	 * 将登入密码放到cookie中
	 * @param password
	 * @param time
	 * @return
	 */
	public static void addPasswordToCookie(String password,int time){
		CookieUtils.addCookieWithBase64(ServletContextHolder.getResponse(), WebConstants.CookieKey.PASSWORD, password, time);
	}
	
	/**
	 * 将微信用户ID加到cookie
	 * @param weixinUserId
	 * @param time
	 */
	public static void addWeixinUserIdToCookie(Long weixinUserId,int time){
		CookieUtils.addCookieWithBase64(ServletContextHolder.getResponse(), WebConstants.CookieKey.COOKIE_WEIXIN_USER_ID, String.valueOf(weixinUserId), time);
	}
	
	/**
	 * 从cookie中获取微信用户的ID
	 * @return
	 */
	public static Long getWeixinUserId(){
		String weixinUserId = CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.COOKIE_WEIXIN_USER_ID);
		if(NumberUtils.isDigits(weixinUserId)){
			return Long.parseLong(weixinUserId);
		}
		return null;
	}
	
	/**
	 * 将微信ID加到cookie里
	 * @param weixinUserId
	 * @param time
	 */
	public static void addWeixinIdToCookie(Long weixinId,int time){
		CookieUtils.addCookieWithBase64(ServletContextHolder.getResponse(), WebConstants.CookieKey.COOKIE_WEIXIN_ID, String.valueOf(weixinId), time);
	}
	
	/**
	 * 从cookie中获取微信用户ID
	 * @return
	 */
	public static Long getWeixinId(){
		String weixinId = CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.COOKIE_WEIXIN_ID);
		if(NumberUtils.isDigits(weixinId)){
			return Long.parseLong(weixinId);
		}
		return null;
	}
}
