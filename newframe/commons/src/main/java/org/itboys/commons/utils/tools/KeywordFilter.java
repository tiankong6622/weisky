package org.itboys.commons.utils.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 基于正向匹配的天朝违禁词过滤
 * @author ChenJunhui
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class KeywordFilter {
	/** 违禁词禁止的 */
	private static HashMap keysMap = new HashMap(0);
	private static int matchType = 1; // 1:最小长度匹配 2：最大长度匹配
	private static final  String IS_END="isEnd";
	private static final  String V_1="1";
	private static final  String V_0="0";
	
	public  synchronized static void initKeywords(List<String> keywords) {
		HashMap tmp = new HashMap();
		for (int i = 0; i < keywords.size(); i++) {
			String key = keywords.get(i).trim();
			HashMap nowhash = tmp;
			for (int j = 0; j < key.length(); j++) {
				char word = key.charAt(j);
				Object wordMap = nowhash.get(word);
				if (wordMap != null) {
					nowhash = (HashMap) wordMap;
				} else {
					HashMap<String, String> newWordHash = new HashMap<String, String>();
					newWordHash.put(IS_END, V_0);
					nowhash.put(word, newWordHash);
					nowhash = newWordHash;
				}
				if (j == key.length() - 1) {
					nowhash.put(IS_END, V_1);
				}
			}
		}
		keysMap=tmp;
	}
	
	/**
	 * 检查一个字符串从begin位置起开始是否有keyword符合， 如果有符合的keyword值，返回值为匹配keyword的长度，否则返回零
	 * flag 1:最小长度匹配 2：最大长度匹配
	 */
	private static int checkKeyWords(String txt, int begin, int flag) {
		HashMap nowhash = keysMap;
		int maxMatchRes = 0;
		int res = 0;
		int l = txt.length();
		char word = 0;
		for (int i = begin; i < l; i++) {
			word = txt.charAt(i);
			Object wordMap = nowhash.get(word);
			if (wordMap != null) {
				res++;
				nowhash = (HashMap) wordMap;
				if (((String) nowhash.get(IS_END)).equals(V_1)) {
					if (flag == 1) {
						wordMap = null;
						nowhash = null;
						txt = null;
						return res;
					} else {
						maxMatchRes = res;
					}
				}
			} else {
				txt = null;
				nowhash = null;
				return maxMatchRes;
			}
		}
		txt = null;
		nowhash = null;
		return maxMatchRes;
	}

	/**
	 * 返回txt中关键字的列表
	 */
	public static Set<String> getTxtKeyWords(String txt) {
		Set set = new HashSet();
		int l = txt.length();
		for (int i = 0; i < l;) {
			int len = checkKeyWords(txt, i, matchType);
			if (len > 0) {
				set.add(txt.substring(i, i + len));
				i += len;
			} else {
				i++;
			}
		}
		txt = null;
		return set;
	}

	/**
	 * 仅判断txt中是否有关键字
	 */
	public static boolean isContentKeyWords(String txt) {
		for (int i = 0; i < txt.length(); i++) {
			int len = checkKeyWords(txt, i, 1);
			if (len > 0) {
				return true;
			}
		}
		txt = null;
		return false;
	}
	
	public static void main(String[] args) {
		long time=System.currentTimeMillis();
		List<String> keywords = new ArrayList<String>();
		keywords.add("胡锦涛");
		keywords.add("胡锦坤");
		keywords.add("江泽民");
		keywords.add("温家宝");
		keywords.add("共产党");
		keywords.add("法轮大法");
		keywords.add("法轮功");
		keywords.add("共产党");
		KeywordFilter.initKeywords(keywords);
		
		System.out.println(keysMap);
		
		String txt = "易超苦练法轮功,终于练成法轮大法,测试违禁词 共产温家党胡锦涛";
		boolean boo = KeywordFilter.isContentKeyWords(txt);
		System.out.println(boo);
		Set set = KeywordFilter.getTxtKeyWords(txt);
		System.out.println(set);
		
		txt = "是两块地激发红果工程中国共产党成立好多年if else delete from yaoyaoxxx士大夫";
		boo = KeywordFilter.isContentKeyWords(txt);
		System.out.println(boo);
		set = KeywordFilter.getTxtKeyWords(txt);
		System.out.println(set);
		
		keywords.add("蒋介石");
		KeywordFilter.initKeywords(keywords);
		txt = "蒋介石是浙江人 测试违禁词 共产温家党胡,锦涛,江xxxx泽民,易超苦练法轮功,终于练成法轮大法";
		KeywordFilter.setMatchType(2);
		boo = KeywordFilter.isContentKeyWords(txt);
		System.out.println(boo);
		set = KeywordFilter.getTxtKeyWords(txt);
		System.out.println(set);
		System.out.println(System.currentTimeMillis()-time);
	}

	public static  int getMatchType() {
		return matchType;
	}
	
	public static  int setMatchType(int mtype) {
		return matchType=mtype;
	}


}