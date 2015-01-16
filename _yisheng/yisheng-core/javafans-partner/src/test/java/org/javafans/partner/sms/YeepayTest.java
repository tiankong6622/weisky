package org.javafans.partner.sms;

import java.io.InputStream;

import org.javafans.common.utils.io.IOUtils;
import org.javafans.partner.yeepay.YeepayUtils;
import org.javafans.partner.yeepay.pay.YeepayResponseParam;
import org.junit.Test;

public class YeepayTest {

	@Test
	public void test(){
	/*	InputStream stream =  Thread.currentThread().getContextClassLoader().getResourceAsStream("yibao.xml");
		String xml = IOUtils.inputStream2String(stream);
		System.out.println(xml);
		YeepayBackParam ybp = YeepayUtils.payResponseToYeepayBackParam(xml);
		System.out.println(ybp);*/
		
		InputStream stream =  Thread.currentThread().getContextClassLoader().getResourceAsStream("yibao2.xml");
		String xml = IOUtils.inputStream2String(stream);
		System.out.println("sign="+YeepayUtils.getMd5Sign(xml, "tlktwnzcw21sqm23td6lmfivsbxoys6jgrdj631q4h9uqnxa732wr5jhxwdw"));
		System.out.println("xxx="+YeepayUtils.isValidate(xml, "tlktwnzcw21sqm23td6lmfivsbxoys6jgrdj631q4h9uqnxa732wr5jhxwdw"));
		YeepayResponseParam yrp = new YeepayResponseParam(xml);
		System.out.println(yrp);
		System.out.println(YeepayUtils.getReponseXml(yrp, "tlktwnzcw21sqm23td6lmfivsbxoys6jgrdj631q4h9uqnxa732wr5jhxwdw", 2, "成功"));
	}
	
	
}
