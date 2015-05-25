package org.itboys.admin.tools;

import java.util.Date;

import org.itboys.admin.entity.BaseAdminEntity;
import org.itboys.commons.utils.servlet.ServletContextHolder;
import org.itboys.mongodb.entity.BaseEntity;

/**
 * 登入用户基本信息获取
 * @author ChenJunhui
 */
public class LoginHolder {

	/**
	 * 获取登入用户信息
	 * @return
	 */
	public static LoginUser getLoginUser(String userSessionKey){
		LoginUser user =  (LoginUser)ServletContextHolder.getSession().getAttribute(userSessionKey);
		return user;
	}
	
	/**
	 * 将登入用户信息 创建时间 等信息注入对象
	 * @param adminEntity
	 * @return
	 */
	public static void prepareLoginMessage(BaseAdminEntity adminEntity,String userSessionKey){
		if(adminEntity!=null){
			if(ServletContextHolder.getSession().getAttribute(userSessionKey)!=null){
				LoginUser user = getLoginUser(userSessionKey);
				adminEntity.setCr(user.getUserid());
				adminEntity.setUr(user.getUserid());
				adminEntity.setCt(System.currentTimeMillis());
				adminEntity.setUt(System.currentTimeMillis());
			}else{
				adminEntity.setCr(1l);
				adminEntity.setUr(1l);
				adminEntity.setCt(System.currentTimeMillis());
				adminEntity.setUt(System.currentTimeMillis());
			}
		}
	}
	
	/**
	 * 将登入用户信息 创建时间 等信息注入对象
	 * @param entity
	 * @return
	 */
	public static void prepareLoginMessage(BaseAdminEntity entity,String[] userSessionKeys){
		for(String sessionKey:userSessionKeys){
			if(ServletContextHolder.getSession().getAttribute(sessionKey)!=null){
				prepareLoginMessage(entity,sessionKey);
				return;
			}
		}
	}
}
