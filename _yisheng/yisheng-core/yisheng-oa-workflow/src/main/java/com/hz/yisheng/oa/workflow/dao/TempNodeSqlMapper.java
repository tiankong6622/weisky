package com.hz.yisheng.oa.workflow.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.oa.workflow.orm.TempNodeSql;

/**
 * 多人临时处理同一条工单
 * @author WeiSKY
 *
 */
public interface TempNodeSqlMapper {

	public void insert(TempNodeSql tempNodeSql);
	
	public void update(TempNodeSql tempNodeSql);
	
	public String getByNodId(Long nodeId);
	
	public List<TempNodeSql> list(Map<String,Object> param);
}
