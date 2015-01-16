package com.hz.yisheng.commondata.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hz.yisheng.commondata.orm.Attachement;

/**
 * 通用附件DAO
 * @author ChenJunhui
 *
 */
public interface AttachementMapper {

	/**
	 * 批量插入附件
	 * @param list
	 */
	public void batchInsert(List<Attachement> list);
	
	/**
	 * 单个插入附件
	 */
	public void insert(Attachement att);
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	public Attachement getById(long id);
	
	/**
	 * 根据关联的ID 和关联对象类型查找其附件
	 * @param objId
	 * @param type
	 * @return
	 */
	public List<Attachement> findBy(@Param("objId") String objId,@Param("type") String type);
	
	/**
	 * 根据关联的ID 和关联对象类型删除其所有附件
	 * @param objId
	 * @param type
	 */
	public int deleteAll(@Param("objId") String objId,@Param("type") String type);
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	public int delete(long id);
	
	/**
	 * 根据ObjId和Type去删除，type可以传空
	 * */
	public void deleteByObjIdAndType(Attachement att);
	
	
	public List<Attachement> getByIds(List<Long> ids);
	
	public List<Attachement> list(Map<String,Object> params);
	
	public void update(Attachement att);
}
