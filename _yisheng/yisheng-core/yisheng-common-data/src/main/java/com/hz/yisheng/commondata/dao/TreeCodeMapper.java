package com.hz.yisheng.commondata.dao;
import java.util.List;
import java.util.Map;

import org.javafans.common.dto.Option;

import com.hz.yisheng.commondata.orm.TreeCode;

public interface TreeCodeMapper {
	
	public List<TreeCode> list(Map<String, Object> map);
	
	public Long count(Map<String, Object> map);
	
	public List<Option> getTreeCodeByType(Map<String, Object> map);
	
	public void insert(TreeCode treeCode);
	
	public int update(TreeCode treeCode);
	
	public int delete(Long id);
	
	public TreeCode  getById(Long id);
}
