package com.hz.yisheng.solr.config;
/**
 * 通过http请求，配置项
 * 
 * 通过XML配置
 * 例如：
 * 	<bean id="httpSolrServerConfig" class="com.hz.yisheng.solr.config.HttpSolrServerConfig" lazy-init="false">
		<property name="url" value="http://localhost:8080/solr" />
		<property name="soTimeout" value="10000" />
		<property name="connectionTimeout" value="1000" />
		<property name="followRedirects" value="false" />
		<property name="allowCompression" value="true" />
		<property name="maxRetries" value="10" />
		<property name="defaultMaxConnectionsPerHost" value="100" />
		<property name="maxTotalConnections" value="100" />
	</bean>
 * 
 * @author WeiSky
 *
 */
public class HttpSolrServerConfig {

	private String url;//solr 服务 的连接地址
	private int soTimeout;//socket read timeout 通讯超时时间
	private int connectionTimeout; //连接超时时间
	private boolean followRedirects=false; 
	private boolean allowCompression=true; //允许压缩
	private int maxRetries; //重试次数
	private int defaultMaxConnectionsPerHost;
	private int maxTotalConnections;//最大连接数
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	public int getDefaultMaxConnectionsPerHost() {
		return defaultMaxConnectionsPerHost;
	}
	public void setDefaultMaxConnectionsPerHost(int defaultMaxConnectionsPerHost) {
		this.defaultMaxConnectionsPerHost = defaultMaxConnectionsPerHost;
	}
	public int getMaxTotalConnections() {
		return maxTotalConnections;
	}
	public void setMaxTotalConnections(int maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}
	
}
