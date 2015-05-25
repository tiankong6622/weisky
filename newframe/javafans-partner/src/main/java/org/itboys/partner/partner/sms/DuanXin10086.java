package org.itboys.partner.partner.sms;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.itboys.commons.utils.number.ToNumberUtils;
import org.itboys.partner.partner.HttpHelper;

/**
 * 短信10086
 * @author ChenJunhui
 *    动态配置项可以在spring的bean里自己配置哈
 *   <bean id="duanXin10086" class="org.javafans.partner.sms.DuanXin10086">
			<property name="url"  value="http://www.duanxin10086.com/sms.aspx" />
			<property name="userid"  value="7900" />
			<property name="account"  value="k1146" />
			<property name="password"  value="*******" />
			<property name="checkcontent"  value="0" />
		</bean>
 */
public class DuanXin10086 {
	
	/*************以下这些属性配置在spring的bean里************/
	private String url; //发短信的url
	private String userid;//发短信的企业账户ID
	private String account;//发短信的账号
	private String password;//密码
	private String checkcontent;//当设置为1时表示需要检查，默认0为不检查 是否检查内容包含非法关键字
	
	/**
	 * 批量发送短信
	 * @param mobiles 要发送的手机号
	 * @param content 发送的内容
	 * @param taskName 本次任务 描述100个字以内  可以为空
	 * @return
	 */
	 public Result10086 doSend(List<String> mobiles,String content,String taskName){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userid", userid);
		param.put("account", account);
		param.put("password", password);
		param.put("mobile", StringUtils.join(mobiles, ","));
		param.put("content", content);
		param.put("sendTime", null);// 为空表示立即发送，定时发送格式2010-10-24 09:08:10  以后需要定时发送的话 再小小重构一下
		param.put("action", "send");
		param.put("checkcontent", checkcontent);
		param.put("taskName", taskName);
		param.put("countnumber", mobiles.size());
		param.put("mobilenumber", mobiles.size());
		param.put("telephonenumber", "0");
		try{
			String xml = HttpHelper.doPostWithUtf8(url, param);
			Result10086 result = new Result10086();
			if(!HttpHelper.FAIL.equals(xml)){
				SAXReader reader = new SAXReader();
				Document document = reader.read(new StringReader(xml));
				Element element = (Element) document.selectSingleNode("/returnsms");
				result.setMessage(getElementValue(element,"message"));
				result.setRemainpoint(ToNumberUtils.getBigDecimal(getElementValue(element,"remainpoint")));
				result.setReturnstatus((getElementValue(element,"returnstatus")));
				result.setSuccessCounts(ToNumberUtils.getIntegerValue(getElementValue(element,"successCounts")));
				result.setTaskID(getElementValue(element,"taskID"));
			}
			return result;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	 }
	 
	 /**
	  * 单个发送短信
	 * @param mobile 要发送的手机号
	 * @param content 发送的内容
	 * @param taskName 本次任务 描述100个字以内  可以为空
	  * @return
	  */
	 public Result10086 doSend(String mobile,String content,String taskName){
		 List<String> mobiles = new ArrayList<String>(1);
		 mobiles.add(mobile);
		 return doSend(mobiles, content, taskName);
	 }
	 
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheckcontent() {
		return checkcontent;
	}
	public void setCheckcontent(String checkcontent) {
		this.checkcontent = checkcontent;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public static String getElementValue(Element element,String properties){
		Element pe = element.element(properties);
		return pe==null?null:pe.getText();
	}
}
