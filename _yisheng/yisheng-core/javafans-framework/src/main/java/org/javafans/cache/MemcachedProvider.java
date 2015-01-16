package org.javafans.cache;

import java.io.Serializable;

/**
 * memcachedProvider
 * @author ChenJunhui
 */
public class MemcachedProvider implements CacheProvider {

	private SpyMemcachedClient spyMemcachedClient;

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T)spyMemcachedClient.get(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, String key) {
		return (T)spyMemcachedClient.get(cacheName+key);
	}

	@Override
	public void put(String key, int expiredTime, Serializable value) {
		spyMemcachedClient.set(key, expiredTime, value);
	}

	@Override
	public void put(String cacheName, String key, int expiredTime,
			Serializable value) {
		spyMemcachedClient.set(cacheName+key, expiredTime, value);
	}

	@Override
	public void removeKey(String key) {
		spyMemcachedClient.delete(key);
	}

	@Override
	public void removeKey(String cacheName, String key) {
		spyMemcachedClient.delete(cacheName+key);
	}

	public void setSpyMemcachedClient(SpyMemcachedClient spyMemcachedClient) {
		this.spyMemcachedClient = spyMemcachedClient;
	}

}
