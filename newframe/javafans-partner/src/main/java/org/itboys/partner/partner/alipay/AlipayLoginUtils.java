package org.itboys.partner.partner.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.itboys.partner.partner.PayConfigHolder;
import org.itboys.partner.partner.thirdapp.ThirdAppConifg;

public class AlipayLoginUtils {

	public static final String ALIPAY_LOGIN_SERVICE="user.auth.quick.login";
	
	/**
	 * 直接获得get请求支付宝支付页面的URL
	 * @param alipayDirectParam
	 * @return
	 */
	public static String getAlipayLoginUrl(){
		Map<String, String> paramMap = getAlipayLoginParamMap();
		StringBuilder redirectUrl = new StringBuilder();
		Set<Entry<String, String>> entrySet = paramMap.entrySet();
		for(Entry<String, String> entry:entrySet){
			try {
				String value = StringUtils.isBlank(entry.getValue())?entry.getValue():URLEncoder.encode(entry.getValue(), AlipayConstants.DEFAULT_CHARSET);
				redirectUrl.append("&").append(entry.getKey()).append("=").append(value);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return ThirdAppConifg.getValue("alipay-gateway")+redirectUrl.toString().substring(1);
	}
	/**
	 * 将支付宝请求参数 拼装成 form表单自动提交字符串
	 * @param alipayDirectParam
	 * @return
	 */
	public static Map<String, String> getAlipayLoginParamMap(){
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.auth.authorize");
        sParaTemp.put("partner", PayConfigHolder.getAlipayPartner());
        sParaTemp.put("_input_charset", AlipayConstants.DEFAULT_CHARSET);
		sParaTemp.put("target_service", ALIPAY_LOGIN_SERVICE);
		sParaTemp.put("return_url", ThirdAppConifg.getValue("alipay-redirectUri"));
		sParaTemp.put("sign", AlipayUtils.buildMysign(sParaTemp));
		sParaTemp.put("sign_type", AlipayConstants.SIGN_TYPE);
	    return sParaTemp;
	}
}
