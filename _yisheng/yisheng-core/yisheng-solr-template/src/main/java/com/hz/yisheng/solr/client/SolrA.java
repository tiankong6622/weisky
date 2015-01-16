package com.hz.yisheng.solr.client;

import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hz.yisheng.dto.ResultPageDO;
import com.hz.yisheng.solr.config.HttpSolrServerConfig;
import com.hz.yisheng.solr.utils.SolrDocument2Entity;

/**
 * solr操作的统一封装
 * 
 * @author WeiSky
 *
 */
public class SolrA {
	
	private SolrClient solrClient;
	
	private static Logger logger = LoggerFactory.getLogger(SolrA.class);
	
	public SolrA(){
		this.solrClient = new SolrClient();
	}
	
	public SolrA(HttpSolrServerConfig httpSolrConfig){
		this.solrClient = new SolrClient(httpSolrConfig);
	}

	/**
	 * 查询
	 * 
	 * @param <T>
	 * @param <T>
	 */
	public <T> List<T> solrQuery(String solrQuery, SolrDocument2Entity<T> iSolrDocument2Entity){
		try {
			SolrQuery query = new SolrQuery(solrQuery.toString());
			return solrClient.query(query, iSolrDocument2Entity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SolrA solrQuery.Exception",e);
			return null;
		}
	}
	
	/**
	 * 带有分页的查询
	 * 
	 * @param solrQuery
	 * @param iSolrDocument2Entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <T> ResultPageDO<T> pageQuery(String solrQuery,SolrDocument2Entity<T> iSolrDocument2Entity,int pageNo,int pageSize){
		try{
			SolrQuery query = new SolrQuery(solrQuery.toString());
			query.setStart((pageNo-1)*pageSize);
			query.setRows(pageSize);
			return solrClient.pageQuery(query, iSolrDocument2Entity, pageNo, pageSize);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("SolrA pageQuery.Exception",e);
			return null;
		}
	}
	
	/**
	 * 向solr添加或更新文档对象
	 * 
	 * @param doc
	 * @return
	 */
	public boolean addSolrInputDocument(SolrInputDocument doc){
		return solrClient.addSolrInputDocument(doc);
	} 
	
	/**
	 * 向solr中批量添加或者更新文档对象
	 * 
	 * @param docs
	 * @return
	 */
	public boolean addSolrInputDocuments(Collection<SolrInputDocument> docs){
		return solrClient.addSolrInputDocuments(docs);
	}
	
	/**
	 * 根据uniqueKey 删除solr的文档对象
	 * 
	 * @param uniqueKey
	 * @return
	 */
	public boolean deleteDocument(String uniqueKey){
		return solrClient.deleteDocument(uniqueKey);
	}
	
	/**
	 * 根据uniqueKey 集合  删除solr的文档对象 
	 * uniqueKey看solr schema.xml 的 <uniqueKey>标签配置的字段
	 * 
	 * @param serverKey
	 * @param uniqueKeys
	 * @return
	 */
	public boolean deleteDocuments(String serverKey,List<String> uniqueKeys){
		return solrClient.deleteDocuments(serverKey, uniqueKeys);
	}
	
	/**
	 * 清空文档
	 * 
	 * @return
	 */
	public boolean clearDocument(){
		return solrClient.clearDocument();
	}
	
}
