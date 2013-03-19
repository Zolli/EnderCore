package com.Zolli.EnderCore.Utils;

public class stringUtils {
	
	public static String replaceByArray(String input, String[] find, String[] replace) throws Exception {
		String returnString = input;
		
		if(find.length != replace.length) {
			throw new Exception("Replace and find length must be equals!");
		}
		
		for( int i = 0 ; i < find.length ; i++ ) {
			returnString = returnString.replace(find[i], replace[i]);
		}
		
		return returnString;
	}
	
}
