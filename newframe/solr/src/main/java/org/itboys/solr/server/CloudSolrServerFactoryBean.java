package org.itboys.solr.server;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.itboys.solr.config.SolrCloudServerConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * 根据SolrCloudServerConfig 创建 CloudSolrServer的工厂bean
 * @author ChenJunhui
 *
 */
public class CloudSolrServerFactoryBean implements FactoryBean<CloudSolrServer> {

	private SolrCloudServerConfig solrCloudServerConfig;
	
	@Override
	public CloudSolrServer getObject() throws Exception {
		CloudSolrServer cloudSolrServer= new CloudSolrServer(solrCloudServerConfig.getZkHosts());
		cloudSolrServer.setDefaultCollection(solrCloudServerConfig.getDefaultCollectionName());
		cloudSolrServer.setZkClientTimeout(solrCloudServerConfig.getZkClientTimeout());
		cloudSolrServer.setZkConnectTimeout(solrCloudServerConfig.getZkConnectTimeout());
		cloudSolrServer.setParallelUpdates(solrCloudServerConfig.isParallelUpdates());
		return cloudSolrServer;
	}

	@Override
	public Class<CloudSolrServer> getObjectType() {
		return CloudSolrServer.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
