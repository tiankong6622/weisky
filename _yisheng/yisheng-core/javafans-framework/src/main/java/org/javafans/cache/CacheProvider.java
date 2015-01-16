package org.javafans.cache;

import java.io.Serializable;

/**
 * cache 提供接口
 * @author ChenJunhui
 */
public interface CacheProvider {

	/**
	 * 从缓存中获取key
	 * @param <T>
	 * @param key
	 * @return
	 */
	public <T> T get(String key);
	
	/**
	 * 根据cacheName 和 key获取缓存对象
	 * @param <T>
	 * @param cacheName 如果是ehcache 则是cache的name 如果是 memcached 之类的remote cache等  则是 cache的前缀 (这种情况下 remote cache的key 为前缀加key)
	 * @param key
	 * @return
	 */
	public <T> T get(String cacheName,String key);
	
	
	/**
	 * ehcache中塞对象
	 * @param key
	 * @param value
	 */
	public void put(String key, int expiredTime,Serializable value) ;
	
	/**
	 * 根据cacheName 像缓存中塞对象
	 * @param cacheName 如果是ehcache 则是cache的name 如果是 memcached 之类的remote cache等  则是 cache的前缀 (这种情况下 remote cache的key 为前缀加key)
	 * @param key
	 * @param value
	 */
	public void put(String cacheName,String key, int expiredTime,Serializable value) ;
	
	/**
	 * 根据key删除缓存中的对象
	 * @param key
	 */
	public void removeKey(String key);
	
	/**
	 * 根据key删除缓存中的对象
	 *@param cacheName 如果是ehcache 则是cache的name 如果是 memcached 之类的remote cache等  则是 cache的前缀 (这种情况下 remote cache的key 为前缀加key)
	 * @param key
	 */
	public void removeKey(String cacheName,String key);
}
