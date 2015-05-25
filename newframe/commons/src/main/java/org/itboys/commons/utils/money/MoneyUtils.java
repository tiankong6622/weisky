package org.itboys.commons.utils.money;

import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import org.itboys.commons.utils.common.CommonUtils;
import org.itboys.commons.utils.string.CommonStringUtils;

/**
 * 金额相关
 * @author ChenJunhui
 *
 */
public class MoneyUtils {

	private final static String fraction[] = { "角", "分" };

	private final static String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆",
			"柒", "捌", "玖" };

	private final static String unit[][] = { { "元", "万", "亿" },
			{ "", "拾", "佰", "仟" } };

	
	/**
	 * 金额转中国银行大写金额
	 * @param n
	 * @return
	 */
	public static String decimalToChineseMoney(BigDecimal n) {
		return n==null?StringUtils.EMPTY:digitToChineseMoney(n.doubleValue());
	}
	/**
	 * 金额转中国银行大写金额
	 * @param n
	 * @return
	 */
	public static String digitToChineseMoney(double n) {
		String head = n < 0 ? "负" : "";

		n = Math.abs(n);

		String s = "";

		for (int i = 0; i < fraction.length; i++) {

			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i])
					.replaceAll("(零.)+", "");

		}

		if (s.length() < 1) {

			s = "整";

		}

		int integerPart = (int) Math.floor(n);

		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {

			String p = "";

			for (int j = 0; j < unit[1].length && n > 0; j++) {

				p = digit[integerPart % 10] + unit[1][j] + p;

				integerPart = integerPart / 10;

			}

			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]
					+ s;

		}

		return head
				+ s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "")
						.replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");

	}
	
	public static String totalToChineseFee(Collection<?> collection,String properties){
		BigDecimal money = CommonUtils.sum(collection, properties);
		return digitToChineseMoney(money.doubleValue());
	}

	public static String getDecimalBfb(BigDecimal money,int bfb){
		if(money==null)
			return "";
		return CommonStringUtils.formatNumber(money.multiply(new BigDecimal(bfb)).divide(new BigDecimal(100)).toString(), 2);
	}
	
	/**
	 * 元 转 分
	 * @param mone
	 * @return
	 */
	public static Integer yuanToFen(BigDecimal money){
		if(money==null)
			return 0;
		return money.multiply(new BigDecimal(100)).intValue();
	}
	
}
