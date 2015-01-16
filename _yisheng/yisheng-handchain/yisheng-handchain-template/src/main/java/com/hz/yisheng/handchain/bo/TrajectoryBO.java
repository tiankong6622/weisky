package com.hz.yisheng.handchain.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.handchain.dao.TrajectoryMapper;
import com.hz.yisheng.handchain.orm.Trajectory;

@Service
@Transactional
public class TrajectoryBO {
	
	@Autowired
	private TrajectoryMapper  trajectoryMapper;
	
	/**
	 * 插入
	 * @param trajectory
	 */
	public void insert(Trajectory trajectory){
		trajectoryMapper.insert(trajectory);
	}
	
	/**
	 * 修改
	 * @param trajectory
	 */
	public void update(Trajectory trajectory){
		trajectoryMapper.update(trajectory);
	}
	
	/**
	 * 查询所有的运动轨迹
	 * @param queryParam
	 * @return
	 */
	public List<Trajectory> list(Map<String,Object> queryParam){
		return trajectoryMapper.list(queryParam);
		
	}
	/**
	 * 记录个数
	 * @param queryParam
	 * @return
	 */
	public Long count(Map<String,Object> queryParam){
		return trajectoryMapper.count(queryParam);
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<Trajectory> pageQuery(Page<Trajectory> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<Trajectory>() {
			@Override
			public long count() {
				return trajectoryMapper.count(queryMap);
			}
			@Override
			public List<Trajectory> list() {
				return trajectoryMapper.list(queryMap);
			}
		});
		return page;
	}
	/**
	 * 根据id查询运动轨迹
	 * @param id
	 * @return
	 */
	public List<Trajectory> getTrajectoryByCustomerId(Long id){
		return trajectoryMapper.getTrajectoryByCustomerId(id);
	}

}
