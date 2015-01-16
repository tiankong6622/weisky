package com.hz.yisheng.oa.workflow.bo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javafans.common.constants.CommonConstants;
import org.javafans.common.utils.reflection.Reflections;
import org.javafans.framework.spring.common.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.yisheng.oa.workflow.dao.WorkTaskBizDataConfigMapper;
import com.hz.yisheng.oa.workflow.dao.WorkTaskBizDataMapper;
import com.hz.yisheng.oa.workflow.orm.WorkTaskBizData;
import com.hz.yisheng.oa.workflow.orm.WorkTaskBizDataConfig;

/**
 * 工单业务数据
 * @author WeiSky
 *
 */
@Service
public class WorkTaskBizDataBO {

	@Autowired
	private WorkTaskBizDataMapper workTaskBizDataMapper;
	@Autowired
	private WorkTaskBizDataConfigMapper workTaskBizDataConfigMapper;
	
	/**
	 * 批量插入业务数据
	 * @param list
	 */
	public void batchInsert(List<WorkTaskBizData> list){
		workTaskBizDataMapper.batchInsert(list);
	}
	
	public Map<String,Object> getWorkTaskRecordBizData(long workTaskRecordId){
		Map<String,Object> sqlMap = new HashMap<String, Object>(1);
		sqlMap.put("workTaskRecordId", workTaskRecordId);
		List<WorkTaskBizData> bizDatas = workTaskBizDataMapper.list(sqlMap);
		return bizDatasToMap(bizDatas);
	}
	
	/**
	 * 获得主流程的数据
	 * @param workTaskId
	 * @return
	 */
	public Map<String,Object> getMainBizDatas(long workTaskId){
		Map<String,Object> sqlMap = new HashMap<String, Object>(2);
		sqlMap.put("workTaskId", workTaskId);
		sqlMap.put("isMain", CommonConstants.YES);
		List<WorkTaskBizData> bizDatas = workTaskBizDataMapper.list(sqlMap);
		return bizDatasToMap(bizDatas);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> bizDatasToMap(List<WorkTaskBizData> bizDatas) {
		if(bizDatas==null || bizDatas.isEmpty()){
			return Collections.EMPTY_MAP;
		}
		Map<String,List<Long>> bizDataMap = new HashMap<String, List<Long>>();
		for(WorkTaskBizData bd:bizDatas){
			if(bizDataMap.containsKey(bd.getDataObjType())){
				bizDataMap.get(bd.getDataObjType()).add(bd.getDataObjId());
			}else{
				List<Long> dataObjIds = Lists.newArrayList(bd.getDataObjId());
				bizDataMap.put(bd.getDataObjType(), dataObjIds);
			}
		}
		List<String> dataObjTypes =  Lists.newArrayListWithCapacity(bizDataMap.size());
		dataObjTypes.addAll(bizDataMap.keySet());
		List<WorkTaskBizDataConfig> configs = workTaskBizDataConfigMapper.getWorkTaskBizDataConfigs(dataObjTypes);
		Map<String, Object> resultMap = Maps.newHashMapWithExpectedSize(configs.size());
		try{
			for(WorkTaskBizDataConfig config:configs){
				if(config.getIsSignle().equals(CommonConstants.YES)){//如果是单个数据对象
					Long dataObjId = bizDataMap.get(config.getDataObjType()).get(0);
					//根据dealBean和beanMethod 反射获取组装单个对象
					resultMap.put(config.getDataObjType(), Reflections.invokeMethod(SpringContextHolder.getBean(config.getDealBean()),
							config.getBeanMethod(), new Class<?>[]{Long.class},new Object[]{dataObjId}));
				}else{
					//根据dealBean和beanMethod 反射获取组装单个对象
					resultMap.put(config.getDataObjType(), Reflections.invokeMethod(SpringContextHolder.getBean(config.getDealBean()),
							config.getBeanMethod(), new Class<?>[]{List.class},new Object[]{bizDataMap.get(config.getDataObjType())}));
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return resultMap;
	}
	
	public void deleteByTaskId(Long workTaskId){
		workTaskBizDataMapper.deleteByTaskId(workTaskId);
	}
	
	public List<WorkTaskBizData> getByTypeId(Map<String,Object> param){
		return workTaskBizDataMapper.list(param);
	}
	
}
