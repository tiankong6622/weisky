package org.itboys.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.itboys.commons.dto.HostAndPort;

/**
 * redis 配置
 * @author ChenJunhui
 *
 */
public class RedisConfig {

	private int maxTotal;
	private int maxIdle;
	private int maxWait;
	private boolean testOnBorrow;
	private int timeOut;
	private String hosts; //host:port 多个逗号隔开 比如 host:port,host2:port2
	private String password;
	private int database=0;//哪个数据库 redis默认是第0个数据库
	private boolean sentine;//是否支持高可用故障转移
	private String master;
	
	public HostAndPort getHostAndPort(){
		if(StringUtils.isBlank(hosts)){
			return null;
		}
		String[] hostsArr = StringUtils.split(StringUtils.trim(hosts),",");
		String[] hostArr = StringUtils.split(StringUtils.trim(hostsArr[0]),":");
		return new HostAndPort(Integer.parseInt(hostArr[0]), hostArr[1]);
	}
	
	public Set<String> getHostAndPorts(){
		if(StringUtils.isBlank(hosts)){
			return null;
		}
		String[] hostsArr = StringUtils.split(StringUtils.trim(hosts),",");
		Set<String> set = new HashSet<String>();
		for(String hostAndPort:hostsArr){
			set.add(StringUtils.trim(hostAndPort));
		}
		return set;
	}
	
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDatabase() {
		return database;
	}
	public void setDatabase(int database) {
		this.database = database;
	}
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public boolean isSentine() {
		return sentine;
	}
	public void setSentine(boolean sentine) {
		this.sentine = sentine;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
}
