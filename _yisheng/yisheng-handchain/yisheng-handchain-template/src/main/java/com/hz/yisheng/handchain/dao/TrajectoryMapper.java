package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.orm.Trajectory;

public interface TrajectoryMapper {
	/**
	 * 插入 
	 * @param trajectory
	 */
	public void insert(Trajectory trajectory);
	/**
	 * 更新
	 * @param trajectory
	 */
	public void update(Trajectory trajectory);
	/**
	 * 查询所有的运动轨迹
	 * @param queryParam
	 * @return
	 */
	public List<Trajectory> list(Map<String,Object> queryParam);
	/**
	 * 记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param);
	
	/**
	 * 根据id查询运动轨迹
	 * @param id
	 * @return
	 */
	public List<Trajectory> getTrajectoryByCustomerId(Long id);

}
