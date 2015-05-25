package org.itboys.commons.utils.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 延迟任务队列 构建spring的bean  destroy-method="shutdown"
 * @author ChenJunhui
 */
public class DelayTaskQueue {

	private static Logger logger = LoggerFactory.getLogger(DelayTaskQueue.class);
	
	private ExecutorService executorService;
	private DelayQueue<DelayTask> queue;
	
	public  DelayTaskQueue(){
		executorService = Executors.newCachedThreadPool(); 
		queue = new DelayQueue<DelayTask>();
		executorService.execute(new DelayTaskConsumer(queue));
	}
	
	/**
	 * 添加延迟执行任务
	 * @param task
	 * @param delayMillis
	 */
	public void addTask(Task task,long delayMillis){
		DelayTask delayTask = new DelayTask(task,delayMillis);
		queue.add(delayTask);
	}
	

	public void shutdown(){
		executorService.shutdownNow();
	}
	
	class DelayTaskConsumer implements Runnable {  
	    private DelayQueue<DelayTask> queue;  
	      
	    public DelayTaskConsumer(DelayQueue<DelayTask> queue) {  
	        this.queue = queue;  
	    }  
	      
	    public void run() {  
	        try {  
	            while (!Thread.interrupted()) {  
	                queue.take().run();  
	            }  
	        } catch (InterruptedException e) {  
	        	logger.error("DelayTaskConsumer Interrupted",e);  
	        }  
	    }  
	}  
	
	
	public static void main(String[] args){
		DelayTaskQueue queue = new DelayTaskQueue();
		long time = 10000;
		for(int i=0;i<10;i++){
			final int j = i;
			queue.addTask(new Task() {
				
				@Override
				public void doTask() {
					System.out.println("伟哥好强"+j);
					
				}
			}, time);
			time=time-1000;
		}
		
		try {
			Thread.sleep(15000);
			queue.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
