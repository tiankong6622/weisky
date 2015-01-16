package org.javafans.common.utils.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 过滤HTML相关
 * @author ShaoHuijun
 */
public abstract class FilterHtmlUtils {

	// 定义script的正则表达式
	public static final String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
	
	// 定义style的正则表达式
	public static final String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
	
	// 定义iframe的正则表达式
	public static final String regEx_iframe = "<[\\s]*?iframe[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?iframe[\\s]*?>";
	
	public static String getFilterHtmlText(String text){
		if(StringUtils.isNotBlank(text)){
			Pattern p = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(text);
			text = m.replaceAll(StringUtils.EMPTY); // 过滤script标签
			if(StringUtils.isNotBlank(text)){
				p = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
				m = p.matcher(text);
				text = m.replaceAll(StringUtils.EMPTY); // 过滤css标签
			}
			if(StringUtils.isNotBlank(text)){
				p = Pattern.compile(regEx_iframe, Pattern.CASE_INSENSITIVE);
				m = p.matcher(text);
				text = m.replaceAll(StringUtils.EMPTY); // 过滤iframe标签
			}
		}
		return text;
	}
}
