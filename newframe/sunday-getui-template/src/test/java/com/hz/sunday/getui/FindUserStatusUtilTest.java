package com.hz.sunday.getui;

import junit.framework.TestCase;

import org.junit.Test;

import com.hz.sunday.getui.getui.FindUserStatusUtil;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 获取用户当前状态-测试
 * 
 * @author huanglei
 * 
 */
public class FindUserStatusUtilTest extends TestCase {

	public static String APP_ID = "hM4c81RaHH7ZMPhGHutZb1"; // 设定接收的应用
	public static String APP_KEY = "UP8yx3NoQx5d7nf5HHV0x"; // 用于鉴定身份是否合法
	public static String MASTER_SECRET = "959FOy37dc7Fgu5UQAQvl4"; // 第三方客户端个推集成鉴权码，用于验证第三方合法性。在客户端集成SDK时需要提供
	public static String CID = "7b2cc2ef75275bab4a7a094a8a176113"; // 用户标识
	public static String HOST = "http://sdk.open.api.igexin.com/apiex.htm"; // 接口地址

	/**
	 * 根据appId与CID,查询用户当前状态
	 */
	@Test
	public void testFindUserStatus() {
		try {

			BaseBean bb = new BaseBean(APP_ID, APP_KEY, MASTER_SECRET, HOST);
			bb.setCid(CID);

			FindUserStatusUtil.findUserStatus(bb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
