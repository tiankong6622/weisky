package org.itboys.webdata;

import org.itboys.commons.utils.servlet.ServletContextHolder;

/**
 * 获得session中一些数据的方法
 * @author ChenJunhui
 *
 */
public class SessionHolder {
	
	/**
	 * 获取登入这IP
	 * @return
	 */
	public static String getLoginIp(){
		return ServletContextHolder.getRequest().getRemoteAddr();
	}
	
	
	/**
	 * 获取session中的shopId
	 * @return
	 */
	public static Long getShopId(){
		Long shopId = (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.SHOP_ID);
		if(shopId==null){
			throw new RuntimeException("shopId is not in session");
		}
		return shopId;
	}
	
	
	/**
	 * 获取session中卖家的id
	 * @return
	 */
	public static Long getSellerId(){
		return (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.SELLER_ID);
	}
	
	/**
	 * 获得前台登入用户的memberId
	 * @return
	 */
	public static Long getMemberId(){
		return (Long) ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.MEMBER_ID);
	}
	
	public static Long getWeixinUserId(){
		return (Long)ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.WEIXIN_USER_ID);
	}
	
	public static Long getWeixinId(){
		return (Long)ServletContextHolder.getSession().getAttribute(WebConstants.SessionKey.WEIXIN_ID);
	}
}
