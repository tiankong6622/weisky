package org.javafans.partner.sms;

/**
 * 支付宝相关常量
 * @author ChenJunhui
 */
public interface SmsConstants {
	
    public final static String TRUE = "true";
	
	public final static String FALSE = "false";
	
	public interface ENCODE {
		public static final String UTF_8 = "UTF-8";
		public static final String ISO_8859_1 = "ISO-8859-1";
		public static final String GB2312="GB2312";
	}
	
	public interface Method{
		public static final String POST= "POST";
		public static final String GET= "GET";
	}
	
	
}
