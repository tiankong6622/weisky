package com.hz.yisheng.handchain.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.yisheng.handchain.dao.KnowledgeStockMapper;
import com.hz.yisheng.handchain.orm.KnowledgeStockBean;

/**
 * KnowledgeStock业务对象
 * @author chengxingju
 *
 */
@Service
public class KnowledgeStockBO {
	@Autowired
	private KnowledgeStockMapper knowledgeStockMapper;
	
	/***
	 * 分页查询
	 * 
	 */
	public Page<KnowledgeStockBean> pageQuery(Page<KnowledgeStockBean> page, final Map<String, Object> queryMap) {
		return PageQueryUtils.pageQuery(page, queryMap, new PageQuery<KnowledgeStockBean>() {

			@Override
			public long count() {
				// TODO Auto-generated method stub
				return knowledgeStockMapper.findCount();
			}

			@Override
			public List<KnowledgeStockBean> list() {
				// TODO Auto-generated method stub
				return knowledgeStockMapper.findKnowledge(queryMap);
			}
	});
		
	}
	
	/**
	 * 依据id查找记录
	 */
	public KnowledgeStockBean findById(Long id){
		return knowledgeStockMapper.findById(id);
	}
	
	
	
	/**
	 * 统计记录条数
	 */
	public Long count(){
		return knowledgeStockMapper.findCount();
		
	}
	/**
	 * 插入操作
	 */
	public void insertKnowlwdge(KnowledgeStockBean knowledgeStock){
		try{
			knowledgeStockMapper.insert(knowledgeStock);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
/**
 * 编辑记录
 */
	public void updateKnowledge(KnowledgeStockBean knowledgeStock){
		try{
			knowledgeStockMapper.update(knowledgeStock);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 删除记录
	 */
	public void delete(Long id){
		try{
			knowledgeStockMapper.delete(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
