package com.hz.yisheng.webdata;

/**
 * web层的常量
 * @author ChenJunhui
 *
 */
public class WebConstants {

	public static final String[] ALL_USER_SESSIONKEY=new String[]{SessionKey.ADMIN_USER,SessionKey.PROJECT_USER,SessionKey.MEMBER_USER,SessionKey.SUPPLIER_USER};
	
	public interface SessionKey{
		/**
		 * Session中的admin用户key
		 */
		public final String ADMIN_USER="a";
		/**
		 * session中的 project_user
		 */
		public final String PROJECT_USER="p";
		/**
		 * session中的projectId
		 */
		public final String PROJECT_ID="pi";
		/**
		 * session中前台会员的memberid
		 */
		public final String MEMBER_ID="m";
		/**
		 * session中前台会员
		 */
		public final String MEMBER_USER="m_u";
		/**
		 * session 中卖家用户ID
		 */
		public final String SELLER_ID="s";
		/**
		 * session中供货商会员
		 */
		public final String SUPPLIER_USER="supp_user";
		/**
		 * session中商家id
		 */
		public final String SHOP_ID="si";
		/**
		 * 微信用户ID
		 */
		public final String WEIXIN_USER_ID="wui";
		/**
		 * 微信配置ID
		 */
		public final String WEIXIN_ID="wi";
	}
	
	public interface CookieKey{
		/**
		 * 登入用户的cookie名称
		 */
		public final String COOKIE_KEY_ADMIN_USER_NAME="ca";
		
		/**
		 * 登入会员的cookie名称
		 */
		public final String COOKIE_KEY_MEMBER_USER_NAME="cm";
		/**
		 * project的username
		 */
		public final String COOKIE_KEY_PROJECT_USER_NAME="cpu";
		/**
		 * project中工程的名称
		 */
		public final String COOKIE_KEY_PROJECT_NAME="cpn";
		/**
		 * projct的domain
		 */
		public final String COOKIE_KEY_PROJECT_DOMAIN="cpd";
		/**
		 * project的code
		 */
		public final String PROJECT_CODE="cpc";
		
		public final String MEMBER_NAME="cmn";
		/**
		 * 自动登入时候 密码放到cookie中
		 */
		public final String PASSWORD="c_gege";
		
		/**
		 * 微信用户ID
		 */
		public final String COOKIE_WEIXIN_USER_ID="c60";
		/**
		 * 微信配置ID
		 */
		public final String COOKIE_WEIXIN_ID="c94";
	}
	
}
