package com.hz.sunday.getui.utils;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.impl.Target;
import com.hz.sunday.getui.constant.TemplateType;
import com.hz.sunday.getui.getui.PushMsgToAppUtil;
import com.hz.sunday.getui.getui.PushMsgToListUtil;
import com.hz.sunday.getui.pojo.BaseBean;

/**
 * 发送消息
 * 
 * @author huanglei
 * 
 */
public class SendMessageUtil {

	/**
	 * 发送消息
	 * 
	 * 直接调用sendMsg方法即可，最简单的调用要实现BaseBean中的一个构造方法
	 * 每个参数的意义，看BaseBean中的属性注释
	 * 
	 * 例如在单元测试中执行：
	 *  BaseBean bb = new BaseBean("IKerZXhvhu7rlF8L6nu6i","YBF4V08Uwc6LeFkTjS0X63","GwqXzW5M7u9DXtjcgwJGH8","http://sdk.open.api.igexin.com/apiex.htm");
		bb.setCid("20becd13a12907d441a9152e1b377b67");
		bb.setTitle("E国寿");
		bb.setText("手机号为1861069805的用户成功兑换了综合意外险、保单号为pp281615162646、保费为30、扣除积分600");
		try {
			SendMessageUtil.sendMsg(bb, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 * 
	 * @param bb
	 * @param aTargets
	 *            android用户
	 * @param iTargets
	 *            IOS用户
	 * @throws Exception
	 */
	public static void sendMsg(BaseBean bb, List<Target> aTargets,
			List<Target> iTargets) throws Exception {
		if (null == aTargets && null == iTargets) {// 给所有APP应用发送消息
			// 针对IOS用户
			bb.setPhoneType("IOS");
			PushMsgToAppUtil.sendMsgToApp(TemplateType.TRANSMISSION_TEMPLATE,
					bb);
			// 针对android用户
			bb.setPhoneType("ANDROID");
			PushMsgToAppUtil.sendMsgToApp(TemplateType.NOTIFICATION_TEMPLATE,
					bb);
		} else {
			// 给android用户发送"点击通知打开应用消息"
			bb.setPhoneType("ANDROID");
			if (null != aTargets && aTargets.size() > 0) {
				PushMsgToListUtil.sendMsgToList(
						TemplateType.NOTIFICATION_TEMPLATE, bb, aTargets);
			}
			// 给IOS用户发送"透传消息"；
			bb.setPhoneType("IOS");
			if (null != iTargets && iTargets.size() > 0) {
				PushMsgToListUtil.sendMsgToList(
						TemplateType.TRANSMISSION_TEMPLATE, bb, iTargets);
			}
		}
	}
	
	public static void main(String[] args) {
		BaseBean baseBean = new BaseBean("r2KTmqZHHC8e9dp1k9bwB9",
				"78ZbvqlQro8psttpBiTqf7",
				"ZYd0opjJIT6ZtgjDvCm5t",
				"http://sdk.open.api.igexin.com/apiex.htm");
		baseBean.setTitle("测试");
		// baseBean.setText(resourceHolder.getStringValue("adminRoot")+"/mobi/getInfoPage?id="+id);
		baseBean.setContent("测试");
		baseBean.setPayload("测试");
		baseBean.setLogo("logo.png");
		baseBean.setLogoUrl("http://fbbadmin.53xsd.com/beauty/images/logo.png");
		baseBean.setActionLocKey("");
		baseBean.setMessage("sdaf");
		/*List<Target> list = new ArrayList<Target>();
		Target target = new Target();
		target.setAppId("r2KTmqZHHC8e9dp1k9bwB9");
		target.setClientId("a59dae0eb9635be982d60203e0d2e3c4");
		list.add(target);*/
		
		try {
			System.out.println("begin");
			SendMessageUtil.sendMsg(baseBean, null, null);
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}