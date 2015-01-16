package com.hz.yisheng.webdata;

import org.javafans.dto.login.LoginHolder;
import org.javafans.dto.login.LoginUser;
import org.javafans.orm.entity.base.BaseAdminEntity;
import org.javafans.web.servlet.ServletContextHolder;

/**
 * 获得session中一些数据的方法
 * 
 * @author ChenJunhui
 * 
 */
public class SessionHolder {

	/**
	 * 获取登入这IP
	 * 
	 * @return
	 */
	public static String getLoginIp() {
		return ServletContextHolder.getRequest().getRemoteAddr();
	}

	/**
	 * 根据参数名称获取保存在session中的信息
	 * 
	 * @return
	 */
	public static Object getAttribute(String objName) {
		return ServletContextHolder.getRequest().getSession().getAttribute(objName);
	}

	/**
	 * 获取session中的projectId
	 * 
	 * @return
	 */
	public static Long getProjectId() {
		Long projectId = (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.PROJECT_ID);
		if (projectId == null) {
			throw new RuntimeException("projectId is not in session");
		}
		return projectId;
	}

	/**
	 * 获取session中的shopId
	 * 
	 * @return
	 */
	public static Long getShopId() {
		Long shopId = (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.SHOP_ID);
		if (shopId == null) {
			throw new RuntimeException("shopId is not in session");
		}
		return shopId;
	}

	/**
	 * 获取admin 登入用户的ID
	 * 
	 * @return
	 */
	public static Long getAdminUserId() {
		return LoginHolder.getLoginUser(WebConstants.SessionKey.ADMIN_USER).getUserid();
	}

	/**
	 * 获取session中admin的登入用户
	 * 
	 * @return
	 */
	public static LoginUser getAdminLoginUser() {
		return LoginHolder.getLoginUser(WebConstants.SessionKey.ADMIN_USER);
	}

	/**
	 * 获取session中admin的登入用户
	 * 
	 * @return
	 */
	public static LoginUser getProjectLoginUser() {
		return LoginHolder.getLoginUser(WebConstants.SessionKey.PROJECT_USER);
	}

	/**
	 * 获取session中前台会员的登入用户
	 * 
	 * @return
	 */
	public static LoginUser getMemberLoginUser() {
		return LoginHolder.getLoginUser(WebConstants.SessionKey.MEMBER_USER);
	}

	/**
	 * 获取session中卖家的id
	 * 
	 * @return
	 */
	public static Long getSellerId() {
		return (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.SELLER_ID);
	}

	/**
	 * 获取project用户的ID
	 * 
	 * @return
	 */
	public static Long getProjectUserId() {
		LoginUser lu = LoginHolder.getLoginUser(WebConstants.SessionKey.PROJECT_USER);
		return lu == null ? null : lu.getUserid();
	}

	/**
	 * 获得前台登入用户的memberId
	 * 
	 * @return
	 */
	public static Long getMemberId() {
		return (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.MEMBER_ID);
	}

	/**
	 * 将admin 登入用户信息 创建时间 等信息注入对象
	 * 
	 * @param BaseAdminEntity的子类
	 * @return
	 */
	public static <T extends BaseAdminEntity> void prepareAdminLoginData(T t) {
		LoginHolder.prepareLoginMessage(t, WebConstants.SessionKey.ADMIN_USER);
	}

	/**
	 * 将member 登入用户信息 创建时间 等信息注入对象
	 * 
	 * @param BaseAdminEntity的子类
	 * @return
	 */
	public static <T extends BaseAdminEntity> void prepareMemberLoginData(T t) {
		LoginHolder.prepareLoginMessage(t, WebConstants.SessionKey.MEMBER_USER);
	}

	/**
	 * 将project 登入用户信息 创建时间 等信息注入对象
	 * 
	 * @param BaseAdminEntity的子类
	 * @return
	 */
	public static <T extends BaseAdminEntity> void prepareProjectLoginData(T t) {
		LoginHolder.prepareLoginMessage(t, WebConstants.SessionKey.PROJECT_USER);
	}

	/**
	 * 看情况组装 admin或project的用户ID
	 * 
	 * @param t
	 */
	public static <T extends BaseAdminEntity> void prepareAdminAndProjectLoginData(T t) {
		LoginHolder.prepareLoginMessage(t, WebConstants.ALL_USER_SESSIONKEY);
	}

	public static Long getWeixinUserId() {
		return (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.WEIXIN_USER_ID);
	}

	public static Long getWeixinId() {
		return (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.WEIXIN_ID);
	}
}
