package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.orm.Lactation;

public interface LactationMapper {
	/**
	 * 新增
	 * @param customer
	 */
	public void insert(Lactation lactation);
	/**
	 * 修改信息
	 * @param customer
	 */
	public void update(Lactation lactation);

	/**
	 * 查询所有信息
	 * @param queryParam
	 * @return
	 */
	public List<Lactation> list(Map<String,Object> queryParam);
	/**
	 * 得到信息的个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param);

}
