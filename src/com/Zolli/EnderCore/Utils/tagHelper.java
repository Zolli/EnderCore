package com.Zolli.EnderCore.Utils;

import java.util.HashMap;
import java.util.Map;

public class tagHelper {

	public Map<String, String> createMap(String s) {
		Map<String,String> m = new HashMap<String,String>();
		String[] pairs = s.split(";");
		
		for(String pair : pairs) {
			String[] kvs = pair.split("=");
			m.put(kvs[0], kvs[1]);
		}
		return m;
	}
	
}
