package org.javafans.partner.alipay.wap;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

/**
 * 支付宝H5页面支付
 * @author ChenJunhui
 *
 */
public class AlipayWapService {
	
	/**
	 * 支付
	 * @param subject 商品标题 随便取
	 * @param orderNo 订单号或业务上的外部订单号 可以多个拼接
	 * @param externalId 外部订单ID
	 * @param fee 支付金额
	 */
	public static String getWapPayForm(String subject,String orderNo,String externalId,BigDecimal fee) throws Exception{
		Map<String, String> sParaTempToken = new HashMap<String, String>();
		sParaTempToken.put("service", "alipay.wap.trade.create.direct");
		sParaTempToken.put("partner", AlipayConfig.partner);
		sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
		sParaTempToken.put("sec_id", AlipayConfig.sign_type_md5);
		sParaTempToken.put("format", AlipayConfig.format);
		sParaTempToken.put("v", AlipayConfig.v);
		sParaTempToken.put("req_id", externalId);
		//请求业务参数详细 先字符串叠加了
		String req_dataToken = "<direct_trade_create_req><notify_url>" + AlipayConfig.notifyUrl 
				+ "</notify_url><call_back_url>" + AlipayConfig.callbackUrl + "</call_back_url><seller_account_name>" 
				+ AlipayConfig.sellerEmail + "</seller_account_name><out_trade_no>" + orderNo + "</out_trade_no><subject>" + subject + "</subject><total_fee>" 
				+ fee + "</total_fee><merchant_url>" + AlipayConfig.merchantUrl + "</merchant_url></direct_trade_create_req>";
		sParaTempToken.put("req_data", req_dataToken);
		
		//建立请求
		String sHtmlTextToken = AlipaySubmit.buildRequest(AlipayConfig.ALIPAY_GATEWAY_NEW,"", "",sParaTempToken);
		//URLDECODE返回的信息
		sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig.input_charset);
		//获取token
		String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
		//out.println(request_token);
		String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
		//必填
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
		//sParaTemp.put("service", "alipay.wap.trade.create.direct");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("sec_id", AlipayConfig.sign_type_md5);
		sParaTemp.put("format", AlipayConfig.format);
		sParaTemp.put("v", AlipayConfig.v);
		sParaTemp.put("req_data", req_data);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(AlipayConfig.ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");
		return sHtmlText;
	}

	/**
	 * 支付宝手机支付 验证异步回调并返回结果
	 * @return AlipayWapResult 
	 */
	public static AlipayWapResult notifyResult(HttpServletRequest request) throws Exception{
		AlipayWapResult result = new AlipayWapResult();
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			params.put(name, valueStr);
		}
		String notify_data = params.get("notify_data");
		result.setNotify_data(notify_data);
		Document doc_notify_data = DocumentHelper.parseText(notify_data);
		
		//商户订单号
		String out_trade_no = doc_notify_data.selectSingleNode( "//notify/out_trade_no" ).getText();
		result.setOut_trade_no(out_trade_no);
		//支付宝交易号
		String trade_no = doc_notify_data.selectSingleNode( "//notify/trade_no" ).getText();
		result.setTrade_no(trade_no);
		//交易状态
		String trade_status = doc_notify_data.selectSingleNode( "//notify/trade_status" ).getText();
		result.setTrade_status(trade_status);
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verifyNotify(params)){//验证成功
			if(trade_status.equals("TRADE_FINISHED")){
				result.setVerify_result(true);
			} else if (trade_status.equals("TRADE_SUCCESS")){
				result.setVerify_result(true);
			}
		}else{//验证失败
			result.setVerify_result(false);
		}
		return result;
	}
	
	
	/**
	 * 校验并返回同步结果
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public  static AlipayWapResult  callbackResult(HttpServletRequest request) throws Exception{
		AlipayWapResult wapResult = new AlipayWapResult();
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		wapResult.setOut_trade_no(out_trade_no);
		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		wapResult.setTrade_no(trade_no);
		//交易状态
		String result = new String(request.getParameter("result").getBytes("ISO-8859-1"),"UTF-8");
		wapResult.setTrade_status(result);
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verifyReturn(params);
		wapResult.setVerify_result(verify_result);
		return wapResult;
	}
}
