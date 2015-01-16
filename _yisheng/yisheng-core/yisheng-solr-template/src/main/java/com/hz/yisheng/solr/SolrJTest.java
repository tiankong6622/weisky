package com.hz.yisheng.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.hz.yisheng.solr.config.HttpSolrServerConfig;
import com.hz.yisheng.solr.server.HttpSolrServerFactoryBean;

public class SolrJTest {
	
	private static String url = "http://localhost:8080/solr";
	
	private static HttpSolrServerFactoryBean httpSolrServerFactoryBean;

	public static void fun1() throws SolrServerException, IOException{
		SolrServer server = new HttpSolrServer(url);
		SolrInputDocument doc1 = new SolrInputDocument(); 
		doc1.addField("id", "1");
		doc1.addField("title", "云南xxx科技");
		doc1.addField("cat", "企业信息门户，元数据，数字沙盘，知识管理");
		SolrInputDocument doc2 = new SolrInputDocument(); 
		doc2.addField("id", "2");
		doc2.addField("title", "胡启稳");
		doc2.addField("cat", "知识管理，企业信息门户，云南，昆明");
		SolrInputDocument doc3 = new SolrInputDocument(); 
		doc3.addField("id", "3");
		doc3.addField("title", "liferay");
		doc3.addField("test_s", "这个内容能添加进去么？这是动态字段呀");
		List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		docs.add(doc1);
		docs.add(doc2);
		docs.add(doc3);
		server.add(docs);
		server.commit();
	}
	
	public static void fun2() throws Exception{
		httpSolrServerFactoryBean = new HttpSolrServerFactoryBean();
		HttpSolrServerConfig hss = new HttpSolrServerConfig();
		hss.setUrl("http://localhost:8080/solr");
		hss.setSoTimeout(10000);
		hss.setConnectionTimeout(1000);
		hss.setMaxRetries(10);
		hss.setDefaultMaxConnectionsPerHost(100);
		hss.setMaxTotalConnections(100);
		httpSolrServerFactoryBean.setHttpSolrServerConfig(hss);
		
		SolrServer server = httpSolrServerFactoryBean.getObject(); 
		SolrQuery query = new SolrQuery("云南");
		QueryResponse response = server.query(query);
		SolrDocumentList docs = response.getResults();
		System.out.println("文档个数：" + docs.getNumFound()); 
		System.out.println("查询时间：" + response.getQTime());
		for (SolrDocument doc : docs) { 
			 System.out.println("id: " + doc.getFieldValue("id")); 
			 System.out.println("name: " + doc.getFieldValue("title")); 
			 System.out.println(); 
		}
	}
	
	public static void main(String[] args) throws Exception {
		fun2();
	}
}
