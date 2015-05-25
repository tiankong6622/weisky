package org.itboys.solr.config;

/**
 * 如果走SolrCloudServer 的话 走这个配置
 * @author ChenJunhui
 *
 */
public class SolrCloudServerConfig {

	private String defaultCollectionName; //solrcloud的collection name
	private int zkClientTimeout; //zk client连接超时时间
	private int zkConnectTimeout; //zk连接超时时间
	private String zkHosts; //zk的配置文件
	private boolean parallelUpdates=true;//是否并行更新 最好设成true
	
	public String getDefaultCollectionName() {
		return defaultCollectionName;
	}
	public void setDefaultCollectionName(String defaultCollectionName) {
		this.defaultCollectionName = defaultCollectionName;
	}
	public int getZkClientTimeout() {
		return zkClientTimeout;
	}
	public void setZkClientTimeout(int zkClientTimeout) {
		this.zkClientTimeout = zkClientTimeout;
	}
	public String getZkHosts() {
		return zkHosts;
	}
	public void setZkHosts(String zkHosts) {
		this.zkHosts = zkHosts;
	}
	public int getZkConnectTimeout() {
		return zkConnectTimeout;
	}
	public void setZkConnectTimeout(int zkConnectTimeout) {
		this.zkConnectTimeout = zkConnectTimeout;
	}
	public boolean isParallelUpdates() {
		return parallelUpdates;
	}
	public void setParallelUpdates(boolean parallelUpdates) {
		this.parallelUpdates = parallelUpdates;
	}
}
