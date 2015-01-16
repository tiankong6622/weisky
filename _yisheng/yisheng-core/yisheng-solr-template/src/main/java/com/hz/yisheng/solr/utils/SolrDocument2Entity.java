package com.hz.yisheng.solr.utils;

import org.apache.solr.common.SolrDocument;

/**
 * solr文档转java对象
 * @author WeiSky
 *
 */
public interface SolrDocument2Entity<T> {

	public T doc2Entity(SolrDocument doc);
}
