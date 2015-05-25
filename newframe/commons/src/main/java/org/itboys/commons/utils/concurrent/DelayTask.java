package org.itboys.commons.utils.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务
 * @author ChenJunhui
 *
 */
public class DelayTask implements Delayed,Runnable {
	
	private long delayedMillis;
	/**
	 * 要延迟执行的任务
	 */
	private Task task;

	public Task getTask() {
		return task;
	}
	
	public DelayTask(Task task,long delayedMillis){
		//创建时间 当前系统的毫秒 如果要纳秒的话 delayMillisecond 换成纳秒
		this.delayedMillis=delayedMillis+System.currentTimeMillis();
		this.task=task;
	}

	@Override
	public void run() {
		task.doTask();
	}
	
	@Override
	public int compareTo(Delayed o) {
		DelayTask otherTask = (DelayTask)o;
		return delayedMillis > otherTask.delayedMillis?1:(delayedMillis < otherTask.delayedMillis ? -1 : 0);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(delayedMillis - System.currentTimeMillis(), TimeUnit.MILLISECONDS); 
	}
}
