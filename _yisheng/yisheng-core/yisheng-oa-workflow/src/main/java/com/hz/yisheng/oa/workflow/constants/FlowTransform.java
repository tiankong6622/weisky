package com.hz.yisheng.oa.workflow.constants;

import com.google.common.base.Function;
import com.hz.yisheng.oa.workflow.orm.WorkTask;
import com.hz.yisheng.oa.workflow.orm.WorkTaskRecord;

public class FlowTransform {

	public final static Function<WorkTask, Long> getTaskCurrentNodeIds = new Function<WorkTask, Long>() {
		@Override
		public Long apply(WorkTask input) {
			return input.getCurrentNode();
		}
	};
	
	public final static Function<WorkTask, Long> getFromTransitionIds = new Function<WorkTask, Long>() {
		@Override
		public Long apply(WorkTask input) {
			return input.getFromTransition();
		}
	};
	
	public final static Function<WorkTask, Long> getActors = new Function<WorkTask, Long>() {
		@Override
		public Long apply(WorkTask input) {
			return input.getActor();
		}
	};
	
	public final static Function<WorkTask, Long> getSubmiters = new Function<WorkTask, Long>() {
		@Override
		public Long apply(WorkTask input) {
			return input.getSubmitor();
		}
	};
	
	public final static Function<WorkTask, Long> getFlowIds = new Function<WorkTask, Long>() {
		@Override
		public Long apply(WorkTask input) {
			return input.getFlowId();
		}
	};
	
	public final static Function<WorkTaskRecord, Long> getTaskRecordNodeIds = new Function<WorkTaskRecord, Long>() {
		@Override
		public Long apply(WorkTaskRecord input) {
			return input.getNodeId();
		}
	};
	
	public final static Function<WorkTaskRecord, Long> getTaskRecordActors = new Function<WorkTaskRecord,Long>() {
		@Override
		public Long apply(WorkTaskRecord input) {
			return input.getActor();
		}
	};
	
	public final static Function<WorkTaskRecord, Long> getTaskRecordSubmiters = new Function<WorkTaskRecord, Long>() {
		@Override
		public Long apply(WorkTaskRecord input) {
			return input.getSubmitor();
		}
	};
	
	public final static Function<WorkTaskRecord, Long> getTaskRecordToTransitionIds = new Function<WorkTaskRecord, Long>() {
		@Override
		public Long apply(WorkTaskRecord input) {
			return input.getToTransition();
		}
	};
}
