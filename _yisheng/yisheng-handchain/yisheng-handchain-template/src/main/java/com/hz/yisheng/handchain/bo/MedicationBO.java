package com.hz.yisheng.handchain.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hz.yisheng.handchain.dao.MedicationMapper;
import com.hz.yisheng.handchain.orm.Medication;

@Service
@Transactional
public class MedicationBO {
	@Autowired
	private MedicationMapper  medicationMapper;
	
	/**
	 * 新增信息
	 * @param customer
	 */
	public void insert(Medication medication){
		medicationMapper.insert(medication);
	}
	/**
	 * 修改母婴信息
	 * @param customer
	 */
	public void update(Medication medication){
		medicationMapper.update(medication);
	}
	
	/**
	 * 查询信息
	 * @param queryParam
	 * @return
	 */
	public List<Medication> list(Map<String,Object> queryParam){
		return medicationMapper.list(queryParam);
	}
	
	/**
	 * 获取记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param){
		return medicationMapper.count(param);
	}
	
	/**
	 * 用药信息分页查询
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<Medication> pageQuery(Page<Medication> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<Medication>() {
			@Override
			public long count() {
				return medicationMapper.count(queryMap);
			}
			@Override
			public List<Medication> list() {
				return medicationMapper.list(queryMap);
			}
		});
		return page;
	}
	
	/**
	 * 根据id查询用药记录
	 * @param id
	 * @return
	 */
	public Medication getMedicationById(Long id){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id",id);
		return medicationMapper.list(param).get(0);
		
	}
	
	/**
	 * 查历史记录
	 * @param customerId
	 * @return
	 */
	public List<Medication> getMedicationByCustomerId(Long customerId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("customerId", customerId);
		List<Medication> list = medicationMapper.list(queryParam);
		return (list.isEmpty()?null:list);
	}
}
