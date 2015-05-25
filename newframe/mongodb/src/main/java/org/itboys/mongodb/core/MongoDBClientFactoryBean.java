package org.itboys.mongodb.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * mongodb client配置
 * @author 俊哥
 *
 */
public class MongoDBClientFactoryBean implements FactoryBean<MongoClient>{
	
	private String servers;
	private String maxWaitTime;
	private String autoConnectRetry;
	private String connectionsPerHost;
	private String socketTimeout;
	private String threadsAllowedToBlockForConnectionMultiplier;
			
	private MongoClientOptions getMongoOptions() {
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();

		MongoClientOptions options = builder
				.autoConnectRetry("true".equals(this.autoConnectRetry))
				.socketTimeout(Integer.parseInt(this.socketTimeout))
				.maxWaitTime(Integer.parseInt(this.maxWaitTime))
				.connectionsPerHost(Integer.parseInt(this.connectionsPerHost))
				.threadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(this.threadsAllowedToBlockForConnectionMultiplier))
				.writeConcern(WriteConcern.SAFE)
				.build();

		return options;
	}

	@Override
	public MongoClient getObject() throws Exception {
		List<ServerAddress> addresses = new ArrayList<ServerAddress>();
		String[] array = this.servers.split(",");
		for(String server : array){
			if(!StringUtils.isBlank(server)){
				String[] splits = server.split(":");
				addresses.add(new ServerAddress(splits[0], Integer.parseInt(splits[1])));
			}
		}
		MongoClient client = new MongoClient(addresses, getMongoOptions());
		return client;
	}

	@Override
	public Class<? extends MongoClient> getObjectType() {
		return MongoClient.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setMaxWaitTime(String maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public void setAutoConnectRetry(String autoConnectRetry) {
		this.autoConnectRetry = autoConnectRetry;
	}

	public void setConnectionsPerHost(String connectionsPerHost) {
		this.connectionsPerHost = connectionsPerHost;
	}

	public void setSocketTimeout(String socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public void setThreadsAllowedToBlockForConnectionMultiplier(String threadsAllowedToBlockForConnectionMultiplier) {
		this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}
	
}