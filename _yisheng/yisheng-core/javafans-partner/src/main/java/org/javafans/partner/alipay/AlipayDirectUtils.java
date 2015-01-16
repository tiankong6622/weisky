package org.javafans.partner.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.javafans.common.utils.string.CommonStringUtils;
import org.javafans.partner.PayConfigHolder;

import com.google.common.collect.Maps;

/**
 * 支付宝即时到账相关调用
 * @author ChenJunhui
 */
public class AlipayDirectUtils {
	
	/**
	 * 直接获得get请求支付宝支付页面的URL
	 * @param alipayDirectParam
	 * @return
	 */
	public static String getRedirectAlipayUrl(AlipayDirectParam alipayDirectParam){
		Map<String, String> paramMap = AlipayDirectUtils.getAlipayUrlRequestParam(alipayDirectParam);
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
		return AlipayConstants.ALIPAY_GATEWAY_NEW+"?"+redirectUrl.toString().substring(1);
		
	}
	

	/**
	 * 将支付宝请求参数 拼装成 form表单自动提交字符串
	 * @param alipayDirectParam
	 * @return
	 */
	public static String getSubmitAlipayFormString(AlipayDirectParam alipayDirectParam){
		Map<String, String> paramMap = AlipayDirectUtils.getAlipayUrlRequestParam(alipayDirectParam);
		StringBuilder formStr = new StringBuilder();
		formStr.append("<form id='alipaysubmit' action='")
			.append(AlipayConstants.ALIPAY_GATEWAY_NEW)/*.append("_input_charset=").append(AlipayConstants.DEFAULT_CHARSET)*/
			.append("' method='get' >");
		Set<Entry<String, String>> entrySet = paramMap.entrySet();
		for(Entry<String, String> entry:entrySet){
			formStr.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
		}
		formStr.append("</form>");
		formStr.append("<script>document.getElementById('alipaysubmit').submit();</script>");
		return formStr.toString();
	}
	
	/**
	 * 获取支付宝请求页面的接口map
	 * @return
	 */
	public static Map<String, String> getAlipayUrlRequestParam(AlipayDirectParam alipayDirectParam){
		Map<String, String> paramMap = Maps.newHashMap();
		paramMap.put("service", AlipayConstants.SERVICE_DIRECT); // 即时到账接口名称
		paramMap.put("payment_type", alipayDirectParam.getPaymentType()); // 支付类型 1商品购买
		paramMap.put("partner", PayConfigHolder.getAlipayPartner()); // 合作伙伴在支付宝的用户id
		paramMap.put("seller_email", PayConfigHolder.getAlipaySellerEmail());//卖家email
		paramMap.put("return_url", PayConfigHolder.getAlipayReturnUrl()); // 同步返回的url
		paramMap.put("notify_url", PayConfigHolder.getAlipayNotifyUrl()); // 异步通知的url
		paramMap.put("out_trade_no", alipayDirectParam.getOrderId());
		paramMap.put("subject", alipayDirectParam.getSubject());
        paramMap.put("total_fee", CommonStringUtils.formatNumber(alipayDirectParam.getTotalFee().toString(), 2));
        paramMap.put("_input_charset", AlipayConstants.DEFAULT_CHARSET);
        if(StringUtils.equals(alipayDirectParam.getPaymethod(), AlipayConstants.PAY_METHOD_BANKPAY)){
        	paramMap.put("paymethod", alipayDirectParam.getPaymethod());
        	paramMap.put("defaultbank", alipayDirectParam.getDefaultbank());
        }
        if(StringUtils.isNotBlank(alipayDirectParam.getToken())){
        	 paramMap.put("token", alipayDirectParam.getToken());
        }
        //签名结果与签名方式加入请求提交参数组中
        paramMap.put("sign", AlipayUtils.buildMysign(paramMap));
        paramMap.put("sign_type", AlipayConstants.SIGN_TYPE);
        return paramMap;
	}
}
