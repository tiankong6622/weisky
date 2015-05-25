package org.itboys.admin.constant;

/**
 * 管理后台session相关
 * @author 俊哥
 *
 */
public interface AdminSessionConstant {

	/**
	 * 管理后台session中用户ID的key
	 */
	public static final String SESSION_USER_ID = "au";
	/**
	 * 管理后台session中用户的类型
	 */
	public static final String SESSION_USER_TYPE="aut";
	/**
	 * 管理后台session中用户ID的权限
	 */
	public static final String SESSION_PERMISSION = "ap";
	/**
	 * 管理后台session中用户当前组织ID
	 */
	public static final String SESSION_ORG_ID = "ao";
}
