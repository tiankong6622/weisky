package com.hz.sunday.getui.getui;

import java.io.IOException;

import com.gexin.rp.sdk.base.IQueryResult;
import com.gexin.rp.sdk.http.IGtPush;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 获取用户当前状态
 * 
 * @author huanglei
 *
 */
public class FindUserStatusUtil {

	/**
	 * 根据appId与CID,查询用户当前状态
	 * 
	 * @param bb
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void findUserStatus(BaseBean bb) throws IOException,
			InterruptedException {
		IGtPush push = new IGtPush(bb.getHost(), bb.getAppKey(),
				bb.getMasterSecret());
		IQueryResult abc = push.getClientIdStatus(bb.getAppId(), bb.getCid());
//		System.out.println(abc.getResponse());
	}
}