package org.javafans.cache;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * ehCache 相关的bean
 * 可做类似如下配置
 * <bean id="ehcacheManager" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean">
		<property name="configLocation" value="classpath:/cache/ehcache.xml" />
		<property name="cacheName" value="CACHE_XXXX" />
	</bean>
 * @author ChenJunhui
 */
public class EhcacheProvider implements CacheProvider{
	
	private static  Logger logger = LoggerFactory.getLogger(EhcacheProvider.class);
	
	private String defaultCacheName;
	
	private CacheManager ehcacheManager;
	
	@Override
	public <T> T get(String key) {
		return (T)get(defaultCacheName,key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, String key) {
		Cache cache = ehcacheManager.getCache(cacheName);
		Element element = cache.get(key);
		if(element!=null){
			return (T)element.getObjectValue();
		}
		return null;
	}
	
	@Override
	public void put(String key, int expiredTime,Serializable value) {
		put(defaultCacheName,key, expiredTime, value);
	}

	@Override
	public void put(String cacheName, String key, int expiredTime,
			Serializable value) {
		Cache cache = ehcacheManager.getCache(cacheName);
		Element element = new Element(key, value);
		element.setTimeToIdle(expiredTime);
		cache.put(element);
		
	}

	@Override
	public void removeKey(String key) {
		removeKey(defaultCacheName, key);
	}

	@Override
	public void removeKey(String cacheName, String key) {
		try{
			Cache cache = ehcacheManager.getCache(cacheName);
			cache.remove(key);
		}catch(Exception e){
			logger.error("ehcache 删除缓存失败 key为{}{},可能因为缓存中没有对应的key",cacheName,key);
		}
		
	}

	public void setDefaultCacheName(String defaultCacheName) {
		this.defaultCacheName = defaultCacheName;
	}

	public void setEhcacheManager(CacheManager ehcacheManager) {
		this.ehcacheManager = ehcacheManager;
	}

}
