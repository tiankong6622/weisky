package com.hz.yisheng.oa.workflow.constants;


/**
 * 流程节点常量定义
 * @author WeiSky
 *
 */
public interface FlowNodeType {
 	
 	public static final String  direct="direct";//"转换连线"
 	public static final String  start="start";//"开始结点"
 	public static final String  start_round="start round";//"开始结点"
 	public static final String  end="end";//"结束结点"
 	public static final String  end_round="end round";//"结束结点"
 	public static final String  task="task";//"任务结点"
 	public static final String  node="node";//"自动结点"
 	public static final String  chat="chat";//"决策结点"
 	public static final String  state="state";//"状态结点"
 	public static final String  plug="plug";//"附加插件"
 	public static final String  fork="fork";//"分支结点"
 	public static final String  join="join";//"联合结点"
 	public static final String  complex="complex";//"复合结点"
 	public static final String  group="group";//"组织划分框编辑开关"
}
