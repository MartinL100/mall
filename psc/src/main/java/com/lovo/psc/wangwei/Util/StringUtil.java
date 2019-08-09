package com.lovo.psc.wangwei.Util;

public class StringUtil {

	public static final String FILEERROR="非法上传！！";
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
