package org.itboys.commons;

import java.math.BigDecimal;

import org.itboys.commons.dto.ResultPageDOHelper;
import org.itboys.commons.utils.ajax.AjaxUtils;

/**
 * 通用常量
 * @author ChenJunhui
 *
 */
public interface CommonConstants {

	public interface CONTENT_TYPE{
		public static final String TEXT_TYPE = "text/plain";
		public static final String JSON_TYPE = "application/json";
		public static final String XML_TYPE = "text/xml";
		public static final String HTML_TYPE = "text/html";
		public static final String JS_TYPE = "text/javascript";
		public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	}
	
	public interface DATE{
		public static final String  FORMAT_NO_STR = "yyyyMMdd";
		public static final String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
		public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
		public static final String FORMAT_YYYY_MM_dd = "yyyy-MM-dd";
		public static final String FORMAT_YYYY_ZH_MM_ZH_dd = "yyyy年MM月dd日";
		public static final String FORMAT_YYYY_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
		public static final String FORMAT_YYYY_MM_dd_HH_mm_ss_S = "yyyy-MM-dd HH:mm:ss.S";
		public static final String FORMAT_MMDDYYYY = "MM/dd/yyyy";
		public static final String[] PARSE_FORMAT_ARR = new String[]{FORMAT_MMDDYYYY,FORMAT_DEFAULT,FORMAT_YYYY_MM_dd,FORMAT_YYYY_MM_dd_HH_mm,FORMAT_YYYY_MM_dd_HH_mm_ss_S,FORMAT_YYYY_ZH_MM_ZH_dd};
		public static final String[] weekDaysName={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	}
	
	public interface REGX{
		public static final String REG_MOBILE = "^((\\(\\d{3}\\))|(\\d{3}\\-))?((13)|(14)|(15)|(18))\\d{9}$";//电话号码正则表达式
		public static final String REG_EMAIL = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";//邮箱正则表达式
		public static final String REG_CHAR = "^[A-Za-z0-9]+$";//标准字符正则表达式
		public static final String REG_CN = "^(([\\u4e00-\\u9fa5])|([A-Za-z0-9]))+$";//中文正则表达式
	}
	
	public interface ENCODE {
		public static final String UTF_8 = "UTF-8";
		public static final String ISO_8859_1 = "ISO-8859-1";
		public static final String GB2312="GB2312";
	}
	
	/**
	 * 图片文件的类型
	 */
	public static final String[] imgFileTypes = new String[] { "image/pjpeg",
		"image/bmp", "image/gif", "image/jpg", "image/png", "image/jpeg" };
	
	public static final BigDecimal ZERO= new BigDecimal(0);
	
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
}
