package com.hz.yisheng.handchain.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hz.yisheng.handchain.dao.ChildHealthMapper;
import com.hz.yisheng.handchain.dao.CustomerMapper;
import com.hz.yisheng.handchain.orm.ChildHealth;

@Service
@Transactional
public class ChildHealthBO {
	@Autowired
	private ChildHealthMapper childHealthMapper;
	@Autowired
	private CustomerMapper customerMapper;
	
	/**
	 * 新增
	 * @param childHealth
	 */
	public void insert(ChildHealth childHealth){
		childHealthMapper.insert(childHealth);
	}
	
	/**
	 * 查询身体指标信息
	 * @param queryParam
	 * @return
	 */
	public List<ChildHealth> list(Map<String,Object> queryParam){
		return childHealthMapper.list(queryParam);
	}
	
	/**
	 * 根据id查询指定身体指标记录信息
	 * @param id
	 * @return
	 */
	public ChildHealth getHealthById(Long id){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("id", id);
		List<ChildHealth> list = childHealthMapper.list(queryParam);
		return (list.isEmpty()?null:list.get(0));
	}
	
	/**
	 * 根据customerid查询身体指标信息
	 * @param customerId
	 * @return
	 */
	public List<ChildHealth> getHealthByCustomerId(Long customerId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("customerId", customerId);
		List<ChildHealth> list = childHealthMapper.list(queryParam);
		return (list.isEmpty()?null:list);
	}
	
	/**
	 * 记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param){
		return childHealthMapper.count(param);
	}
	
	
	/**
	 * 分页查询
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<ChildHealth> pageQuery(Page<ChildHealth> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<ChildHealth>() {
			@Override
			public long count() {
				return childHealthMapper.count(queryMap);
			}
			@Override
			public List<ChildHealth> list() {
				return childHealthMapper.list(queryMap);
			}
		});
		return page;
	}
	
}
