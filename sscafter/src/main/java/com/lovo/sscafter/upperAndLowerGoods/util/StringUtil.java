package com.lovo.sscafter.upperAndLowerGoods.util;

public class StringUtil {

	/**
	 * 如果字符串为空就返回true
	 * @param str
	 * @return
	 */
	public static boolean blString(String str) {
		boolean bl=false;
		if(null==str||"".equals(str)) {
			bl=true;
		}
		return bl;
	}
}
