package com.hz.yisheng.handchain.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.handchain.dao.SportsMapper;
import com.hz.yisheng.handchain.orm.Sports;

@Service
@Transactional
public class SportsBO {
	@Autowired
	private SportsMapper  sportsMapper;
	
	/**
	 * 插入
	 * @param trajectory
	 */
	public void insert(Sports sports){
		sportsMapper.insert(sports);
	}
	
	/**
	 * 修改
	 * @param trajectory
	 */
	public void update(Sports sports){
		sportsMapper.update(sports);
	}
	
	/**
	 * 查询所有的运动记录
	 * @param queryParam
	 * @return
	 */
	public List<Sports> list(Map<String,Object> queryParam){
		return sportsMapper.list(queryParam);
		
	}
	/**
	 * 记录个数
	 * @param queryParam
	 * @return
	 */
	public Long count(Map<String,Object> queryParam){
		return sportsMapper.count(queryParam);
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<Sports> pageQuery(Page<Sports> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<Sports>() {
			@Override
			public long count() {
				return sportsMapper.count(queryMap);
			}
			@Override
			public List<Sports> list() {
				return sportsMapper.list(queryMap);
			}
		});
		return page;
	}
	/**
	 * 根据id查询运动记录
	 * @param id
	 * @return
	 */
	public List<Sports> getSportsByCustomerId(Long id){
		return sportsMapper.getSportsByCustomerId(id);
	}
}
