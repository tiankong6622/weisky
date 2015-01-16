package com.hz.yisheng.handchain.bo.impl;

import org.apache.solr.common.SolrDocument;

import com.hz.yisheng.handchain.orm.KnowledgeStockBean;
import com.hz.yisheng.solr.utils.SolrDocument2Entity;

public class SolrDoc2KnowledgeStockBeanImpl implements SolrDocument2Entity<KnowledgeStockBean>{

	@Override
	public KnowledgeStockBean doc2Entity(SolrDocument doc) {
		KnowledgeStockBean  ksb = new KnowledgeStockBean();
		if(doc != null){
			if(doc.getFieldValue("handchain_id") != null){
				ksb.setId(Long.parseLong(doc.getFieldValue("handchain_id").toString()));
			}
			if(doc.getFieldValue("handchain_title") != null){
				ksb.setTitle((String)doc.getFieldValue("handchain_title") );
			}
			if(doc.getFieldValue("handchain_content") != null){
				ksb.setContent((String)doc.getFieldValue("handchain_content"));
			}
		}
		return ksb;
	}

}
