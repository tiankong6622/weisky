package org.itboys.admin.tools;

import java.util.List;
import java.util.Map;

import org.itboys.admin.constant.AdminSessionConstant;
import org.itboys.admin.entity.BaseAdminEntity;
import org.itboys.commons.utils.servlet.ServletContextHolder;

/**
 * 管理后台session相关
 * @author 俊哥.Chen
 *
 */
public class AdminSessionHolder {
	/**
	 * 获取登入这IP
	 * 
	 * @return
	 */
	public static String getLoginIp() {
		return ServletContextHolder.getRequest().getRemoteAddr();
	}

	/**
	 * 获取session中的用户ID
	 * @return
	 */
	public static final Long getAdminUserId(){
		return (Long) ServletContextHolder.getSession().getAttribute(AdminSessionConstant.SESSION_USER_ID);
	}
	
	/**
	 * 设置session中的用户ID
	 * @return
	 */
	public static final void setAdminUserId(Long userId){
		 ServletContextHolder.getSession().setAttribute(AdminSessionConstant.SESSION_USER_ID,userId);
	}
	
	/**
	 * 移除session中的用户ID
	 * @return
	 */
	public static final void removeAdminUserId(){
		 ServletContextHolder.getSession().removeAttribute(AdminSessionConstant.SESSION_USER_ID);
	}
	
	/**
	 * 获取session中的用户类型
	 * @return
	 */
	public static final Integer getUserType(){
		return (Integer) ServletContextHolder.getSession().getAttribute(AdminSessionConstant.SESSION_USER_TYPE);
	}
	
	/**
	 * 设置session中的用户类型
	 * @return
	 */
	public static final void setUserType(Integer type){
		 ServletContextHolder.getSession().setAttribute(AdminSessionConstant.SESSION_USER_TYPE,type);
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
	 * 获取session中的权限
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String,List<String>> getPermissionMap(){
		return (Map<String,List<String>>) ServletContextHolder.getSession().getAttribute(AdminSessionConstant.SESSION_PERMISSION);
	}
	
	/**
	 * 设置session中的权限
	 * @return
	 */
	public static final void setPermissionMap(Map<String,List<String>> perssmionMap){
		ServletContextHolder.getSession().setAttribute(AdminSessionConstant.SESSION_PERMISSION,perssmionMap);
	}
	

	/**
	 * 获取session中的用户组织ID
	 * @return
	 */
	public static final Long getOrgId(){
		return (Long) ServletContextHolder.getSession().getAttribute(AdminSessionConstant.SESSION_ORG_ID);
	}
	
	/**
	 * 设置session中的用户组织ID
	 * @return
	 */
	public static final void setOrgId(long orgId){
	  ServletContextHolder.getSession().setAttribute(AdminSessionConstant.SESSION_ORG_ID,orgId);
	}
	
	/**
	 * 将admin 登入用户信息 创建时间 等信息注入对象
	 * 
	 * @param BaseAdminEntity的子类
	 * @return
	 */
	public static <T extends BaseAdminEntity> void prepareAdminLoginData(T t) {
		LoginHolder.prepareLoginMessage(t, "a");
	}
	
	/**
	 * 看情况组装 admin或project的用户ID
	 * 
	 * @param t
	 */
	public static <T extends BaseAdminEntity> void prepareAdminAndProjectLoginData(T t) {
		LoginHolder.prepareLoginMessage(t, WebConstants.ALL_USER_SESSIONKEY);
	}
	/**
	 * 获得前台登入用户的memberId
	 * 
	 * @return
	 */
	public static Long getMemberId() {
		return (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.MEMBER_ID);
	}

	public static Long getWeixinUserId() {
		return (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.WEIXIN_USER_ID);
	}

	public static Long getWeixinId() {
		return (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.WEIXIN_ID);
	}
}
