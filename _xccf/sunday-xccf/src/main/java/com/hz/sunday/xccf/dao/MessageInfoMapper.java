package com.hz.sunday.xccf.dao;

import java.util.List;
import java.util.Map;

import com.hz.sunday.xccf.orm.MessageInfo;

/**
 * 新闻、通知公告、相关资料接口
 * @author WeiSky
 *
 */
public interface MessageInfoMapper {

	/**
	 * 新增
	 * @param MessageInfo
	 */
	public void insert(MessageInfo messageInfo);
	
	/**
	 * 编辑
	 * @param MessageInfo
	 */
	public void update(MessageInfo messageInfo);
	
	/**
	 * 根id，删除一条信息
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 根据条件查询符合条件的信息条数
	 * @param param
	 * @return
	 */
	public Long getCount(Map<String, Object> param);
	
	/**
	 * 根据条件，查询符合条件的信息，返回的是一个集合
	 * @param param
	 * @return
	 */
	public List<MessageInfo> getList(Map<String, Object> param);
	
	/**
	 * 根户id，获取一条详情
	 * @param id
	 * @return
	 */
	public MessageInfo getSingleDetail(Long id);
	
	/**
	 * 根据信息类型获取
	 * @param mtype
	 * @return
	 */
	public List<MessageInfo> getByMtype(int mtype);
}
