package com.hz.sunday.getui;

import junit.framework.TestCase;

import org.junit.Test;

import com.hz.sunday.getui.getui.StopTaskUtil;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 停止任务
 * 
 * @author huanglei
 * 
 */
public class StopTaskUtilTest extends TestCase {

	public static String APP_KEY = "UP8yx3NoQx5d7nf5HHV0x"; // 用于鉴定身份是否合法
	public static String MASTER_SECRET = "959FOy37dc7Fgu5UQAQvl4"; // 第三方客户端个推集成鉴权码，用于验证第三方合法性。在客户端集成SDK时需要提供
	public static String HOST = "http://sdk.open.api.igexin.com/apiex.htm"; // 接口地址
	static String TASK_ID = "";// 任务唯一识别号

	/**
	 * 单推
	 */
	@Test
	public void testStopTask() {
		try {
			BaseBean bb = new BaseBean(null, APP_KEY, MASTER_SECRET, HOST);
			bb.setTaskId(TASK_ID);

			StopTaskUtil.stopTask(bb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
