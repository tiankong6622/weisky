package com.hz.yisheng.handchain.bo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrInputDocument;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.yisheng.handchain.bo.impl.SolrDoc2KnowledgeStockBeanImpl;
import com.hz.yisheng.handchain.dao.KnowledgeStockMapper;
import com.hz.yisheng.handchain.orm.KnowledgeStockBean;
import com.hz.yisheng.handchain.orm.StagePlate;
import com.hz.yisheng.solr.client.SolrA;
import com.hz.yisheng.solr.config.HttpSolrServerConfig;

/**
 * KnowledgeStock业务对象
 * 
 * @author chengxingju
 *
 */
@Service
public class KnowledgeStockBO {
	
	@Autowired
	private KnowledgeStockMapper knowledgeStockMapper;
	@Autowired
	private HttpSolrServerConfig httpSolrServerConfig;

	/***
	 * 分页查询
	 * 
	 */
	public Page<KnowledgeStockBean> pageQuery(Page<KnowledgeStockBean> page,
			final Map<String, Object> queryMap) {
		
		return PageQueryUtils.pageQuery(page, queryMap,
				new PageQuery<KnowledgeStockBean>() {

					@Override
					public long count() {
						// TODO Auto-generated method stub
						return knowledgeStockMapper.findCount(queryMap);
					}

					@Override
					public List<KnowledgeStockBean> list() {
						// TODO Auto-generated method stub
						return knowledgeStockMapper.findKnowledge(queryMap);
					}
				});

	}
	
	/***
	 * 分页查询
	 * 
	 * 走搜索引擎，若从文档中没有搜到数据，却从数据库从搜索到，则把数据库中搜索到的值，插入到文档中
	 * 
	 * @throws Exception 
	 * 
	 */
	public List<KnowledgeStockBean> list(final Map<String, Object> queryMap) throws Exception {
		List<KnowledgeStockBean> knoList = null;
		StringBuffer query = new StringBuffer();
		if(queryMap.get("title") != null && queryMap.get("title") != ""){
			query.append("handchain_title:" +queryMap.get("title"));
		}
		
		if(queryMap.get("content") != null && queryMap.get("content") != ""){
			query.append(" OR  handchain_content:" + queryMap.get("content"));
		}
		
		SolrA solra = new SolrA(httpSolrServerConfig);
		
		//从搜索引擎中搜索
		if(!StringUtils.isBlank(query)){
			SolrDoc2KnowledgeStockBeanImpl sddd = new SolrDoc2KnowledgeStockBeanImpl();
			knoList = solra.solrQuery(query.toString(), sddd);
		}
		
		//从数据库总搜索
		if(knoList == null || knoList.size()==0){
			knoList = knowledgeStockMapper.findKnowledge(queryMap);
			if(knoList != null && knoList.size() > 0){
				List<SolrInputDocument> docList = Lists.newArrayListWithExpectedSize(knoList.size());
				for(KnowledgeStockBean kno : knoList){
					SolrInputDocument sid = new SolrInputDocument();	
					sid.addField("handchain_id", kno.getId());
					sid.addField("handchain_title",kno.getTitle());
					sid.addField("handchain_content", kno.getContent());
					sid.addField("id", "handchain_id_" + kno.getId());
					docList.add(sid);
				}
				//插入到搜索引擎文档中
				solra.addSolrInputDocuments(docList);
			}
		}
		
		return knoList;
	}
	/**
	 * 根据板块id查询板块信息
	 * @param id
	 * @return
	 */
	public StagePlate getChildMenu(Long id) {
		Map<String, Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("id", id);
		List<StagePlate> list = knowledgeStockMapper.childlist(queryParam);
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * 依据id查找记录
	 */
	public KnowledgeStockBean findById(Long id) {
		return knowledgeStockMapper.findById(id);
	}
	
	/**
	 * 查询数量
	 * 
	 * @param queryMap
	 * @return
	 */
	public Long findCount(final Map<String, Object> queryMap){
		return knowledgeStockMapper.findCount(queryMap);
	}

	/**
	 * 插入操作
	 */
	public void insertKnowlwdge(KnowledgeStockBean knowledgeStock) {
		try {
			//插入数据库
			knowledgeStockMapper.insert(knowledgeStock);
			
			//插入到搜索引擎文档中
			SolrA solra = new SolrA(httpSolrServerConfig);
			SolrInputDocument sid = new SolrInputDocument();	
			sid.addField("handchain_id", knowledgeStock.getId());
			sid.addField("handchain_title",knowledgeStock.getTitle());
			sid.addField("handchain_content", knowledgeStock.getContent());
			sid.addField("id", "handchain_id_" + knowledgeStock.getId());
			solra.addSolrInputDocument(sid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑记录
	 */
	public void updateKnowledge(KnowledgeStockBean knowledgeStock) {
		try {
			knowledgeStockMapper.update(knowledgeStock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除记录
	 */
	public void delete(Long id) {
		try {
			knowledgeStockMapper.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 知识库阶段与版块查询 获取全部内容
	 */
	public List<StagePlate> getAllMenus() {
		Map<String, Object> queryParam = Maps.newHashMapWithExpectedSize(0);
		return knowledgeStockMapper.list(queryParam);
	}

	/**
	 * 新增相关版块内容
	 * 
	 * @param StagePlate
	 */
	public void insertStage(StagePlate stagePlate) {
		knowledgeStockMapper.insertStage(stagePlate);
	}

	/**
	 * 通过菜单名成查找
	 * 
	 * @param name
	 */
	public StagePlate getMenuByName(String name) {
		return knowledgeStockMapper.getMenuByName(name);
	}

	/**
	 * 根据一级菜单id,获取所有的二级菜单
	 * 
	 * @param parentId
	 * @return
	 */
	public StagePlate getParentMenu(Long parentId) {
		return knowledgeStockMapper.getParentMenu(parentId);
	}

	/**
	 * 根据ID加载菜单对象
	 * 
	 * @param id
	 * @return
	 */
	public StagePlate getAdminMenu(Long id) {
		Map<String, Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("id", id);
		List<StagePlate> list = knowledgeStockMapper.list(queryParam);
		return list.isEmpty() ? null : list.get(0);
	}

	public String getMaxSort(Long parentId) {
		return knowledgeStockMapper.getMaxSort(parentId);
	}

	/**
	 * 删除menu操作,同时删除admin_role_menu中的数据
	 * 
	 * @param StagePlate
	 */
	public void deleteStage(Long id) {
		knowledgeStockMapper.deleteStage(id);
	}

	/**
	 * 更新操作 可更新的动态字段
	 * 
	 * @param StagePlate
	 * @return
	 */
	public void updateStage(StagePlate stageplate) {
		knowledgeStockMapper.updateStage(stageplate);
	}
	
	/**
	 * 通过父节点id找回相关信息
	 * @param id
	 */
	public StagePlate listByParentid(Long id){
		return knowledgeStockMapper.listByParentid(id);
	}
	/*
	 * 判断父节点是否存在
	 * @param name
	 */
	public StagePlate listByFname(String name){
		return knowledgeStockMapper.listByFname(name);
	}
	/**
	 * 判断子节点是否存在
	 * @param plate
	 * @param parentid
	 */
	public StagePlate listByFid(String name,Long parentId){
		return knowledgeStockMapper.listByFid(name,parentId);
	}
	/**
	 * 查找所有的阶段信息
	 */
	public List<StagePlate> listStageInfo(){
		return knowledgeStockMapper.listStageInfo();
	}
	
	/**
	 * 根据阶段id查找其孩子
	 */
	public List<StagePlate> listPlateInfo(Long parentId){
		return knowledgeStockMapper.listPlateInfo(parentId);
		
	}
	/**
	 * 依据id查找记录相关信息
	 */
	public StagePlate findFname(Long id){
		return knowledgeStockMapper.findFname(id);
	}
	
	/**
	 * 根据父亲节点的nama获取所有子节点信息
	 * 
	 */
	public List<StagePlate> findSonInfoByFname(String plate){
		return knowledgeStockMapper.findSonInfoByFname(plate);
	}
}
