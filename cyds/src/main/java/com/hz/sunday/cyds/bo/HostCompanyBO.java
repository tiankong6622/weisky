package com.hz.sunday.cyds.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hz.sunday.cyds.dao.HostCompanyMapper;
import com.hz.sunday.cyds.orm.HostCompany;
/**
 * 主办单位基本信息
 * @author WeiSky
 *
 */
@Service
@Transactional
public class HostCompanyBO {
	
	@Autowired
	private HostCompanyMapper hostCompanyMapper;

	/**
	 * 新增
	 * @param MessageNotice
	 */
	public void insert(HostCompany hostCompany){
		hostCompanyMapper.insert(hostCompany);
	}
	
	/**
	 * 编辑
	 * @param MessageNotice
	 */
	public void update(HostCompany hostCompany){
		hostCompanyMapper.update(hostCompany);
	}
	
	/**
	 * 根id，删除一条信息
	 * @param id
	 */
	public void delete(Long id){
		hostCompanyMapper.delete(id);
	}
	
	/**
	 * 根据条件查询符合条件的信息条数
	 * @param param
	 * @return
	 */
	public Long getCount(Map<String, Object> param){
		return hostCompanyMapper.getCount(param);
	}
	
	/**
	 * 根据条件，查询符合条件的信息，返回的是一个集合
	 * @param param
	 * @return
	 */
	public List<HostCompany> getList(Map<String, Object> param){
		return hostCompanyMapper.getList(param);
	}
	
	/**
	 * 根户id，获取一条详情
	 * @param id
	 * @return
	 */
	public HostCompany getSingleDetail(Long id){
		return hostCompanyMapper.getSingleDetail(id);
	}
	
	/**
	 * 取一定条数的信息
	 * @param limit
	 * @return
	 */
	public List<HostCompany> getListLimit(int htype, int limit){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(3);
		if(htype > 0){
			param.put("htype", htype);
		}
		param.put("rowStart", 0);
		param.put("pageSize", limit);
		return getList(param);
	}
}
