package org.javafans.common.utils.ip;

import javax.servlet.http.HttpServletRequest;

/**
 * IP地址解析相关
 * @author ChenJunhui
 */
public class IPUtil {

	/**
	 * IP地址转成long型
	 * @param ip
	 * @return
	 */
	public static long ip2long(String ip) {
		String[] ips = ip.split("[.]");
		long num = 16777216L * Long.parseLong(ips[0]) + 65536L
				* Long.parseLong(ips[1]) + 256 * Long.parseLong(ips[2])
				+ Long.parseLong(ips[3]);
		return num;

	}

	/**
	 * long型转IP地址
	 * @param ipLong
	 * @return
	 */
	public static String long2ip(long ipLong) {
		long mask[] = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };
		long num = 0;
		StringBuffer ipInfo = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			num = (ipLong & mask[i]) >> (i * 8);
			if (i > 0) {
				ipInfo.insert(0, ".");
			}
			ipInfo.insert(0, Long.toString(num, 10));
		}

		return ipInfo.toString();

	}

	public static String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(IPUtil.ip2long("125.120.85.115"));
		System.out.println(IPUtil.long2ip(1033476647L));

	}

}
