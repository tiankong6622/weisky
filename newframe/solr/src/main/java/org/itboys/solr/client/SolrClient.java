package org.itboys.solr.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest.ACTION;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.itboys.commons.dto.ResultPageDO;
import org.itboys.solr.doc2dto.SolrDocument2DataObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * solrj 客户端的API封装
 * @author ChenJunhui
 *
 */
public class SolrClient {

	private static Logger logger = LoggerFactory.getLogger(SolrClient.class);
	
	public static final int SOLR_SUCCESS_STATUS=0;
	
	/**
	 * 注入 CloudSolrServerFactoryBean 或  HttpSolrServerFactoryBean 创建的对象
	 */
	private SolrServer solrServer;

	public void setSolrServer(SolrServer solrServer) {
		this.solrServer = solrServer;
	}
	
	/**
	 * 向solr server中添加或更新 lucene文档对象
	 * @param doc
	 * @return
	 */
	public  boolean addSolrInputDocument(SolrInputDocument doc){
		try {
			UpdateRequest updateRequest = getUpdateResult();
			updateRequest.add(doc);
			UpdateResponse updateResponse = updateRequest.process(solrServer);
			if(updateResponse.getStatus() == SOLR_SUCCESS_STATUS){
				return true;
			}else{
				logger.error("addSolrInputDocument not success status="+updateResponse.getStatus() );
				return false;
			}
		} catch (Exception e) {
			logger.error("SolrClient addSolrInputDocument.Exception",e);
			return false;
		}
	}

	
	/**
	 * 向solr server中添加或更新 lucene 文档对象
	 * @param doc
	 * @return
	 */
	public  boolean addSolrInputDocuments(Collection<SolrInputDocument> docs){
		try {
			UpdateRequest updateRequest = getUpdateResult();
			updateRequest.add(docs);
			UpdateResponse updateResponse = updateRequest.process(solrServer);
			if(updateResponse.getStatus() == SOLR_SUCCESS_STATUS){
				return true;
			}else{
				logger.error("addSolrInputDocuments not success status="+updateResponse.getStatus() );
				return false;
			}
		} catch (Exception e) {
			logger.error("SolrClient addSolrInputDocuments.Exception",e);
			return false;
		}
	}
	
	/**
	 * solr 查询
	 * @param solrQuery
	 * @param iSolrDocument2DataObject SolrDocument2DataObject的实现类类 是java对象和solr文档对象的转换
	 * @return
	 */
	public <T> List<T> query(SolrQuery solrQuery,SolrDocument2DataObject<T> iSolrDocument2DataObject){
		try {
			QueryResponse rsp = solrServer.query( solrQuery );   
			SolrDocumentList docs = rsp.getResults();
			List<T> list = prepareSolrResult(iSolrDocument2DataObject, docs);
			return list;
		}catch(Exception e){
			logger.error("SolrClient query.Exception",e);
			return null;
		}
	}

	
	

	/**
	 * solr 分页查询
	 * @param solrServer
	 * @param solrQuery 查询条件
	 * @param iSolrDocument2DataObject SolrDocument2DataObject的子类 是java对象和solr文档对象的转换
	 * @param pageNo 当前页
	 * @param pageSize 每页多少条
	 * @return
	 */
	public  <T> ResultPageDO<T> pageQuery(SolrQuery solrQuery,SolrDocument2DataObject<T> iSolrDocument2DataObject,int pageNo,int pageSize){
		try {
			solrQuery.setStart((pageNo-1)*pageSize);
			solrQuery.setRows(pageSize);
			QueryResponse rsp = solrServer.query( solrQuery );   
			SolrDocumentList docs = rsp.getResults();
			List<T> list = prepareSolrResult(iSolrDocument2DataObject, docs);
			return getResultPageDO(docs.getNumFound(), list);
		}catch(Exception e){
			logger.error("SolrClient pageQuery.Exception",e);
			return getErrorResultPageDO(-1, "查询失败");
		}
	}
	
	/**
	 * 根据uniqueKey 删除solr server 的文档对象 
	 * uniqueKey看solr schema.xml 的 <uniqueKey>标签配置的字段
	 * @param uniqueKey
	 * @return
	 */
	public  boolean deleteDocument(String uniqueKey){
		try {
			UpdateRequest updateRequest = getUpdateResult();
			updateRequest.deleteById(uniqueKey);
			UpdateResponse updateResponse = updateRequest.process(solrServer);
			if(updateResponse.getStatus() == SOLR_SUCCESS_STATUS){
				return true;
			}else{
				logger.error("deleteDocument not success status="+updateResponse.getStatus()+",uniqueKey="+uniqueKey);
				return false;
			}
		} catch (Exception e) {
			logger.error("SolrClient deleteDocument.Exception",e);
			return false;
		}
	}
	
	/**
	 * 清空文档
	 * @return
	 */
	public  boolean clearDocument(){
		try {
			UpdateRequest updateRequest = getUpdateResult();
			UpdateResponse updateResponse = updateRequest.deleteByQuery("*:*").process(solrServer);
			if(updateResponse.getStatus() == SOLR_SUCCESS_STATUS){
				return true;
			}else{
				logger.error("clearDocument not success status="+updateResponse.getStatus());
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("SolrClient  clearDocument.Exception",e);
			return false;
		} 
	}
	
	/**
	 * 根据uniqueKey 集合  删除solr server 的文档对象 
	 * uniqueKey看solr schema.xml 的 <uniqueKey>标签配置的字段
	 * @param uniqueKeys
	 * @return
	 */
	public boolean deleteDocuments(String serverKey,List<String> uniqueKeys){
		try {
			UpdateRequest updateRequest = getUpdateResult();
			updateRequest.deleteById(uniqueKeys);
			UpdateResponse updateResponse = updateRequest.process(solrServer);
			if(updateResponse.getStatus() == SOLR_SUCCESS_STATUS){
				logger.error("deleteDocuments not success status="+updateResponse.getStatus()+",uniqueKeys="+uniqueKeys);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			logger.error("SolrClient  deleteDocuments.Exception",e);
			return false;
		} 
	}
	

	private UpdateRequest getUpdateResult() {
		UpdateRequest updateRequest = new UpdateRequest();  
		updateRequest.setAction(ACTION.COMMIT, false, false);
		return updateRequest;
	}
	
	private <T> List<T> prepareSolrResult(SolrDocument2DataObject<T> iSolrDocument2DataObject,SolrDocumentList docs) {
		List<T> list = new ArrayList<T>(docs.size());
		for(SolrDocument doc:docs){
			T t = iSolrDocument2DataObject.doc2Entity(doc);
			list.add(t);
		}
		return list;
	}
	
	private  <T> ResultPageDO<T> getErrorResultPageDO(int errorCode, String message) {
		ResultPageDO<T> result = new ResultPageDO<T>();
		result.setOk(false);
		result.setCode(errorCode);
		result.setMessage(message);
		return result;
	}

	private  <T> ResultPageDO<T> getResultPageDO(long count, List<T> list) {
		ResultPageDO<T> result = new ResultPageDO<T>();
		result.setOk(true);
		result.setList(list);
		result.setCount(count);
		return result;
	}

}
