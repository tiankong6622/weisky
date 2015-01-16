package com.hz.yisheng.commondata.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.commondata.orm.Watermark;

/**
 * dao - 水印
 * @author lenovo
 */
public interface WatermarkMapper {

	/**
	 * 获取列表
	 * @param sqlMap
	 * @return
	 */
	public List<Watermark>list(Map<String,Object>sqlMap);
	
	/**
	 * 添加水印
	 * @return
	 */
	public void insert(Watermark watermark);
	/**
	 * 修改
	 */
	public void update(Watermark watermark);
	/**
	 * 删除
	 */
	public int delete(Long id);
	/**
	 * 根据id获取信息
	 * @return
	 */
	public Watermark getById(Long id);
	
	public List<Watermark>getWatermarkList();
	
	public Watermark getWatermark(Long projectId);
}
