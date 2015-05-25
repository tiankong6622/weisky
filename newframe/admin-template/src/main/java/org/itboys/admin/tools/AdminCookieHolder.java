package org.itboys.admin.tools;
 
import org.itboys.admin.constant.AdminCookieConstant;
import org.itboys.commons.utils.cookie.CookieUtils;
import org.itboys.commons.utils.servlet.ServletContextHolder;

/**
 * 管理后台 cookie相关的变量
 * @author 俊哥.Chen
 *
 */
public class AdminCookieHolder {

	public static void setAdminUserName(String username){
		CookieUtils.addCookie(ServletContextHolder.getResponse(), AdminCookieConstant.COOKIE_USER_NAME, username, 100*24*60*60);
	}
	public static void setAdminPassword(String password){
		CookieUtils.addCookie(ServletContextHolder.getResponse(), AdminCookieConstant.COOKIE_PASSWORD, password, 100*24*60*60);
	}
	
	public static void setUserName(String name){
		CookieUtils.addCookie(ServletContextHolder.getResponse(), AdminCookieConstant.COOKIE_NAME, name, 100*24*60*60);
	}
	
	public static String getAdminUserName(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), AdminCookieConstant.COOKIE_USER_NAME);
	}
	public static String getAdminPassword(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), AdminCookieConstant.COOKIE_PASSWORD);
	}
	
	public static String getUserName(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), AdminCookieConstant.COOKIE_NAME);
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
	 * 将登入用户名放到cookie中
	 * @param password
	 * @param time
	 * @return
	 */
	public static void addUserNameToCookie(String userName,int time){
		CookieUtils.addCookieWithBase64(ServletContextHolder.getResponse(), WebConstants.CookieKey.COOKIE_KEY_ADMIN_USER_NAME, userName, time);
	}
	/**
	 * 获取cookie中的登入密码
	 * @return
	 */
	public static String getPassword(){
		return CookieUtils.getDecryptBase64ValueFromCookie(ServletContextHolder.getRequest(), WebConstants.CookieKey.PASSWORD);
	}
}
