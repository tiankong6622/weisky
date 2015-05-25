package com.hz.sunday.getui.getui;

import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.gexin.rp.sdk.base.uitls.MD5Util;
import com.gexin.rp.sdk.http.utils.HttpUtil;

/**
 * 获取推送结果
 * 
 * @author huanglei
 *
 */
public class FindPushMsgResultUtil {

	private static final String MASTER_SECRET = "959FOy37dc7Fgu5UQAQvl4";// 第三方客户端个推集成鉴权码，用于验证第三方合法性。在客户端集成SDK时需要提供
	private static final String APP_KEY = "UP8yx3NoQx5d7nf5HHV0x";// 用于鉴定身份是否合法
	private static final String TASK_ID = "";// 任务唯一识别号

	public static void main(String[] args) {
		SortedMap<String, Object> param = new TreeMap<String, Object>();
		param.put("action", "getPushMsgResult");
		param.put("appkey", APP_KEY);
		param.put("taskId", TASK_ID);

		// 计算Sign值
		StringBuilder input = new StringBuilder(MASTER_SECRET);
		for (Entry<String, Object> entry : param.entrySet()) {
			Object value = entry.getValue();
			if (value instanceof String || value instanceof Integer
					|| value instanceof Long) {
				input.append(entry.getKey());
				input.append(entry.getValue());
			}
		}
		String sign = MD5Util.getMD5Format(input.toString());
		param.put("sign", sign);

		Map<String, Object> ret = post(param);
//		System.out.println(ret);

	}

	private static Map<String, Object> post(Map<String, Object> param) {
		return HttpUtil.httpPostJSON(
				"http://sdk.open.api.igexin.com/apiex.htm", param);
	}

}
