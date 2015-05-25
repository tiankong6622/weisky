package org.itboys.framework.query;

import java.util.List;

/**
 * 分页查询抽象封装 子类实现该接口提供的两个方法
 * @author ChenJunhui
 * @param <T>
 */
public interface PageQuery<T> {

	/**
	 * count查询总数的对象
	 * @param queryObject
	 * @return
	 */
	public long count();
	
	/**
	 * 查询结果集
	 * @param queryObject
	 * @return
	 */
	public List<T> list();
}
