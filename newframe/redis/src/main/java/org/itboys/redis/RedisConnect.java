package org.itboys.redis;

import org.itboys.commons.dto.HostAndPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

/**
 * redis 连接对象管理
 * @author 俊哥
 *
 */
public class RedisConnect implements InitializingBean,DisposableBean{

	private static  Logger logger = LoggerFactory.getLogger(RedisConnect.class);
	
	private RedisConfig redisConfig;

	private Pool<Jedis> jedisPool = null ;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		 JedisPoolConfig poolConfig = new JedisPoolConfig();
         //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
         //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		 poolConfig.setMaxTotal(redisConfig.getMaxTotal());
         //config.setMaxActive(redisConfig.getMaxActive());
         //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		 poolConfig.setMaxIdle(redisConfig.getMaxIdle());
         //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
         //config.setMaxWait(redisConfig.getMaxWait());
         //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		 poolConfig.setTestOnBorrow(redisConfig.isTestOnBorrow());
         if(redisConfig.isSentine()){
        	 jedisPool = new JedisSentinelPool(redisConfig.getMaster(), redisConfig.getHostAndPorts(), poolConfig, 
        			  redisConfig.getTimeOut(), redisConfig.getPassword(), redisConfig.getDatabase());
         }else{
        	 HostAndPort hostAndPort = redisConfig.getHostAndPort();
        	 jedisPool = new JedisPool(poolConfig, hostAndPort.getHost(), hostAndPort.getPort(), redisConfig.getTimeOut(), redisConfig.getPassword(),redisConfig.getDatabase());
         }
	}

	@Override
	public void destroy() throws Exception {
		if(jedisPool!=null){
			jedisPool.destroy();
		}
	}


	
	/**
	 * 池子里获取jedis对象
	 * @return
	 */
	public Jedis getJedis(){
		return jedisPool.getResource();
	}
	
	/**
     * 用完返还到连接池
     * @param pool 
     * @param redis
     */
	public void returnResource(Jedis jedis) {
		try {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		} catch (Exception e) {
			logger.error("return redis resource error",e);
			jedisPool.returnBrokenResource(jedis);
		}
	}


	public void setRedisConfig(RedisConfig redisConfig) {
		this.redisConfig = redisConfig;
	}

	

}
