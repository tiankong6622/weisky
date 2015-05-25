package com.hz.sunday.getui.getui;

import java.io.IOException;

import com.gexin.rp.sdk.http.IGtPush;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 停止任务
 * 
 * @author huanglei
 *
 */
public class StopTaskUtil {

	/**
	 * 调用stop方法停止任务
	 * 
	 * @param bb
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void stopTask(BaseBean bb) throws IOException,
			InterruptedException {
		IGtPush push = new IGtPush(bb.getHost(), bb.getAppKey(),
				bb.getMasterSecret());
		boolean result = push.stop(bb.getTaskId());
//		System.out.println(result);
	}
}