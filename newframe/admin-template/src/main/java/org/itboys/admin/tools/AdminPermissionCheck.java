package org.itboys.admin.tools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.commons.lang3.StringUtils;

/**
 * admin 权限AOP拦截注解
 * @author huml
 *
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminPermissionCheck {

	/**
	 * 访问权限拦截的key
	 * @return
	 */
	public String value() default StringUtils.EMPTY;
}
