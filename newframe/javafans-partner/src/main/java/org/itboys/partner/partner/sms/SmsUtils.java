package org.itboys.partner.partner.sms;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.itboys.partner.partner.SmsConfigHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 短信发送工具
 * 
 * @author niujh
 * 
 */
public  class SmsUtils {
	
	private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);
	

	public static boolean send(String mobile, String smsContent) {
		try {
			String gbkString = smsContent;
			String urlvalue =   SmsConfigHolder.getSmsUrl();
			StringBuilder params = new StringBuilder();
			params.append("uid=" +SmsConfigHolder.getSmsUid());
			params.append("&pwd=" +SmsConfigHolder.getSmsPwd());
			params.append("&rev=" + mobile);
			params.append("&msg=" + gbkString + " "+SmsConfigHolder.getSmsDomain());
			params.append("&sdt=");
			params.append("&snd=" + SmsConfigHolder.getSmsSnd());
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod(SmsConstants.Method.POST);
			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			urlConnection.connect();
			// post信息 ,这步很重要,不然就乱码
			OutputStream os = urlConnection.getOutputStream();
			os.write(params.toString().getBytes(SmsConstants.ENCODE.GB2312));
			os.close();

			// // 获取该动态链接响应的状态码
			int iHttpResult = urlConnection.getResponseCode();
			// 判断该动态链接的响应是否能正确连接
			if (iHttpResult == HttpURLConnection.HTTP_OK)
				return true;// urlvalue + SmsConstants.FALSE;
			else{
				logger.error("网络连接错误,{}",iHttpResult);
			}
		} catch (Exception e) {
			logger.error("send sms fail:",e);
		}
		return false;
	}
	
	

}
