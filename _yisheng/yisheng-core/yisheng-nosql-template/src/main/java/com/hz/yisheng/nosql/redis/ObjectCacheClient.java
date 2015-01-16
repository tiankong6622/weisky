package com.hz.yisheng.nosql.redis;

/**
 * 针对复杂对象格式的读写操作
 * 
 * @author WeiSky
 *
 */
public interface ObjectCacheClient {

	/**
	 * 根据key获取对象
	 * @param key
	 * @return
	 */
	public <T> T get(String key);
	
	/**
	 * 将对象放进redis
	 * @param key
	 * @param object
	 * @return
	 */
	public <T> boolean set(String key, T object);
	
	/**
	 * 将对象放进redis,并设置过期时间
	 * @param key
	 * @param object
	 * @param expireTime
	 * @return
	 */
	public <T> boolean set(String key, T object, int expireTime);
	
	/**
	 * 根据一个key删除对象
	 * @param key
	 * @return
	 */
	public long del(String... keys);
}
