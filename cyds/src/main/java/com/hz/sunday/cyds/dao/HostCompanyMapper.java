package com.hz.sunday.cyds.dao;

import java.util.List;
import java.util.Map;

import com.hz.sunday.cyds.orm.HostCompany;

/**
 * 主办单位基本信息
 * @author WeiSky
 *
 */
public interface HostCompanyMapper {

	/**
	 * 新增
	 * @param MessageNotice
	 */
	public void insert(HostCompany hostCompany);
	
	/**
	 * 编辑
	 * @param MessageNotice
	 */
	public void update(HostCompany hostCompany);
	
	/**
	 * 根id，删除一条信息
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 根据条件查询符合条件的信息条数
	 * @param param
	 * @return
	 */
	public Long getCount(Map<String, Object> param);
	
	/**
	 * 根据条件，查询符合条件的信息，返回的是一个集合
	 * @param param
	 * @return
	 */
	public List<HostCompany> getList(Map<String, Object> param);
	
	/**
	 * 根户id，获取一条详情
	 * @param id
	 * @return
	 */
	public HostCompany getSingleDetail(Long id);
}
