package org.itboys.solr.doc2dto;

import org.apache.solr.common.SolrDocument;

/**
 * solr 文档和 java 数据对象转换接口
 * @author ChenJunhui
 *
 * @param <T>
 */
public interface SolrDocument2DataObject<T> {

	public T doc2Entity(SolrDocument doc);
}
