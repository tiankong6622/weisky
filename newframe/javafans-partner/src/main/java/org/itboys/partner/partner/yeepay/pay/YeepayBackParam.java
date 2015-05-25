package org.itboys.partner.partner.yeepay.pay;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 易宝线下pos机器支付订单成功回调参数 回调xml大概如下
 * <?xmlversion="1.0" encoding="UTF-8"?>
	<COD-MS>
		<SessionHead>
			    <Version>1.0</Version>
			    <ServiceCode>COD603</ServiceCode>
				<TransactionID>NEPCOD603201106214068358793</TransactionID>
				<SrcSysID>yeepay</SrcSysID>
				<DstSysID>NEP</DstSysID>
				<Result_Code>2</Result_Code>
				<Result_Msg>成功</Result_Msg>
				<Resp_Time>20110621165622</Resp_Time>
				<ExtendAtt><Order_No>fx230077247cn</Order_No></ExtendAtt>
				<HMAC>981050D351DFD80FFF7F47C960928F54</HMAC>
		</SessionHead>
	</COD-MS>
 * @author ChenJunhui
 *
 */
public class YeepayBackParam {
	
	private String version;
	private String serviceCode;
	private String transactionID;
	private String srcSysID;
	private String dstSysID;
	private String resultCode;
	private Long respTime;
	private String HMAC;
	private List<String> orderNos;//订单号集合
	
	/**
	 * 是否成功
	 * @return
	 */
	public boolean isSuccess(){
		return "2".equals(serviceCode);
	}
	
	/**
	 * 获取订单号
	 * @return
	 */
	public String getOrderNo(){
		return orderNos==null||orderNos.isEmpty()?null:orderNos.get(0);
	}
	
	/**
	 * 假设订单号是订单ID的情况下可以用这个
	 * @return
	 */
	public Long getOrderId(){
		if(NumberUtils.isDigits(getOrderNo())){
			return Long.parseLong(getOrderNo());
		}
		return null;
	}
	
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
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public Long getRespTime() {
		return respTime;
	}
	public void setRespTime(Long respTime) {
		this.respTime = respTime;
	}
	
	public String getHMAC() {
		return HMAC;
	}
	public void setHMAC(String hMAC) {
		HMAC = hMAC;
	}
	public List<String> getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(List<String> orderNos) {
		this.orderNos = orderNos;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

}
