package com.hz.yisheng.handchain.solr;

import org.apache.solr.common.SolrDocument;

import com.hz.yisheng.solr.utils.SolrDocument2Entity;

public class SolrDocument2EntityImpl implements SolrDocument2Entity<TestPOJO>{

	@Override
	public TestPOJO doc2Entity(SolrDocument doc) {
		TestPOJO pojo = new TestPOJO();
		if((String) doc.getFieldValue("handchain_id") != null){
			pojo.setId(Long.parseLong((String) doc.getFieldValue("handchain_id")));
		}
		if((String)doc.getFieldValue("handchain_title") != null){
			pojo.setTitle((String)(doc.getFieldValue("handchain_title")));
		}
		if((String)doc.getFieldValue("handchain_content") != null ){
			pojo.setCat((String)(doc.getFieldValue("handchain_content")));
		}
		return pojo;
	}

}
