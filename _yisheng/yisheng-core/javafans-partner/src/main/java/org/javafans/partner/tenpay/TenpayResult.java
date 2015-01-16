package org.javafans.partner.tenpay;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.javafans.common.utils.string.CommonStringUtils;
import org.javafans.partner.PayConfigHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenpay.ResponseHandler;

/**
 * 财付通支付回调参数结果
 * @author ChenJunhui
 *
 */
public class TenpayResult {
	
	private  static Logger logger = LoggerFactory.getLogger(TenpayResult.class);
	
	private TenpayResult(){
		super();
	}
	
	/**
	 * 通过静态方法获取财付通返回结果对象
	 * @param request
	 * @param response
	 * @return
	 */
	public static TenpayResult builderTenpayResult(HttpServletRequest request,HttpServletResponse response){
		TenpayResult result = new TenpayResult();
		ResponseHandler _resHandler = new ResponseHandler(request, response);
		_resHandler.setKey(PayConfigHolder.getTenpayKey());
		result.resHandler = _resHandler;
		try {
			result.verifyResult = TenpayUtils.verifyResult(_resHandler);
		} catch (Exception e) {
			logger.error("tenpay verify fail",e);
			result.verifyResult = false;
		}
		return result;
	}

	/**
	 *财付通响应参数 如果 系统订单成功后 可调用 该参数的  resHandler.sendToCFT("success"); 方法给财付通发消息 财付通后续就不会继续回调了
	 */
	private ResponseHandler resHandler;
	
	/**
	 * 财付通回调参数校验是否成功
	 */
	private boolean verifyResult = false;

	
	/**
	 * 验证财付通回调是否合法
	 * @return
	 */
	public boolean isVerifySuccess() {
		return verifyResult;
	}

	/**
	 * 获取财付通回调参数
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getReturnParam() {
		return resHandler.getAllParameters();//回调参数;
	}

	/**
	 * 签名是否合法
	 * @return
	 */
	public boolean isSignSuccess() {
		return resHandler.isTenpaySign();
	}

	/**
	 * 商户订单号
	 */
	public String getOutTradeno() {
		return resHandler.getParameter("out_trade_no");
	}

	/**
	 * 财付通订单号
	 */
	public String getTransactionId() {
		return resHandler.getParameter("transaction_id");
	}

	/**
	 * 支付结果
	    0付款成功
		1交易创建
		2收获地址填写完毕
		4卖家发货成功
		5买家收货确认，交易成功
		6交易关闭，未完成超时关闭
		7修改交易价格成功
		8买家发起退款
		9退款成功
		10退款关闭
	 */
	public String getTradeState() {
		return resHandler.getParameter("trade_state");
	}
	
	/**
	 * 返回的状态是否交易成功的状态
	 * @return
	 */
	public boolean isTradeSuccess(){
		return CommonStringUtils.in(getTradeState(), "0","1");
				
	}

	public ResponseHandler getResHandler() {
		return resHandler;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
