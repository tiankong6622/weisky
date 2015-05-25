package com.hz.crf.model;
/**
 * 众筹常量定义
 * 
 * @author weisky
 *
 * May 28, 2015
 */
public interface CrowdConstants {

	/*
	 * 手机验证码
	 */
	public interface MobiCode{
		public static final int MOBI_CODE_RESG = 1;//注册
		public static final int MOBI_CODE_FORGET = 2;//忘记密码  密码找回
		public static final int MOBI_CODE_UPADTE = 3;//修改密码
	}
	
	/*
	 * 项目类型
	 */
	public interface Product{
		public static final int TYPE_ADMIN = 1;//后台编辑发布的
		public static final int TYPE_MOBI = 2;//会员提交的项目  也就是从手机端提交的
	}
	
	/*
	 * 默认收货地址
	 */
	public interface Address{
		public static final int DEFAULT_YES = 1;//是
		public static final int DEFAULT_NO = 0;//否
	}
	
	/*
	 * 订单相关常量
	 */
	public interface Order{
		public static final int PAY_TYPE_WEIXIN = 1;//微信支付
		public static final int PAY_TYPE_ALIPAY = 2;//支付宝支付
		public static final int PAY_TYPE_BANK = 3;//网银支付
		public static final int PAY_TYPE_OTHER = 4;//其他支付方式
		
		public static final int BUILD_SUCCESS = 1;//创建订单成功，等待支付
		public static final int PAY_SUCCESS = 2;//支付成功，等待发货
		public static final int SEND_SUCCESS = 3;//已发货。等待确认收货
		public static final int RECEIVE_SUCCCESS = 4;//收货确认成功，订单结束
		
		public static final int PAY_FAIL = 0;//订单支付失败
	}
}
