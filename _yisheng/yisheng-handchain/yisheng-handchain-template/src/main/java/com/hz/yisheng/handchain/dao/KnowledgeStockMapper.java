package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hz.yisheng.handchain.orm.KnowledgeStockBean;
import com.hz.yisheng.handchain.orm.StagePlate;

/**
 * 知识库 DAO接口
 * @author chengxingju
 *
 */

public interface KnowledgeStockMapper {
	//查询所有记录
	public List<KnowledgeStockBean> findKnowledge(Map<String,Object> queryParam);
	public KnowledgeStockBean findById(Long id);
	
	
	//统计记录的条数
	public Long findCount(Map<String, Object> queryMap);
	//插入操作
	public void insert(KnowledgeStockBean knowledgeStock);
	//更新数据
	public void update(KnowledgeStockBean knowledgeStock);
	//删除数据
	public void delete(@Param("id")Long id);
	//阶段与版块添加
	public List<StagePlate> list(Map<String,Object> queryParam);
	//新增
	public void insertStage(StagePlate stage);
	//通过name查找
	public StagePlate getMenuByName(@Param("name")String name);
	//获取父菜单
	public StagePlate getParentMenu(Long id);
	public String getMaxSort(@Param("parentId")Long parentId);
	//编辑
	public int updateStage(StagePlate stageplate);
	//删除
	public int deleteStage(@Param("id")Long id);
	//通过parentid找到父节点及其菜单名称
	public StagePlate listByParentid(@Param("id")Long id);
	//通过fname判断父节点是否存在
	public StagePlate listByFname(@Param("name")String name);
	//通过子节点名称及其parentId判断子节点是否存在
	public StagePlate listByFid(@Param("name")String name,@Param("parentId")Long parentId);
	//获取所有板块信息
	public List<StagePlate> listStageInfo();
	//获取指定阶段所有版块信息
	public List<StagePlate> listPlateInfo(@Param("parentId")Long parentId);
	//通过父亲id查找fname
	public StagePlate findFname(@Param("id")Long id);
    //根据父亲节点name获取所有子节点
	public List<StagePlate> findSonInfoByFname(@Param("plate")String plate);
	//根据板块id查询版块信息
	public List<StagePlate> childlist(Map<String,Object> queryParam);
}
