package org.javafans.common.utils.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.javafans.common.constants.CommonConstants;


/**
 * 正则表达式相关的静态类
 * @author ChenJunHui
 */
public abstract class RegUtils {
	
	/**
	 * 手机号码的验证 13 15 18 打头的都算
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobile){ 
			if(StringUtils.isBlank(mobile)){
				return false;
			}
			Pattern p = Pattern.compile(CommonConstants.REGX.REG_MOBILE); 
			Matcher m = p.matcher(mobile); 
			return m.matches(); 
	} 
	
	/**
	 * 邮箱的验证
	 * @param emails
	 * @return
	 */
	public static boolean isEmail(String emails){
		if(StringUtils.isBlank(emails)){
			return false;
		}
		Pattern p = Pattern.compile(CommonConstants.REGX.REG_EMAIL); 
		Matcher m = p.matcher(emails); 
		return m.matches();  
	}
	
	/**
	 * 判断是不是由 数字和字母组成的数字
	 * @param chars
	 * @return
	 */
	public static boolean isChar(String chars){
		if(StringUtils.isBlank(chars)){
			return false;
		}
		Pattern p = Pattern.compile(CommonConstants.REGX.REG_CHAR); 
		Matcher m = p.matcher(chars); 
		return m.matches();
	}
	
	/**
	 * 判断是否由中文 英文 数字组成的
	 * @param str
	 * @return
	 */
	public static boolean isCharOrCn(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		Pattern p = Pattern.compile(CommonConstants.REGX.REG_CN); 
		Matcher m = p.matcher(str); 
		return m.matches();
	}
	
	/**
	 * 判断是否是length位数字的字符串
	 * @param zipCode
	 * @param length
	 * @return
	 */
	public static boolean isDigits(String str,int length) {
		return (NumberUtils.isDigits(str) && (str.length() == length));
	}

	public  static void main(String[] args){
		Pattern p = Pattern.compile("/aaa/*"); 
		Matcher m = p.matcher("/aaa/sdfsdf");
		System.out.println(m.matches());
		System.out.println(isMobile("13656650713"));
		System.out.println(isEmail("xiaobai_totti@163.com"));
		System.out.println(isChar("07937788688"));
		System.out.println(isCharOrCn("s肯3發"));
		System.out.println(StringEscapeUtils.escapeJavaScript("© 2011 杭州线团网络科技有限公司 xiantuan2.com 浙ICP备11018602号-3"));
	}
}
