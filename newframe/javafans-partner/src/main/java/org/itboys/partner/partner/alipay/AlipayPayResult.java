package org.itboys.partner.partner.alipay;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.itboys.commons.utils.servlet.Servlets;
import org.itboys.commons.utils.string.CommonStringUtils;

/**
 * 支付宝支付回调参数封装
 * @author ChenJunhui
 *
 */
public class AlipayPayResult {

	private AlipayPayResult(){
		super();
	}
	
	/**
	 * 静态创建AlipayPayResult 对象的方法
	 * @param request
	 * @return
	 */
	public static AlipayPayResult buildAlipayPayResult(HttpServletRequest request){
		AlipayPayResult payResult = new AlipayPayResult();
		payResult.returnParam=Servlets.genMapByRequestParas(request, AlipayConstants.DEFAULT_CHARSET);
		payResult.verifyResult = AlipayUtils.verify(payResult.returnParam);
		return payResult;
	}
	
	/**
	 * 支付宝回调参数校验是否成功
	 */
	private boolean verifyResult = false;
	/**
	 * 支付宝所有请求参数封装的map
	 */
	private Map<String,String> returnParam;
	
	/**
	 * 获取支付宝交易号
	 * @return
	 */
	public String getTradeNo(){
		return returnParam.get("trade_no");
	}
	/**
	 * 获取订单号
	 * @return
	 */
	public String getOrderNo(){
		return returnParam.get("out_trade_no");
	}
	
	/**
	 * 获得支付宝回调交易状态
	 * TRADE_FINISHED 交易完成
	 * TRADE_SUCCESS 交易成功
	 * 其他状态参考文档
	 * @return
	 */
	public String getTradeStatus(){
		return returnParam.get("trade_status");
	}
	
	/**
	 * 判断交易是否成功
	 * @return
	 */
	public boolean isTradeSuccess(){
		return CommonStringUtils.in(getTradeStatus(), "TRADE_FINISHED","TRADE_SUCCESS");
	}
	
	/**
	 * true 同步回调 false 异步回调
	 * @return
	 */
	public boolean isSync(){
		return StringUtils.equals(returnParam.get("notify_type"), "trade_status_sync");
	}
	
	/**
	 * 支付宝回调参数校验是否成功
	 */
	public boolean isVerifySuccess() {
		return verifyResult;
	}
	
	/**
	 * 支付宝所有请求参数封装的map
	 */
	public Map<String, String> getReturnParam() {
		return returnParam;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
