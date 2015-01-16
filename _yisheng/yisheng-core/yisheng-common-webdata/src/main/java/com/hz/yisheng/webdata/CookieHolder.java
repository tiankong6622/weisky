package com.hz.yisheng.webdata;

import org.apache.commons.lang.math.NumberUtils;
import org.javafans.common.utils.cookie.CookieUtils;
import org.javafans.web.servlet.ServletContextHolder;

/**
 * 获得cookie中一些数据的方法
 * @author ChenJunhui
 *
 */
public class CookieHolder {

	
	/**
	 * 获取admin登入用户的用户名
	 * @return
	 */
	public static String getAdminUserName(){
		return CookieUtils.getDecryptBase64ValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.COOKIE_KEY_ADMIN_USER_NAME);
	}
	/**
	 * 获取cookie中projectUser的用户名
	 * @return
	 */
	public static String getProjectUserName(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.COOKIE_KEY_PROJECT_USER_NAME);
	}
	/**
	 * 获取cookie中工程的名称
	 * @return
	 */
	public static String getProjectName(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.COOKIE_KEY_PROJECT_NAME);
	}
	/**
	 * 获取cookie中project的domain
	 * @return
	 */
	public static String getProjectDomain(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.COOKIE_KEY_PROJECT_DOMAIN);
	}
	/**
	 * 获取cookie中projectcode
	 * @return
	 */
	public static String getProjectCode(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.PROJECT_CODE);
	}
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
