package org.itboys.partner.partner.yeepay;

import java.io.StringReader;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.itboys.commons.utils.encryption.Digests;
import org.itboys.commons.utils.io.IOUtils;
import org.itboys.partner.partner.yeepay.pay.YeepayBackParam;
import org.itboys.partner.partner.yeepay.pay.YeepayResponseParam;

import com.google.common.collect.Lists;

/**
 * 易宝相关
 * @author ChenJunhui
 *
 */
public class YeepayUtils {
	
	/**
	 * 确保类路径下 有yeepayBackParam.xml 内容如下
	 * <?xml version="1.0" encoding="UTF-8"?>
		<COD-MS>
			<SessionHead>
				<Version>{Version}</Version>
				<ServiceCode>{ServiceCode}</ServiceCode>
				<TransactionID>{TransactionID}</TransactionID>
				<SrcSysID>{SrcSysID}</SrcSysID>
				<DstSysID>{DstSysID}</DstSysID>
				<Result_Code>{Result_Code}</Result_Code>
				<Result_Msg>{Result_Msg}</Result_Msg>
				<Resp_Time>{Resp_Time}</Resp_Time>
				<ExtendAtt><Order_No>{Order_No}</Order_No></ExtendAtt>
				<HMAC>{HMAC}</HMAC>
			</SessionHead>
		</COD-MS>
	 */
	private static String BACK_PARAM_XML = IOUtils.inputStream2String(Thread.currentThread().getContextClassLoader().getResourceAsStream("yeepayBackParam.xml"));
	
	/**
	 * 响应xml
	 * @param yrp 请求参数组装
	 * @param sercet 密钥
	 * @param successCode 2:成功 4:报文验证失败 10:用户名密码错误 11:没有该用户 好搓 没有程序处理失败的code
	 * @param resultMsg 消息
	 */
	public static String getReponseXml(YeepayResponseParam yrp,String sercet,int successCode,String resultMsg){
		String xml = BACK_PARAM_XML;
		xml=xml.replace("{Version}", yrp.getSessionHead().getVersion());
		xml=xml.replace("{ServiceCode}", yrp.getSessionHead().getServiceCode());
		xml=xml.replace("{TransactionID}", yrp.getSessionHead().getTransactionID());
		xml=xml.replace("{SrcSysID}", yrp.getSessionHead().getSrcSysID());
		xml=xml.replace("{DstSysID}", yrp.getSessionHead().getDstSysID());
		xml=xml.replace("{Result_Code}", String.valueOf(successCode));
		xml=xml.replace("{Result_Msg}", resultMsg);
		xml=xml.replace("{Resp_Time}", String.valueOf(System.currentTimeMillis()));
		xml=xml.replace("{Order_No}", yrp.getSessionBody().getOrderNo());
		xml=xml.replace("{HMAC}", getMd5Sign(xml,sercet));
		return xml;
	}
	
	public static void addElement(Element element,String key,String value){
		
	}
	

	@SuppressWarnings("unchecked")
	public static YeepayBackParam payResponseToYeepayBackParam(String xml){
		SAXReader reader = new SAXReader();
		YeepayBackParam ybp = new YeepayBackParam();
		try{
			Document document = reader.read(new StringReader(xml));
			Element element = (Element) document.selectSingleNode("/COD-MS/SessionHead");
			ybp.setVersion(getElementValue(element,"Version"));
			ybp.setServiceCode(getElementValue(element,"ServiceCode"));
			ybp.setTransactionID(getElementValue(element,"TransactionID"));
			ybp.setSrcSysID(getElementValue(element,"SrcSysID"));
			ybp.setDstSysID(getElementValue(element,"DstSysID"));
			ybp.setResultCode(getElementValue(element,"Result_Code"));
			String respTime = getElementValue(element,"Resp_Time");
			ybp.setRespTime(NumberUtils.isDigits(respTime)?Long.parseLong(respTime):null);
			ybp.setHMAC(getElementValue(element,"HMAC"));
			List<Element> orderNoEles = element.selectNodes("ExtendAtt/Order_No");
			List<String> orderNos = Lists.newArrayListWithCapacity(orderNoEles.size());
			for(Element ele:orderNoEles){
				orderNos.add(ele.getText());
			}
			ybp.setOrderNos(orderNos);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return ybp;
	}
	
	public static String getElementValue(Element element,String properties){
		Element pe = element.element(properties);
		return pe==null?null:pe.getText();
	}
	
	/**
	 * 回调过来xml是否验证通过
	 * 易宝copy过来的代码 
	 * @return true通过,false失败
	 */
	public static boolean isValidate(String callback,String secKey) {
		String oldMd5 = "";
		// 需要加密的字符串
		String md5String = "";
		// 本次传送xml的md5
		String md5token = "";
		md5String = callback.substring(callback.indexOf("<COD-MS>") + 8,callback.indexOf("</COD-MS>"));
		// 把hmac节点过滤点
		oldMd5 = callback.substring(callback.indexOf("<HMAC>"), callback.indexOf("</HMAC>") + 7);
		md5String = md5String.replace(oldMd5, "");
		// 生成md5
 	   /*Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);
		md5token = md5.encodePassword(md5String + secKey, null);*/
		md5token =  Digests.md5(md5String + secKey);
		// log.info("加密的串：" + md5String + secKey);
		// log.info("老的MD5:" + oldMd5 + "  ,新的MD5：" + md5token);
		if (oldMd5.toUpperCase().indexOf(md5token.toUpperCase()) >= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * xml md5
	 * @param xml
	 * @param secKey
	 * @return
	 */
	public static String getMd5Sign(String xml,String secKey){
		String md5String = xml.substring(xml.indexOf("<COD-MS>") + 8,xml.indexOf("</COD-MS>"));
		String oldMd5 = xml.substring(xml.indexOf("<HMAC>"), xml.indexOf("</HMAC>") + 7);
		md5String = md5String.replace(oldMd5, "");
		return  Digests.md5(md5String + secKey);
	}

}
