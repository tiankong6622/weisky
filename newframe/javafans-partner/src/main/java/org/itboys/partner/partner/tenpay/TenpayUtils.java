package org.itboys.partner.partner.tenpay;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.itboys.commons.utils.exception.Exceptions;
import org.itboys.commons.utils.string.CommonStringUtils;
import org.itboys.partner.com.tenpay.RequestHandler;
import org.itboys.partner.com.tenpay.ResponseHandler;
import org.itboys.partner.com.tenpay.client.ClientResponseHandler;
import org.itboys.partner.com.tenpay.client.TenpayHttpClient;
import org.itboys.partner.partner.PayConfigHolder;

/**
 * 财付通相关
 * @author ChenJunhui
 */
public class TenpayUtils {

	
	
	/**
	 * 根据 TenpayParam 的参数 组装请求财付通的url
	 * @param param
	 * @return
	 */
	public static String getTenpayUrl(TenpayParam param,HttpServletRequest request,HttpServletResponse response){
		//创建支付请求对象
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init();
		//设置密钥
		reqHandler.setKey(PayConfigHolder.getTenpayKey());
		//设置支付网关
		reqHandler.setGateUrl(TenpayConstants.TENPAY_GATEWAY);
		//-----------------------------
		//设置支付参数
		//-----------------------------
		reqHandler.setParameter("partner", PayConfigHolder.getTenpayPartner());	//商户号
		reqHandler.setParameter("out_trade_no", param.getOrderId());//商家订单号
		int totalFee = param.getTotalFee().multiply(TenpayConstants.YUAN_DIV_FEN).intValue();
		reqHandler.setParameter("total_fee", String.valueOf(totalFee));//商品金额,以分为单位
		reqHandler.setParameter("return_url", PayConfigHolder.getTenpayReturnUrl());//交易完成后同步跳转的URL
		reqHandler.setParameter("notify_url", PayConfigHolder.getTenpayReturnUrl());//接收财付通异步通知的URL		
		reqHandler.setParameter("bank_type", StringUtils.isBlank(param.getBankType())?TenpayConstants.DEFAULT_BANK_TYPE:param.getBankType());//银行类型(中介担保时此参数无效)
		reqHandler.setParameter("spbill_create_ip",request.getRemoteAddr());   //用户的公网ip，不是商户服务器IP
		reqHandler.setParameter("fee_type", TenpayConstants.DEFAULT_FEE_TYPE);//币种，1人民币 目前只支持人民币
		String subject =  CommonStringUtils.subStringAndAppendValue(param.getSubject(), 32, StringUtils.EMPTY);
		reqHandler.setParameter("subject",subject);//商品名称(中介交易时必填)最大32个字符
		reqHandler.setParameter("body",subject);//NND 财付通一定要传商品描述 这里描述就用标题
		//系统可选参数
		reqHandler.setParameter("sign_type", TenpayConstants.DEFAULT_SIGN_TYPE);//签名类型,默认：MD5
		reqHandler.setParameter("service_version", "1.0");//版本号，默认为1.0
		reqHandler.setParameter("input_charset", TenpayConstants.DEFAULT_ENCODE);//字符编码
		reqHandler.setParameter("sign_key_index", "1");//多密钥支持的密钥序号，默认1 

		//请求的url
		try {
			String requestUrl = reqHandler.getRequestURL();
			return requestUrl;
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}
	
	/**
	 * 校验腾讯返回结果是否合法
	 * @param resHandler
	 * @return
	 */
	public static boolean verifyResult(ResponseHandler resHandler) throws Exception{
		//判断签名
		if(resHandler.isTenpaySign()) {
			//通知id
			String notify_id = resHandler.getParameter("notify_id");
			//创建请求对象
			RequestHandler queryReq = new RequestHandler(null, null);
			//通信对象
			TenpayHttpClient httpClient = new TenpayHttpClient();
			//应答对象
			ClientResponseHandler queryRes = new ClientResponseHandler();
			//通过通知ID查询，确保通知来至财付通
			queryReq.init();
			queryReq.setKey(PayConfigHolder.getTenpayKey());
			queryReq.setGateUrl(TenpayConstants.TENPAY_VERFIY_URL);
			queryReq.setParameter("partner", PayConfigHolder.getTenpayPartner());
			queryReq.setParameter("notify_id", notify_id);
			//通信对象 超时秒数
			httpClient.setTimeOut(6);
			//设置请求内容
			httpClient.setReqContent(queryReq.getRequestURL());
			//System.out.println("验证ID请求字符串:" + queryReq.getRequestURL());
			
			// 后台调用
			if (httpClient.call()) {
				// 设置结果参数
				queryRes.setContent(httpClient.getResContent());
				// System.out.println("验证ID返回字符串:" +
				// httpClient.getResContent());
				queryRes.setKey(PayConfigHolder.getTenpayKey());
				// 获取id验证返回状态码，0表示此通知id是财付通发起
				String retcode = queryRes.getParameter("retcode");
				// 支付结果
				String trade_state = resHandler.getParameter("trade_state");
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
				String trade_mode = resHandler.getParameter("trade_mode");
				// 判断签名及结果
				if (queryRes.isTenpaySign() && "0".equals(retcode)) {
					if ("1".equals(trade_mode) && "0".equals(trade_state)) { // 即时到账
							// 财付通调用成功后 给财付通发送成功通知 财付通就不再回调了
							// resHandler.sendToCFT("success"); //到程序订单状态改成功后再发
							return true;
						} else {
							resHandler.sendToCFT("fail");
							return false;
						}
					}
				}
		}
		return false;
	}
}
