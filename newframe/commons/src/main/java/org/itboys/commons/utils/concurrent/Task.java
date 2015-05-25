package org.itboys.commons.utils.concurrent;

/**
 * 需要被异步执行的任务实现该方法
 * @author ChenJunhui
 *
 */
public interface Task {

	public void doTask();
	
}
