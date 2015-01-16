package com.hz.yisheng.commondata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.yisheng.commondata.orm.CodesConfig;

/**
 * 数据字典定义
 * @author ChenJunhui
 *
 */
public interface CodesConfigMapper {

	public List<CodesConfig> getAll(long projectId);
	
	public void insert(CodesConfig codesConfig);
	
	public int update(CodesConfig codesConfig);
	
	public CodesConfig getByProjectAndType(@Param("projectId") long projectId,@Param("type") String type);
}
