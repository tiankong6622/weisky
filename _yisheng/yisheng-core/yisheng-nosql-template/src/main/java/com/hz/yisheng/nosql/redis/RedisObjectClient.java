package com.hz.yisheng.nosql.redis;

import java.util.Arrays;

import org.javafans.common.utils.serialize.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * redis 对基本对象读写相关操作
 * 
 * @author WeiSky
 *
 */
public class RedisObjectClient implements ObjectCacheClient{
	
	private  static Logger logger = LoggerFactory.getLogger(RedisObjectClient.class);
	
	private RedisFactory redisFactory;
	
	@Override
	public <T> T get(String key) {
		Jedis jedis = null;
		try{
			jedis = redisFactory.getJedis();
			byte[] bytes = jedis.get(key.getBytes());
			return SerializeUtil.unserialize(bytes);
		}catch(Exception e){
			logger.error("RedisObjectClient.get error, key=" + key, e);
			return null;
		}finally{
			redisFactory.returnResource(jedis);
		}
	}

	@Override
	public <T> boolean set(String key, T object) {
		Jedis jedis = null;
		try{
			jedis = redisFactory.getJedis();
			String result = jedis.set(key.getBytes(), SerializeUtil.serialize(object));
			return RedisConstants.SUCCESS_FLAGE.equals(result);
		}catch(Exception e){
			logger.error("RedisObjectClient.set error, key=" + key, e);
			return false;
		}finally{
			redisFactory.returnResource(jedis);
		}
	}

	@Override
	public <T> boolean set(String key, T object, int expireTime) {
		Jedis jedis = null;
		try{
			jedis = redisFactory.getJedis();
			String result = jedis.set(key.getBytes(), SerializeUtil.serialize(object));
			if(RedisConstants.SUCCESS_FLAGE.equals(result)){
				jedis.expire(key.getBytes(), expireTime);
				return true;
			}
			return false;
		}catch(Exception e){
			logger.error("RedisObjectClient.set error, key=" + key, e);
			return false;
		}finally{
			redisFactory.returnResource(jedis);
		}
	}

	@Override
	public long del(String... keys) {
		if(keys == null || keys.length == 0){
			return 0L;
		}
		Jedis jedis = null;
		try{
			jedis = redisFactory.getJedis();
			long success = 0L;
			for(int i = 0, size = keys.length; i < size; i++){
				success += jedis.del(keys[i].getBytes());
			}
			return success;
		}catch(Exception e){
			logger.error("RedisObjectClient.del error, key=" + Arrays.toString(keys), e);
			return -1L;
		}finally{
			redisFactory.returnResource(jedis);
		}
	}

}
