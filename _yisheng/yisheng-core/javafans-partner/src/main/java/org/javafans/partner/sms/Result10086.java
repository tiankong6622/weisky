package org.javafans.partner.sms;

import java.math.BigDecimal;

/**
 * 短信10086的发送结果
 * @author ChenJunhui
 *
 */
public class Result10086 {

	public static final String SUCCESS="Success";
	public static final String FAILED="Faild";
	
	private String returnstatus=FAILED;//发送状态 Success 成功  Faild 失败
	private String message;//发送信息 成功ok 失败错误消息
	private BigDecimal remainpoint;//发完后还剩多少钱
	private String taskID;//10086的任务ID
	private Integer successCounts;//发送成功的数量
	
	/**
	 * 判断是否发送成功
	 * @return
	 */
	public boolean isSuccess(){
		return SUCCESS.equals(returnstatus);
	}
	
	public String getReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BigDecimal getRemainpoint() {
		return remainpoint;
	}
	public void setRemainpoint(BigDecimal remainpoint) {
		this.remainpoint = remainpoint;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public Integer getSuccessCounts() {
		return successCounts;
	}
	public void setSuccessCounts(Integer successCounts) {
		this.successCounts = successCounts;
	}
	
}
