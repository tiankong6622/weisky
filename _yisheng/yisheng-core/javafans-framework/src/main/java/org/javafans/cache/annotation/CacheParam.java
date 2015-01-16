package org.javafans.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.commons.lang3.StringUtils;

/**
 * 该参数和 FetchFromCache 或 RemoveCache 标记同时出现
 * @author ChenJunhui
 */
@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheParam {

	/**
	 * 如果是基本类型 则可以不填 如果缓存关联某个对象的一个属性
	 * 比如关联用户 User 对象的一个属性叫userId 则 field="userId"
	 * @return
	 */
	public String field() default StringUtils.EMPTY;
}
