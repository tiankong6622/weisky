package org.itboys.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * redis 相关api封装
 * @author ChenJunhui
 *
 */
public class RedisClient {

	private  static Logger logger = LoggerFactory.getLogger(RedisCacheClient.class);
	
	private RedisConnect redisConnect;
	
	/**
	 * 根据key删除
	 * @param keys
	 * @return
	 */
	public long del(String... keys) {
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			return jedis.del(keys);
		}catch(Exception e){
			logger.error("RedisApi.del error ,key="+Arrays.toString(keys),e);
			return -1L;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 批量对象到 redis hash里 一个对象对应多个计数器的时候可用这个
	 * @param hashKey redis hash的key
	 * @param param
	 * @return
	 */
	public boolean hmset(String hashKey,Map<String, String> hash){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			String result = jedis.hmset(hashKey, hash);
			return RedisConstants.SUCCESS_FLAG.equals(result);
		}catch(Exception e){
			logger.error("RedisApi.hmset error ,hashKey="+hashKey,e);
			return false;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 添加int类型的score 到 sorted
	 * @param zkey
	 * @param score
	 * @param member
	 * @return
	 */
	public long zincrby(String zkey,long score,String member){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			double result = jedis.zincrby(zkey, score, member);
			System.out.println("redis===="+result);
			return (long)result;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("RedisApi.zincrby error ,zkey="+zkey+",score="+score+",member="+member,e);
			return -1;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 按score降序排 取得
	 * @param zkey
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> zrevrange(String zkey,long start,long end){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			Set<String> result = jedis.zrevrange(zkey, start, end);
			List<String> list = new ArrayList<String>(result.size());
			list.addAll(result);
			return list;
		}catch(Exception e){
			logger.error("RedisApi.zrevrange error ,zkey="+zkey+",start="+start+",end="+end,e);
			return null;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 按score降序排 取得一定范围内的 值
	 * @param zkey
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Tuple> zrevrangeWithScores(final String zkey, final long start,final long end) {
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			Set<Tuple> result = jedis.zrevrangeWithScores(zkey, start, end);
			List<Tuple> list = new ArrayList<Tuple>(result.size());
			list.addAll(result);
			return list;
		}catch(Exception e){
			logger.error("RedisApi.zrevrange error ,zkey="+zkey+",start="+start+",end="+end,e);
			return null;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 获取zscore
	 * @param zkey
	 * @param member
	 * @return
	 */
	public double zscore(String zkey,String member){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			return jedis.zscore(zkey, member);
		}catch(Exception e){
			logger.error("RedisApi.zscore error ,zkey="+zkey+",member="+member,e);
			return -1;
		}
	}
	
	public Long zcount(final String zkey, final double min, final double max){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			return jedis.zcount(zkey, min,max);
		}catch(Exception e){
			logger.error("RedisApi.zscore error ,zkey="+zkey+",min="+min+",max="+max,e);
			return -1L;
		}
	}
	
	/**
	 * redis hash 获取整个map
	 * @param hashkey
	 * @return
	 */
	public Map<String, String> hgetAll(String hashkey){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			return jedis.hgetAll(hashkey);
		}catch(Exception e){
			logger.error("RedisApi.hgetAll error ,hashKey="+hashkey,e);
			return null;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 获取redis hash中 hashKey为 hashKey key为key的值
	 * @param hashKey
	 * @param key
	 * @return
	 */
	public String hget(String hashKey,String key){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			return jedis.hget(hashKey, key);
		}catch(Exception e){
			logger.error("RedisApi.hget error ,hashKey="+hashKey+",key="+key,e);
			return null;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 获取redis hash中 hashKey为 hashKey key为key的值 并转成int类型返回
	 * @param hashKey
	 * @param key
	 * @return
	 */
	public Integer hgetWithIntVaue(String hashKey,String key){
		String value = hget(hashKey, key);
		return NumberUtils.isDigits(value)?Integer.parseInt(value):null;
	}

	/**
	 * 对redis hash里 key 为 key的字段 做自增操作 对象计数器的时候可用
	 * @param hashKey
	 * @param key
	 * @param incr 自增加多少
	 * @return
	 */
	public int hincrby(String hashKey,String key,long incr){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			jedis.hincrBy(hashKey, key, incr);
			return 0;
		}catch(Exception e){
			logger.error("RedisApi.hincrby error ,hashKey="+hashKey+",key="+key,e);
			return -1;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}

	/**
	 * 对redis hash里 key 为 key的字段 做自增加1操作
	 * @param hashKey
	 * @param key
	 * @return
	 */
	public int hincr(String hashKey,String key){
		return this.hincrby(hashKey, key, 1);
	}
	
	/**
	 * sorted set 添加元素 适合排行榜之类的
	 * @return
	 */
	public boolean zadd(String sortKey,String key,long value){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			long result = jedis.zadd(sortKey, value, key);
			return result>0;
		}catch(Exception e){
			logger.error("RedisApi.zadd error ,sortKey="+sortKey+",key="+key,e);
			return false;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 获取元素在redis 里的排名
	 * @param zkey
	 * @param member
	 * @return
	 */
	public Long zrank(final String zkey, final String member) {
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			return jedis.zrank(zkey, member);
		}catch(Exception e){
			logger.error("RedisApi.zadd error ,zkey="+zkey+",member="+member,e);
			return -1L;
		}
	}
	/**
	 * 获取元素在redis 里的排名(降序)
	 * @param zkey
	 * @param member
	 * @return
	 */
	public Long zrevrank(final String zkey, final String member) {
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			return jedis.zrevrank(zkey, member);
		}catch(Exception e){
			logger.error("RedisApi.zadd error ,zkey="+zkey+",member="+member,e);
			return -1L;
		}
	}
	
	/**
	 * 获得sort set 中 排名前 size的对象 按 isDesc 来确定是否降序或升序
	 * @param sortKey
	 * @param size
	 * @param isDesc
	 */
	public Set<String> getTopSortedSet(String sortKey,int size,boolean isDesc){
		Jedis jedis = null;
		try{
			jedis = redisConnect.getJedis();
			Set<String> result = null;
			if(isDesc){
				result=jedis.zrevrange(sortKey, 0, size);
			}else{
				result=jedis.zrange(sortKey, 0, size);
			}
			return result;
		}catch(Exception e){
			logger.error("RedisApi.getTopSortedSet error ,sortKey="+sortKey,e);
			return null;
		}finally{
			redisConnect.returnResource(jedis);
		}
	}
	
	/**
	 * 确定排行榜里的内容是long类型的用这个
	 * @param sortKey
	 * @param size
	 * @param isDesc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getTopSortedSetToList(String sortKey,int size,boolean isDesc){
		Set<String> set = this.getTopSortedSet(sortKey, size, isDesc) ;
		if(set==null || set.isEmpty())
			return Collections.EMPTY_LIST;
		List<Long> list = Lists.newArrayListWithCapacity(set.size());
		for(String s:set){
			list.add(Long.parseLong(s));
		}
		return list;
	}
	
	public void setRedisConnect(RedisConnect redisConnect) {
		this.redisConnect = redisConnect;
	} 
}
