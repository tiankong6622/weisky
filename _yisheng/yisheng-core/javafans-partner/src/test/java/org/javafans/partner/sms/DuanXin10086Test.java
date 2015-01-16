package org.javafans.partner.sms;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.javafans.partner.HttpHelper;
import org.junit.Test;

public class DuanXin10086Test {

	@Test
	public void testSend(){
		
		DuanXin10086 duanXin10086 = new DuanXin10086();
		duanXin10086.setUrl("http://www.duanxin10086.com/sms.aspx");
		duanXin10086.setAccount("k1146");
		duanXin10086.setUserid("7900");
		duanXin10086.setPassword("123");
		duanXin10086.setCheckcontent("0");
		Result10086 result = duanXin10086.doSend("18610698805", "我是圣代孙太后 你们都给我过来 哇哈哈哈哈", "孙太后测试");
		System.out.println(ToStringBuilder.reflectionToString(result));
		/*Map<String, Object> param = new HashMap<String, Object>();
		param.put("userid", "7900");
		param.put("account", "k1146");
		param.put("password", "123");
		param.put("mobile", "18610698805");
		param.put("content", "圣代网络孙太后 哇哈哈哈哈!");
		param.put("sendTime", null);
		param.put("action", "send");
		param.put("checkcontent", "0");
		param.put("taskName", "孙老师测试发短信");
		param.put("countnumber", "1");
		param.put("mobilenumber", "1");
		param.put("telephonenumber", "0");
		
		String sendValue = HttpHelper.doPostWithUtf8("http://www.duanxin10086.com/sms.aspx", param);
		System.out.println(sendValue);*/
	}
}
