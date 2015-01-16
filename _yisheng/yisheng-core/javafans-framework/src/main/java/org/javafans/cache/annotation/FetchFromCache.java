package org.javafans.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.commons.lang3.StringUtils;
import org.javafans.cache.constants.CacheConstants;

/**
 * 方法缓存标记 加上该标记的 再该方法第一次返回值后 根据方法的参数 设置缓存 下次同样参数过来调用 则cache中取
 * see org.javafans.cache.aop.FetchFromCacheAspect
 * @author ChenJunhui
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FetchFromCache {

	/**
	 * 缓存类型 默认分布式缓存
	 * @return
	 */
	public String cacheType() default CacheConstants.CACHE_TYPE_REMOTE;
	
	/**
	 * 缓存名称或前缀
	 * 如果是ehcache 则是cache的name 如果是 memcached 之类的remote cache等  则是 cache的前缀 (这种情况下 remote cache的key 为前缀加key
	 * 不填的话 ehcache则是默认的 cache名称 远程cache看你怎么设计 
	 * @return
	 */
	public String cacheName() default StringUtils.EMPTY;
	
	/**
	 * 缓存时间 秒
	 * @return
	 */
	public int cacheTime() default 3600;
}
