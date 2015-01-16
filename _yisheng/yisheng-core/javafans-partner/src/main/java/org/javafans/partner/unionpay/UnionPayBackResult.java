package org.javafans.partner.unionpay;

/**
 * 银联回调结果 几个重要的参数验证
 * @author ChenJunhui
 *
 */
public class UnionPayBackResult {
	private boolean signatureCheck;//校验签名是否正确 true:正确 false:不正确
	private String[] resArr;//返回结果
	
	/**
	 * 是否成功
	 * @return
	 */
	public boolean isSuccess() {
		return "00".equals(resArr[10]);
	}
	
	/**
	 * 签名是否成功
	 * @return
	 */
	public boolean isSignatureCheck() {
		return signatureCheck;
	}
	
	/**
	 * 这边的订单ID
	 * @return
	 */
	public Long getOrderId(){
		return Long.parseLong( getOrderNumber());
	}
	
	/**
	 * 银联的QID 第二次付款有用
	 * @return
	 */
	public String qId(){
		return resArr[9];
	}
	
	/**
	 * 原始订单号 可能带了 0000001之类的
	 * @return
	 */
	public String getOrderNumber(){
		return resArr[8];
	}
	
	
	public String getErrorMessage(){
		if(isSuccess()){
			return null;
		}
		return resArr[11];
	}
	
	public void setSignatureCheck(boolean signatureCheck) {
		this.signatureCheck = signatureCheck;
	}
	
	public String[] getResArr() {
		return resArr;
	}
	public void setResArr(String[] resArr) {
		this.resArr = resArr;
	}
}
