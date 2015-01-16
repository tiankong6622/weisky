package com.hz.yisheng.commondata.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.commondata.orm.KVConfig;

public interface KVConfigMapper {

	public List<KVConfig> list(Map<String, Object> sqlMap);
	
	public void insert(KVConfig config);
	
	public int delete(Long id);
	
	public int update(KVConfig config);

	public KVConfig findById(Long id);
	
	public Long count(Map<String, Object> map);
	
}
