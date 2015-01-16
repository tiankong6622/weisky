package com.hz.yisheng.commondata.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.commondata.orm.ForbiddenWords;

/**
 * 违禁词DAO层
 * @author Liuguanjun
 */
public interface ForbiddenWordsMapper {

	public List<ForbiddenWords> list(Map<String, Object> map);
	
	public Long count(Map<String, Object> map);
	
	public ForbiddenWords getById(Long id);
	
	public void insert(ForbiddenWords entity);
	
	public void update(ForbiddenWords entity);
	
	public int delete(Long id);
	
}
