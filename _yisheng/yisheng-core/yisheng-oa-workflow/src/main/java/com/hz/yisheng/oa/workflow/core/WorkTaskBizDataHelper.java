package com.hz.yisheng.oa.workflow.core;

import java.util.List;

import org.javafans.common.utils.FieldGetter;

import com.google.common.collect.Lists;
import com.hz.yisheng.oa.workflow.orm.WorkTaskBizData;

public class WorkTaskBizDataHelper {

	/**
	 * 将其他对象 组装成 工单的业务对象
	 * @author Chenjunhui
	 * @param bizDatas
	 * @param fieldGetter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  static <O> List<WorkTaskBizData> perpareBizDatas(String dataObjectType,List<O> bizDatas,FieldGetter<O, Long> fieldGetter){
		if(bizDatas==null || bizDatas.isEmpty())
			return java.util.Collections.EMPTY_LIST;
		List<WorkTaskBizData> list = Lists.newArrayListWithCapacity(bizDatas.size());
		for(O o:bizDatas){
			WorkTaskBizData bizData = new WorkTaskBizData();
			bizData.setDataObjId(fieldGetter.getKey(o));
			bizData.setDataObjType(dataObjectType);
			list.add(bizData);
		}
		return list;
	} 
	
	/**
	 * 将其他对象 组装成 工单的业务对象
	 * 自定义流程时使用
	 * @param <O>
	 * @param bizDatas
	 * @param fieldGetter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <O> List<WorkTaskBizData> perpareBizDatasObj(String dataObjectType,List<O> bizDatas,Long objId){
		if(bizDatas==null || bizDatas.isEmpty())
			return java.util.Collections.EMPTY_LIST;
		List<WorkTaskBizData> list = Lists.newArrayListWithCapacity(bizDatas.size());
		for(int i = 0, k = bizDatas.size(); i < k; i++){
			WorkTaskBizData bizData = new WorkTaskBizData();
			bizData.setDataObjId(objId);
			bizData.setDataObjType(dataObjectType);
			list.add(bizData);
		}
		return list;
	} 
	
}
