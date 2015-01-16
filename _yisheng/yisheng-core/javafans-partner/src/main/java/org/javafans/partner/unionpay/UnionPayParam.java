package org.javafans.partner.unionpay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.javafans.common.constants.CommonConstants;
import org.javafans.common.utils.money.MoneyUtils;
import org.javafans.common.utils.string.CommonStringUtils;

import com.unionpay.upop.sdk.QuickPayConf;

/**
 * 银联参数
 * @author ChenJunhui
 *
 */
public class UnionPayParam {
	
	/**
	 * 将 UnionPayParam对象组装成请求数组
	 * @return
	 */
	public String[] toUnionPayArray(){
		String[] valueVo = new String[]{
				QuickPayConf.version,//协议版本
				QuickPayConf.charset,//字符编码
	            "01",//交易类型
	            StringUtils.isEmpty(bankOrderNum)?"":bankOrderNum,//原始交易流水号
	            QuickPayConf.merCode,//商户代码
	            QuickPayConf.merName,//商户简称
	            "",//收单机构代码（仅收单机构接入需要填写）
	            "",//商户类别（收单机构接入需要填写）
	            commodityUrl==null?"":commodityUrl,//商品URL
	            productName,//商品名称
	            MoneyUtils.yuanToFen(productPrice).toString(),//商品单价 单位：分
	            String.valueOf(num),//商品数量
	            String.valueOf(zhekou),//折扣 单位：分
	            MoneyUtils.yuanToFen(wuliuFee).toString(),//运费 单位：分
	            orderNumber,//订单号（需要商户自己生成）
	            MoneyUtils.yuanToFen(fee).toString(),//交易金额 单位：分
	            "156",//交易币种
	            new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()),//交易时间
	            ip,//用户IP
	            customerName,//用户真实姓名
	            defaultPayType,//默认支付方式
	            defaultBankNumber,//默认银行编号
	            String.valueOf(timeout),//交易超时时间
	            QuickPayConf.merFrontEndUrl,// 前台回调商户URL
				QuickPayConf.merBackEndUrl,// 后台回调商户URL
	            ""//商户保留域
		};
		return valueVo;
	}

	private String bankOrderNum;//银联交易流水号 退货或再次付款时候用
	private String commodityUrl;//商品页面 可以当做订单详情页面
	private String productName;//商品名称
	private BigDecimal productPrice;//商品单价 当成一个订单的话 也可以传订单金额
	private Integer num=1;//商品数量 当成一个订单的话 就搞一个1好了
	private Integer zhekou = 0;//折扣 精确到分 不管 直接搞0 一堆乱七八糟的参数
	private BigDecimal wuliuFee=CommonConstants.ZERO;//物流费用
	private BigDecimal fee;//订单交易总金额
	private String currency="156";//币种 默认 人民币
	private String ip;//IP地址
	private String customerName=StringUtils.EMPTY;//商户姓名
	private String defaultPayType=StringUtils.EMPTY;//空的不管 支付方式 LitePay（认证支付）,ProPay（快捷支付）,CommonPay（小额支付）,ExpressPay（储值卡支付）,CSPay（网银支付）,DirectPay（后台支付），ICPay（IC卡支付）
	/**
	 * 银行编码
	 * 1	工商银行	ICBC
		2	农业银行	ABC
		3	中国银行	BOCSH
		4	建设银行	CCB
		5	招商银行	CMB
		6	浦发银行	SPDB
		7	广发银行	GDB
		8	交通银行	BOCOM
		9	邮政储蓄银行	PSBC
		10	中信银行	CNCB
		11	民生银行	CMBC
		12	光大银行	CEB
		13	华夏银行	HXB
		14	兴业银行	CIB
		15	上海银行	BOS
		16	上海农商	SRCB
		17	平安银行	PAB
		18	中行（大额）	BOC
		19	北京银行	BCCB
		20	北京农商银行	BRCB
	 */
	private String defaultBankNumber="";
	private long timeout=300000L;//交易超时时间
	private String orderNumber;//订单号8-32位的 
	
	public Long getOrderId() {
		return Long.parseLong(orderNumber);
	}
	
	public void setOrderId(Long orderId) {
		orderNumber=CommonStringUtils.getNumberStr(orderId, 8);
	}
	
	public String getCommodityUrl() {
		return commodityUrl;
	}
	public void setCommodityUrl(String commodityUrl) {
		this.commodityUrl = commodityUrl;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getZhekou() {
		return zhekou;
	}
	public void setZhekou(Integer zhekou) {
		this.zhekou = zhekou;
	}
	public BigDecimal getWuliuFee() {
		return wuliuFee;
	}
	public void setWuliuFee(BigDecimal wuliuFee) {
		this.wuliuFee = wuliuFee;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDefaultPayType() {
		return defaultPayType;
	}
	public void setDefaultPayType(String defaultPayType) {
		this.defaultPayType = defaultPayType;
	}
	public String getDefaultBankNumber() {
		return defaultBankNumber;
	}
	public void setDefaultBankNumber(String defaultBankNumber) {
		this.defaultBankNumber = defaultBankNumber;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
}
