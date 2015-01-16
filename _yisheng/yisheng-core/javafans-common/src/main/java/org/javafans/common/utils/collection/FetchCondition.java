package org.javafans.common.utils.collection;

/**
 * 集合对象过滤条件接口
 * @author ChenJunhui
 */
public interface FetchCondition<T> {

	/**
	 * T对象是否符合抽取条件
	 * @param t
	 * @return
	 */
	public boolean needFetch(T t);
}
