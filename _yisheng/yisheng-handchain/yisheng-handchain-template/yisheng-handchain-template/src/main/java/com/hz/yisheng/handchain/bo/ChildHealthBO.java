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
	
	public void insert(ChildHealth childHealth){
		childHealthMapper.insert(childHealth);
	}
	
	public List<ChildHealth> list(Map<String,Object> queryParam){
		return childHealthMapper.list(queryParam);
	} 
	
	public ChildHealth getChildHealthById(Long id){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("id", id);
		List<ChildHealth> list = childHealthMapper.list(queryParam);
		return (list.isEmpty()?null:list.get(0));
	}
	
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
				Long xx = childHealthMapper.count(queryMap);
				System.out.println(xx);
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
