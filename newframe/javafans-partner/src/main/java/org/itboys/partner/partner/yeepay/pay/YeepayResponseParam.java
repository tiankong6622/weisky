package org.itboys.partner.partner.yeepay.pay;

import java.io.StringReader;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.itboys.partner.partner.yeepay.YeepayUtils;

/**
 * 易宝响应参数
 * @author ChenJunhui
 */
public class YeepayResponseParam {
	
	
	private SessionHead sessionHead;
	private SessionBody sessionBody;
	
	public YeepayResponseParam(String xml){
		SAXReader reader = new SAXReader();
		SessionHead sh = new SessionHead();
		try{
			Document document = reader.read(new StringReader(xml));
			//解析请求头
			Element element = (Element) document.selectSingleNode("/COD-MS/SessionHead");
			sh.setVersion(YeepayUtils.getElementValue(element,"Version"));
			sh.setServiceCode(YeepayUtils.getElementValue(element,"ServiceCode"));
			sh.setTransactionID(YeepayUtils.getElementValue(element,"TransactionID"));
			sh.setSrcSysID(YeepayUtils.getElementValue(element,"SrcSysID"));
			sh.setDstSysID(YeepayUtils.getElementValue(element,"DstSysID"));
			String reqTime = YeepayUtils.getElementValue(element,"ReqTime");
			sh.setReqTime(NumberUtils.isDigits(reqTime)?Long.parseLong(reqTime):null);
			sh.setHMAC(YeepayUtils.getElementValue(element,"HMAC"));
			//解析请求尾
			element = (Element) document.selectSingleNode("/COD-MS/SessionBody/ExtendAtt");
			SessionBody sb = new SessionBody();
			sb.setOrderNo(YeepayUtils.getElementValue(element,"Order_No"));
			sb.setReceiverNo(YeepayUtils.getElementValue(element,"Receiver_No"));
			sb.setBankCardNo(YeepayUtils.getElementValue(element,"BankCard_No"));
			sb.setBankOrderNo(YeepayUtils.getElementValue(element,"BankOrder_No"));
			sb.setOrderAmt(YeepayUtils.getElementValue(element,"Order_AMT"));
			sb.setPayType(YeepayUtils.getElementValue(element,"Pay_Type"));
			sb.setYeepayOrderId(YeepayUtils.getElementValue(element,"Yeepay_OrderId"));
			sb.setChequeNo(YeepayUtils.getElementValue(element,"Cheque_No"));
			sb.setPayDetail(YeepayUtils.getElementValue(element,"Pay_Detail"));
			
			this.setSessionHead(sh);
			this.setSessionBody(sb);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 易宝请求体
	 * @author ChenJunhui
	 *
	 */
	public class SessionBody{
		private String orderNo;
		private String receiverNo;
		private String bankCardNo;
		private String bankOrderNo;
		private String orderAmt;
		private String payType;
		private String yeepayOrderId;
		private String chequeNo;
		private String payDetail;
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public String getBankCardNo() {
			return bankCardNo;
		}
		public void setBankCardNo(String bankCardNo) {
			this.bankCardNo = bankCardNo;
		}
		public String getBankOrderNo() {
			return bankOrderNo;
		}
		public void setBankOrderNo(String bankOrderNo) {
			this.bankOrderNo = bankOrderNo;
		}
		public String getOrderAmt() {
			return orderAmt;
		}
		public void setOrderAmt(String orderAmt) {
			this.orderAmt = orderAmt;
		}
		public String getPayType() {
			return payType;
		}
		public void setPayType(String payType) {
			this.payType = payType;
		}
		public String getYeepayOrderId() {
			return yeepayOrderId;
		}
		public void setYeepayOrderId(String yeepayOrderId) {
			this.yeepayOrderId = yeepayOrderId;
		}
		public String getChequeNo() {
			return chequeNo;
		}
		public void setChequeNo(String chequeNo) {
			this.chequeNo = chequeNo;
		}
		public String getPayDetail() {
			return payDetail;
		}
		public void setPayDetail(String payDetail) {
			this.payDetail = payDetail;
		}
		public String getReceiverNo() {
			return receiverNo;
		}
		public void setReceiverNo(String receiverNo) {
			this.receiverNo = receiverNo;
		}
		public String toString(){
			return ToStringBuilder.reflectionToString(this);
		}
	}

	/**
	 * 易宝请求头
	 * @author ChenJunhui
	 *
	 */
	public class SessionHead{
		
		private String version;
		private String serviceCode;
		private String transactionID;
		private String srcSysID;
		private String dstSysID;
		private Long reqTime;
		private String HMAC;
		
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getServiceCode() {
			return serviceCode;
		}
		public void setServiceCode(String serviceCode) {
			this.serviceCode = serviceCode;
		}
		public String getTransactionID() {
			return transactionID;
		}
		public void setTransactionID(String transactionID) {
			this.transactionID = transactionID;
		}
		public String getSrcSysID() {
			return srcSysID;
		}
		public void setSrcSysID(String srcSysID) {
			this.srcSysID = srcSysID;
		}
		public String getDstSysID() {
			return dstSysID;
		}
		public void setDstSysID(String dstSysID) {
			this.dstSysID = dstSysID;
		}
		public String getHMAC() {
			return HMAC;
		}
		public void setHMAC(String hMAC) {
			HMAC = hMAC;
		}
		public String toString(){
			return ToStringBuilder.reflectionToString(this);
		}
		public Long getReqTime() {
			return reqTime;
		}
		public void setReqTime(Long reqTime) {
			this.reqTime = reqTime;
		}
	}
	
	public SessionHead getSessionHead() {
		return sessionHead;
	}

	public void setSessionHead(SessionHead sessionHead) {
		this.sessionHead = sessionHead;
	}

	public SessionBody getSessionBody() {
		return sessionBody;
	}

	public void setSessionBody(SessionBody sessionBody) {
		this.sessionBody = sessionBody;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
