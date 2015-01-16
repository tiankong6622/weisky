package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hz.yisheng.handchain.orm.HandBook;
import com.hz.yisheng.handchain.orm.Hospital;

public interface AdminHandBookMapper {
    public Long count(Map<String,Object> param);
	
	public List<HandBook> list(Map<String,Object> param);
	
	public HandBook select(Long id);
	
	public void insert(HandBook hb);
	
	public void update(HandBook hb);
	
	public int delete(@Param("id")Long id);
	
	public List<Hospital> getAll();
	
	/**
	 * 根据id查看某医院指南
	 * @param id
	 * @return
	 */
	public HandBook queryById(Long id);
	

}
