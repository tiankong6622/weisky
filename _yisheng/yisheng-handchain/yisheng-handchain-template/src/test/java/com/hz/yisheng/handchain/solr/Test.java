package com.hz.yisheng.handchain.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.hz.yisheng.handchain.dao.BaseDAOTest;
import com.hz.yisheng.solr.client.SolrClient;
import com.hz.yisheng.solr.config.HttpSolrServerConfig;
import com.hz.yisheng.solr.server.HttpSolrServerFactoryBean;


public class Test extends BaseDAOTest{
	
	@Autowired
	private HttpSolrServerConfig httpSolrServerConfig;
	//添加
	//@org.junit.Test
	public void testAdd() throws Exception{
		HttpSolrServerFactoryBean httpSolrServerFactoryBean = new HttpSolrServerFactoryBean();
		httpSolrServerFactoryBean.setHttpSolrServerConfig(httpSolrServerConfig);
		
		SolrClient solrClient = new SolrClient();
		solrClient.setSolrServer(httpSolrServerFactoryBean.getObject());
		
		SolrInputDocument sid = new SolrInputDocument();	
		sid.addField("handchain_id", "4");
		sid.addField("handchain_title", "医疗常识");
		sid.addField("handchain_content", "易盛母婴手环云南");
		sid.addField("id", "12");
		solrClient.addSolrInputDocument(sid);
	}
	//查询
	@org.junit.Test
	public void testQuery() throws Exception{
		HttpSolrServerFactoryBean httpSolrServerFactoryBean = new HttpSolrServerFactoryBean();
		httpSolrServerFactoryBean.setHttpSolrServerConfig(httpSolrServerConfig);
		
		SolrClient solrClient = new SolrClient();
		solrClient.setSolrServer(httpSolrServerFactoryBean.getObject());
		
		SolrQuery query = new SolrQuery("handchain_title:云南");
		SolrDocument2EntityImpl sddd = new SolrDocument2EntityImpl();
		List<TestPOJO> pojo = solrClient.query(query, sddd);
		
		for(TestPOJO pp : pojo){
			System.out.println(pp.getId() + ":" + pp.getTitle() + "-" + pp.getCat());
			
		}
	}
	
}