package org.itboys.redis;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import org.itboys.commons.utils.serialize.SerializeUtil;

/**
 * redis 对基本对象读写相关的
 * @author ChenJunhui
 */
public class RedisCacheClient{
	
	private  static Logger logger = LoggerFactory.getLogger(RedisCacheClient.class);
	
	private RedisConnect redisConnect;
	
	public <T> T get(String key) {
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			byte[] bytes = jedis.get(key.getBytes());
			return SerializeUtil.unserialize(bytes);
		}catch(Exception e){
			logger.error("RedisObjectClient.set error ,key="+key,e);
			return null;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}

	public <T> boolean set(String key, T object) {
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			String result = jedis.set(key.getBytes(), SerializeUtil.serialize(object));
			return RedisConstants.SUCCESS_FLAG.equals(result);
		}catch(Exception e){
			logger.error("RedisObjectClient.set error ,key="+key,e);
			return false;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 设置超时时间的对象缓存
	 * @param key
	 * @param object
	 * @param expireTime
	 * @return
	 */
	public <T> boolean set(String key, T object, int expireTime) {
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			String result = jedis.set(key.getBytes(), SerializeUtil.serialize(object));
			if(RedisConstants.SUCCESS_FLAG.equals(result)){
				jedis.expire(key.getBytes(), expireTime);
				return true;
			}
			return false;
		}catch(Exception e){
			logger.error("RedisObjectClient.set error ,key="+key,e);
			return false;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}


	
	public long del(String... keys) {
		if(keys==null || keys.length==0){
			return 0L;
		}
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			long success = 0L;
			for(int i=0,size=keys.length;i<size;i++){
				success+=jedis.del(keys[i].getBytes());
			}
			return success;
		}catch(Exception e){
			logger.error("RedisObjectClient.del error ,key="+Arrays.toString(keys),e);
			return -1L;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}

	
	public void setRedisConnect(RedisConnect redisConnect) {
		this.redisConnect = redisConnect;
	}

}
