package org.javafans.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.javafans.cache.constants.CacheConstants;

/**
 * 从缓存中删除某个key
 * @author ChenJunhui
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoveCache {

	/**
	 * 缓存类型 默认从分布式缓存删
	 * @return
	 */
	public String cacheType() default CacheConstants.CACHE_TYPE_REMOTE;
	
	/**
	 * 要删除的缓存前缀集合
	 * 举个例子 比如 类目 有 
	 * @FetchFromCache(cacheName="xxx1")
	 * getCategory(Long id)
	 * 和 
	 * @FetchFromCache(cacheName="xxx2")
	 * getCategoryByParentIds(Long parentId)
	 * 那么 我对这个ID的category做更新操作后 需要清除上面两个缓存 那么
	 * @RemoveCache(catcheNames={"xxx1","xxx2"})
	 * @return
	 */
	public String[] catcheNames();
}
