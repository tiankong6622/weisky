package com.hz.yisheng.oa.workflow.exception;

/**
 * 流程异常
 * @author WeiSky
 *
 */
public class FlowException extends RuntimeException{

	private static final long serialVersionUID = -7104187880954582297L;
	
	public static final int START_FLOW_FAIL=-99;//启动流程失败
	public static final int EXECUTE_FLOW_NODE_FAIL=-100;//流程节点处理失败
	public static final int DIRTY_DATA=-999;//脏数据
	public static final int NO_FLOW=0;//流程找不到
	public static final int CAN_NOT_FIND_START_NODE=-1;//流程开始节点找不到
	public static final int CAN_NOT_FIND_NODE=-2;//节点找不到
	public static final int CAN_NOT_FIND_TRANSITION=-3;//流转线找不到
	public static final int CAN_NOT_FIND_DEAL_BEAN=-4;//处理bean找不到
	
	private int errorCode;
	private String flowname;
	private Long flowId;

    public FlowException(String message) {
        super(message);
    }

    public FlowException(int errorCode,Long flowId) {
  	  super("flowException errorCode="+errorCode+",flowId="+flowId);
  	  this.errorCode=errorCode;
  	  this.flowId=flowId;
 }
    
    public FlowException(int errorCode,String flowname) {
    	  super("flowException errorCode="+errorCode+",flowname="+flowname);
    	  this.errorCode=errorCode;
    	  this.flowname=flowname;
   }

	public int getErrorCode() {
		return errorCode;
	}

	public String getFlowname() {
		return flowname;
	}

	public Long getFlowId() {
		return flowId;
	}
}
