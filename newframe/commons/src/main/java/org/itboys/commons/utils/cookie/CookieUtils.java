package org.itboys.commons.utils.cookie;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.encode.Encodes;
import org.itboys.commons.utils.encryption.Cryptos;

/**
 * cookie相关操作
 * @author Chenjunhui
 *
 */
public class CookieUtils {

	/*
	 * 往cookie里塞值
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void addCookie(HttpServletResponse response, String key,String value,int time){
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(time);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/*
	 * 往固定的域的cookie里塞值
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void addCookie(HttpServletResponse response, String key,String value,int time,String domain){
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(time);
		cookie.setPath("/");
		cookie.setDomain(domain);
		response.addCookie(cookie);
	}
	
	/*
	 * 往cookie里塞值 Base64加密
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void addCookieWithBase64(HttpServletResponse response, String key,String value,int time){
		String cookieValueUrlEncode ;
	    try {
	    	byte[] values = value.getBytes(CommonConstants.ENCODE.UTF_8);
			String cookieValueBase64 = Encodes.encodeBase64(values);
			//System.out.println("cookieValueBase64="+cookieValueBase64);
			 cookieValueUrlEncode = URLEncoder.encode(cookieValueBase64, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		addCookie(response, key, cookieValueUrlEncode, time);
	}
	
	
	/*
	 * 往固定的域的cookie里塞值 Base64加密
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void addCookieWithBase64(HttpServletResponse response, String key,String value,int time,String domain){
		String cookieValueUrlEncode ;
	    try {
			String cookieValueBase64 = Encodes.encodeBase64(value.getBytes(CommonConstants.ENCODE.UTF_8));
			//System.out.println("cookieValueBase64="+cookieValueBase64);
			 cookieValueUrlEncode = URLEncoder.encode(cookieValueBase64, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		addCookie(response, key, cookieValueUrlEncode, time,domain);
	}
	
	/**
	 * 往cookie里塞值 并上Base64+AES加密
	 * @param response
	 * @param key
	 * @param value
	 * @param time
	 * @param aesKey 加密密钥
	 * @param IV 偏移向量
	 */
	public static void addCookieWithBase64AndAes(HttpServletResponse response, String key,String value,int time,byte[] aesKey,byte[] IV){
		try{
			byte[] encryptResult = Cryptos.aesEncrypt(value.getBytes(),aesKey, IV);
			value = Encodes.encodeBase64(encryptResult);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		addCookie(response, key, value, time);
	}
	
	/**
	 * 往cookie里塞值 并上MD5单向加密
	 * @param response
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void addCookieWithMd5(HttpServletResponse response, String key,String value,int time){
		value = DigestUtils.md5Hex(value.getBytes());
		addCookie(response, key, value, time);
	}
	
	/**
	 * 因为MD5是单向加密 所以取出值判断先
	 * @param request
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean isCookieValueMd5Success(HttpServletRequest request,String key,String value){
		value = DigestUtils.md5Hex(value.getBytes());
		String cookieValue = getValueFromCookie(request, key);
		return StringUtils.equals(value, cookieValue);
	}
	
	/**
	 * 根据key 从cookie中获取值
	 * @param key
	 * @return
	 */
	public static String getValueFromCookie(HttpServletRequest request,String key){
		Cookie cookies[] = request.getCookies();
		if(!ArrayUtils.isEmpty(cookies)){
			for(Cookie cookie:cookies){
				if(cookie!=null && StringUtils.equals(key, cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * 从cookie中获取base64解密的value值
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getDecryptBase64ValueFromCookie(HttpServletRequest request,String key){
		String value = getValueFromCookie(request, key);
		if(StringUtils.isNotBlank(value)){
			 try {
				 value = URLDecoder.decode(value, CommonConstants.ENCODE.UTF_8);
				 value =new String(Encodes.decodeBase64(value), CommonConstants.ENCODE.UTF_8);
				return value;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}
		return null;
		
	}
	
	/**
	 * 从cookie中获取base64+AES解密的value值
	 * @param request
	 * @param key
	 * @param aesKey 加密密钥
	 * @param IV 偏移向量
	 * @return
	 */
	public static String getDecryptBase64AndAesValueFromCookie(HttpServletRequest request,String key,byte[] aesKey,byte[] IV){
		String value = getValueFromCookie(request, key);
		byte[] byteArr = Encodes.decodeBase64(value);
		String descryptResult = Cryptos.aesDecrypt(byteArr, aesKey, IV);
		return descryptResult;
	}
	
	/**
	 * 根据key 删除cookie
	 * @param key
	 */
	public static void removeCookie(HttpServletResponse response, String key){
		Cookie cookie = new Cookie(key, null);
		cookie.setMaxAge(0);
		cookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
		response.addCookie(cookie);
	}
	
	/**
	 * 判断cookie里是否有一个key
	 * @param key
	 * @return
	 */
	public static boolean cookiesContailsKey(HttpServletRequest request,String key){
		Cookie cookies[] = request.getCookies();
		if(!ArrayUtils.isEmpty(cookies)){
			for(Cookie cookie:cookies){
				if(cookie!=null && StringUtils.equals(key, cookie.getName())){
					return true;
				}
			}
		}
		return false;
	}
}
