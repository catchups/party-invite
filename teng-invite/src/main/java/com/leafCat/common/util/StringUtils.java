package com.leafCat.common.util;

public class StringUtils {
	public static String camelToUnderbar(String str){
		String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        
        String rtn = str.replaceAll(regex, replacement).toUpperCase();
        
		return rtn;
	}

}
