package org.itboys.solr.config;

/**
 * 如果走HttpSolrServer 配置这些
 * @author ChenJunhui
 *
 */
public class HttpSolrServerConfig {

	private String url;//solr 服务 的连接地址
	private int soTimeout;
	private int connectionTimeout; //连接超时时间
	private boolean followRedirects=false; 
	private boolean allowCompression=true; //允许压缩
	private int maxRetries; //重试此时
	private int defaultMaxConnectionsPerHost;
	private int maxTotalConnections;
	
	public int getSoTimeout() {
		return soTimeout;
	}
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}
	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	public boolean isFollowRedirects() {
		return followRedirects;
	}
	public void setFollowRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
	}
	public boolean isAllowCompression() {
		return allowCompression;
	}
	public void setAllowCompression(boolean allowCompression) {
		this.allowCompression = allowCompression;
	}
	public int getMaxRetries() {
		return maxRetries;
	}
	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}
	
	public int getMaxTotalConnections() {
		return maxTotalConnections;
	}
	public void setMaxTotalConnections(int maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}
	public int getDefaultMaxConnectionsPerHost() {
		return defaultMaxConnectionsPerHost;
	}
	public void setDefaultMaxConnectionsPerHost(int defaultMaxConnectionsPerHost) {
		this.defaultMaxConnectionsPerHost = defaultMaxConnectionsPerHost;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
