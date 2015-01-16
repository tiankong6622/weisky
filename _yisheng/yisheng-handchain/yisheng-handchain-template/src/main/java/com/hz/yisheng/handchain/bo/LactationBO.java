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
import com.hz.yisheng.handchain.dao.LactationMapper;
import com.hz.yisheng.handchain.orm.Lactation;

@Service
@Transactional
public class LactationBO {
	@Autowired
	private LactationMapper  lactationMapper;
	
	/**
	 * 新增信息
	 * @param customer
	 */
	public void insert(Lactation lactation){
		lactationMapper.insert(lactation);
	}
	/**
	 * 修改信息
	 * @param customer
	 */
	public void update(Lactation lactation){
		lactationMapper.update(lactation);
	}
	
	/**
	 * 查询信息
	 * @param queryParam
	 * @return
	 */
	public List<Lactation> list(Map<String,Object> queryParam){
		return lactationMapper.list(queryParam);
	}
	
	/**
	 * 获取记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param){
		return lactationMapper.count(param);
	}
	
	/**
	 * 哺乳信息分页查询
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<Lactation> pageQuery(Page<Lactation> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<Lactation>() {
			@Override
			public long count() {
				return lactationMapper.count(queryMap);
			}
			@Override
			public List<Lactation> list() {
				return lactationMapper.list(queryMap);
			}
		});
		return page;
	}
	
	/**
	 * 根据id查询哺乳记录
	 * @param id
	 * @return
	 */
	public Lactation getLactationById(Long id){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id",id);
		return lactationMapper.list(param).get(0);
		
	}
	
	/**
	 * 查历史记录
	 * @param customerId
	 * @return
	 */
	public List<Lactation> getLactationByCustomerId(Long customerId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("childrenId", customerId);
		List<Lactation> list = lactationMapper.list(queryParam);
		return (list.isEmpty()?null:list);
	}

}
