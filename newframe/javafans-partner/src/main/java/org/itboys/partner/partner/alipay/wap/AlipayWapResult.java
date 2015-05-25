package org.itboys.partner.partner.alipay.wap;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 手机H5交易地址
 * @author ChenJunhui
 *
 */
public class AlipayWapResult {

	private boolean verify_result=false;//验证成功或失败的标识
	private String trade_status;//交易状态
	private String out_trade_no;//外部交易订单号 就是本系统的订单
	private String trade_no;//支付宝交易订单号
	private String notify_data;//异步通知数据
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getNotify_data() {
		return notify_data;
	}
	public void setNotify_data(String notify_data) {
		this.notify_data = notify_data;
	}
	
	public boolean isVerify_result() {
		return verify_result;
	}
	public void setVerify_result(boolean verify_result) {
		this.verify_result = verify_result;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
