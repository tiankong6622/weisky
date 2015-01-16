package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.orm.Hospital;

public interface HospitalMapper {
	
	public Long count(Map<String,Object> param);
		
	public List<Hospital> list(Map<String,Object> param);
	
	public Hospital select(Long id);
	
	public void insert(Hospital hp);
	
	public void update(Hospital hp);
	
	public void delete(Long id);
	
	/**
	 * 查询所有的医院名称
	 * @param param
	 * @return
	 */
	public List<Hospital> queryHospitalName(Map<String,Object> param);

}
