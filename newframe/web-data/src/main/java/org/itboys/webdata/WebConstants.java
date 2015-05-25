package org.itboys.webdata;

/**
 * web层的常量
 * @author ChenJunhui
 *
 */
public class WebConstants {

	public interface SessionKey{
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
		
		public final String MEMBER_NAME="cmn";
		
		/**
		 * 自动登入时候 密码放到cookie中
		 */
		public final String PASSWORD="c_wg";
		
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
