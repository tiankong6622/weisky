package weibo4j.util;

import java.util.HashMap;
import java.util.Map;

import org.javafans.partner.thirdapp.ThirdAppConifg;

public class WeiboConfig {
	
	public WeiboConfig(){}
			
	public static final Map<String,String> changeMap = new HashMap<String,String>(10);
	static{
		changeMap.put("client_ID", "weibo-appkey");
		changeMap.put("client_SERCRET", "weibo-sercet");
		changeMap.put("redirect_URI", "weibo-baseUrl");
		changeMap.put("baseURL", "weibo-baseUrl");
		changeMap.put("accessTokenURL", "weibo-accessTokenUrl");
		changeMap.put("authorizeURL", "weibo-authorizeUr");
		changeMap.put("rmURL", "weibo-rmURL");
	}
	
	public static String getValue(String key){
		return ThirdAppConifg.getValue(changeMap.get(key));
	}

}
