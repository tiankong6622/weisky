package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.orm.KnowledgeStockBean;

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
	public Long findCount();
	//插入操作
	public void insert(KnowledgeStockBean knowledgeStock);
	//更新数据
	public void update(KnowledgeStockBean knowledgeStock);
	//删除数据
	public void delete(Long id);
}
