package org.itboys.partner.partner.unionpay;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.partner.com.unionpay.upop.sdk.QuickPayConf;
import org.itboys.partner.com.unionpay.upop.sdk.QuickPayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 银联交易相关帮助类
 * @author ChenJunhui
 *
 */
public class UnionPayUtils {

	private static   Logger logger = LoggerFactory.getLogger(UnionPayUtils.class);
	
	/**
	 * 获取跳转银联支付的html代码
	 */
	public static String getToPayHtml(UnionPayParam unionPayParam){
		String[] payArr = unionPayParam.toUnionPayArray();
		//跳转到银联页面支付
		String html = new QuickPayUtils().createPayHtml(payArr, QuickPayConf.signType);
		return html;
	}
	
	/**
	 * 银联返回结果校验封装
	 * @param request
	 * @return
	 */
	public static  UnionPayBackResult validateRequest(HttpServletRequest request){
		try {
			request.setCharacterEncoding(QuickPayConf.charset);
		} catch (UnsupportedEncodingException e) {
			logger.error("setCharacterEncoding fail ",e);
		}
		String[] resArr = new String[QuickPayConf.notifyVo.length]; 
		for(int i=0;i<QuickPayConf.notifyVo.length;i++){
			resArr[i] = request.getParameter(QuickPayConf.notifyVo[i]);
		}
		String signature = request.getParameter(QuickPayConf.signature);
		String signMethod = request.getParameter(QuickPayConf.signMethod);
		boolean signatureCheck = new QuickPayUtils().checkSign(resArr, signMethod, signature);
		UnionPayBackResult upbr = new UnionPayBackResult();
		upbr.setResArr(resArr);
		upbr.setSignatureCheck(signatureCheck);
		return upbr;
	}
	
	/**
	 * 生成响应页面
	 * @param response
	 * @param upp
	 */
	public static  void doResponseUnionPay(HttpServletResponse response,UnionPayParam upp) {
		try {
			String html = UnionPayUtils.getToPayHtml(upp);
			response.setContentType("text/html;charset="+QuickPayConf.charset);   
			response.setCharacterEncoding(QuickPayConf.charset);
			System.out.println(html);
			response.getWriter().write(html);
		} catch (Exception e) {
			logger.error("doResponseUnionPay fail",e);
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
