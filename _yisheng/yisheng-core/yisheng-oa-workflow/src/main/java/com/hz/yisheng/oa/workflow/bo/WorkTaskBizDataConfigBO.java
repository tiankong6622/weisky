package com.hz.yisheng.oa.workflow.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.hz.yisheng.oa.workflow.dao.WorkTaskBizDataConfigMapper;
import com.hz.yisheng.oa.workflow.dto.TypeJson;
import com.hz.yisheng.oa.workflow.orm.WorkTaskBizDataConfig;

/**
 * 流程基础数据设置
 * 
 * @author a
 * 
 */
@Service
public class WorkTaskBizDataConfigBO {

	@Autowired
	private WorkTaskBizDataConfigMapper workTaskBizDataConfigMapper;

	/**
	 * 获取列表数据
	 * 
	 * @param params
	 * @return
	 */
	public List<WorkTaskBizDataConfig> findPageDataByMap(
			Map<String, Object> params) {
		return workTaskBizDataConfigMapper.findAll(params);
	}

	/**
	 * 根据实体名称获取一条信息
	 * 
	 * @param name
	 * @return
	 */
	public WorkTaskBizDataConfig findByBizDataConfigName(String name) {
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("dataObjType", name);
		List<WorkTaskBizDataConfig> list = workTaskBizDataConfigMapper
				.findAll(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取数据个数
	 * 
	 * @param params
	 * @return
	 */
	public Long findAllCount(Map<String, Object> params) {
		return workTaskBizDataConfigMapper.findAllCount(params);
	}

	/**
	 * 根据ID，获取数据
	 * 
	 * @param id
	 * @return
	 */
	public WorkTaskBizDataConfig getById(Long id) {
		return workTaskBizDataConfigMapper.getById(id);
	}

	/**
	 * 新增
	 * 
	 * @param workTaskBizDataConfig
	 */
	public void add(WorkTaskBizDataConfig workTaskBizDataConfig) {
		workTaskBizDataConfigMapper.add(workTaskBizDataConfig);
	}

	/** 修改 */
	public void update(WorkTaskBizDataConfig workTaskBizDataConfig) {
		workTaskBizDataConfigMapper.update(workTaskBizDataConfig);
	}

	/**
	 * 获取所有的流程
	 * 
	 * @return
	 */
	public List<WorkTaskBizDataConfig> choiceFlow() {
		return workTaskBizDataConfigMapper.findAllFlow();
	}

	/**
	 * 获取状态
	 * 
	 * @return
	 */
	public List<TypeJson> choiceStatus() {
		List<TypeJson> list = new ArrayList<TypeJson>();
		list.add(new TypeJson(1, "使用中"));
		list.add(new TypeJson(2, "已停用"));
		return list;
	}

}
