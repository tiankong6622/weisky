package com.hz.yisheng.nosql.redis;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis工厂  根据redisConfig来生成
 * @author WeiSky
 *
 */
public class RedisFactory implements InitializingBean, DisposableBean{
	
	private RedisConfig redisConfig;
	
	private JedisPool jedisPool = null;
	
	/**
	 * 在连接池中获取redis对象
	 * @return
	 */
	public Jedis getJedis(){
		return jedisPool.getResource();
	}
	
	/**
	 * 用完后返还给连接池
	 * @param jedis
	 */
	public void returnResource(Jedis jedis){
		if(jedis != null){
			jedisPool.returnResource(jedis);
		}
	}
	
	
	@Override
	public void destroy() throws Exception {
		if(jedisPool != null){
			jedisPool.destroy();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		JedisPoolConfig config = new JedisPoolConfig();
		/*
		 * 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取。
		 * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		 */
		config.setMaxActive(redisConfig.getMaxActive());
		
		/*
		 * 控制一个pool可以有多少个状态为idle(空闲的)的jedis实例
		 */
		config.setMaxIdle(redisConfig.getMaxIdle());
		
		/*
		 * 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException;
		 */
		config.setMaxWait(redisConfig.getMaxWait());
		
		/*
		 * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
		 */
		config.setTestOnBorrow(redisConfig.isTestOnBorrow());
		
		jedisPool = new JedisPool(config, redisConfig.getIp(), redisConfig.getPort(), redisConfig.getTimeOut(), redisConfig.getPassword(),redisConfig.getDatabase());
	}

	public RedisConfig getRedisConfig() {
		return redisConfig;
	}

	public void setRedisConfig(RedisConfig redisConfig) {
		this.redisConfig = redisConfig;
	}

}
