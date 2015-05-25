package org.itboys.commons.utils.pattern;

import java.io.IOException;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class PatternValUtils {

public static boolean isMobileNO(String mobiles){

Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

Matcher m = p.matcher(mobiles);


return m.matches();

}

public static void main(String[] args) throws IOException {

System.out.println(PatternValUtils.isMobileNO("12016155153"));

}

}

