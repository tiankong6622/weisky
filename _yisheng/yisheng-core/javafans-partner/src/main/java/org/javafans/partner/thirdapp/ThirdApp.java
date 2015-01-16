package org.javafans.partner.thirdapp;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.javafans.common.utils.cookie.CookieUtils;
import org.javafans.common.utils.random.RandomUtils;
import org.javafans.partner.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

/**
 * 三方应用抽象父类 子类实现各个抽象方法
 * see 各个抽象方法的父类
 * @author ChenJunhui
 *
 */
public abstract class ThirdApp {
	
	private static  Logger logger = LoggerFactory.getLogger(ThirdApp.class);

	private static Map<String,String> configMap = ThirdAppConifg.getConfigMap();
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * 微博授权URL
	 */
	private static String WEIBO_AUTHORIZEURL = null;
	/**
	 * qq授权URL
	 */
	private static String TENCENT_AUTHORIZEURL = null;
	static{
		WEIBO_AUTHORIZEURL = getWeiboAuthorizeurl();
		TENCENT_AUTHORIZEURL=getTencentAuthorzeurl();
	}

	/**
	 *  第三方登入 成功以后 组装好三方用户信息 本系统实现该抽象类 对登入信息做处理
	 */
	protected abstract String doWithThirdUser(TridUser user,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 将微博令牌交换的数据 做些处理 如果做微博其他应用的话 必须需要返回的令牌
	 * @param param
	 * @return
	 */
	protected abstract String doWithWeiboReturnData(Map<String,String> param);
	
	/**
	 * 跳转微博登入
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void gotoWeibo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.sendRedirect(WEIBO_AUTHORIZEURL);
	}
	
	/**
	 * 微博回调地址
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	public void weiboCallBack(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String code = request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
			Map<String,Object> params = new HashMap<String, Object>(5);
			params.put("client_id", configMap.get("weibo-appkey"));
			params.put("client_secret", configMap.get("weibo-sercet"));
			params.put("grant_type", "authorization_code");
			params.put("redirect_uri", configMap.get("weibo-redirectUri"));
			params.put("code", code);
			String content = HttpHelper.doPost(configMap.get("weibo-accessTokenUrl"), params);
			if(StringUtils.indexOf(content, "access_token")!=-1){
				try {
					Map<String, String> weiboParam = objectMapper.readValue(content, Map.class);
					doWithWeiboReturnData(weiboParam);
					String accessToken = weiboParam.get("access_token");
					String userId = weiboParam.get("uid");
					//Users um = new Users();
					//um.client.setToken(accessToken);
					// 需要调用其他应用的时候 该token需要存在cookie或session里
					//User user = um.showUserById(userId);
					TridUser thirdUser = new TridUser();
					thirdUser.setUserId(userId);
				//	thirdUser.setName(user.getName());
					thirdUser.setSource(TridUser.SOURCE_WEIBO);
					//第三方用户处理
					doWithThirdUser(thirdUser,request,response);
					response.sendRedirect(configMap.get("weibo_login_error_url"));
				} catch (Exception e) {
					logger.error("WeiboException,return content ="+content,e);
					response.sendRedirect(configMap.get("weibo_login_error_url"));
				}
			}else{
				logger.error("weiboCallBack return code is null");
				response.sendRedirect(configMap.get("weibo_login_error_url"));
			}
		}else{
			logger.error("weiboCallBack return code is null");
			response.sendRedirect(configMap.get("weibo_login_error_url"));
		}
	}
	
	/**
	 * 跳转qq登入
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void gotoTencent(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String state = RandomUtils.getIntRandom(6);//防止csrf攻击加入的
		CookieUtils.addCookie(response, "qqstate", state, 86400);
		response.sendRedirect(TENCENT_AUTHORIZEURL+"&state="+state);
	}
	
	/**
	 * 腾讯回调地址
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	public void tencentCallback(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String code = request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
			Map<String,Object> params = new HashMap<String, Object>(5);
			params.put("client_id", configMap.get("tencent-appkey"));
			params.put("client_secret", configMap.get("tencent-sercet"));
			params.put("grant_type", "authorization_code");
			params.put("redirect_uri", configMap.get("weibo-redirectUri"));
			params.put("code", code);
			String content = HttpHelper.doPost(configMap.get("tencent-accessTokenUrl"), params);
			System.out.println("qqqqqqqqqqqqqqqqqqqq="+content);
			if(StringUtils.indexOf(content, "access_token")!=-1){
				try{
						String[] arr = StringUtils.split(StringUtils.trim(content), "&");
						String[] tokenarr = StringUtils.split(StringUtils.trim(arr[0]), "=");
						String accessToken = tokenarr[1];
						String userJson = HttpHelper.doGet(configMap.get("tencent-meUrl")+"?access_token="+accessToken);
						if(StringUtils.indexOf(content, "openid")!=-1){
							userJson=userJson.replace("callback(", "");
							userJson=userJson.replace(");", "");
							Map<String, String> qqParam = objectMapper.readValue(userJson, Map.class);
							String openid = qqParam.get("openid");
							String qqUserInfoUrl = configMap.get("tencent-getuserinfoUrl");
							Map<String, Object> getUserInfoParam = Maps.newHashMapWithExpectedSize(3);
							getUserInfoParam.put("openid", openid);
							getUserInfoParam.put("access_token", accessToken);
							getUserInfoParam.put("oauth_consumer_key", configMap.get("tencent-appkey"));
							String userInfo = HttpHelper.doGet(qqUserInfoUrl, getUserInfoParam);
							Map<String,String> userInfoJson = objectMapper.readValue(userInfo, Map.class);
							TridUser thirdUser = new TridUser();
							thirdUser.setUserId(openid);
							thirdUser.setName(userInfoJson.get("nickname"));
							thirdUser.setSource(TridUser.SOURCE_TENCENT);
							//第三方用户处理
							doWithThirdUser(thirdUser,request,response);
							response.sendRedirect(configMap.get("/"));
						}
						
				} catch (Exception e) {
					logger.error("WeiboException,return content ="+content,e);
					response.sendRedirect(configMap.get("tencent_login_error_url"));
				}
				
			}else{
				logger.error("weiboCallBack return access_token not exist,content={}",content);
				response.sendRedirect(configMap.get("tencent_login_error_url"));
			}
		}else{
			logger.error("qqCallBack return code is null");
			response.sendRedirect(configMap.get("tencent_login_error_url"));
		}
	}
	
	/**
	 * 腾讯授权地址
	 * @return
	 */
	public static String getTencentAuthorzeurl(){
		//新浪微博授权地址
		String authorizeUrl = configMap.get("tencent-authorizeUrl");
		//新浪微博appkey
		String clientId = configMap.get("tencent-appkey");
		//新浪微博授权成功后回调的本系统地址
		String redirectUri = configMap.get("tencent-redirectUri");
		return authorizeUrl+"?client_id="+clientId+"&response_type=code&redirect_uri="+redirectUri;
	}
	
	/**
	 * 初始化微博授权url
	 * @return
	 */
	private static String getWeiboAuthorizeurl() {
		//新浪微博授权地址
		String authorizeUrl = configMap.get("weibo-authorizeUrl");
		//新浪微博appkey
		String clientId = configMap.get("weibo-appkey");
		//新浪微博授权成功后回调的本系统地址
		String redirectUri = configMap.get("weibo-redirectUri");
		return authorizeUrl+"?client_id="+clientId+"&response_type=code&redirect_uri="+redirectUri;
	}

}
