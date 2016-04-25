package com.sailnow.utils;

import java.util.concurrent.ConcurrentHashMap;

public class CachUtil {

	private static ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
	
	public static void putInCache(String key, Object value)
	{
		map.put(key, value);
	}
	
	public static Object getFromCache(String key)
	{
		return map.get(key);
	}

	public static void clearCache() {
		map.clear();
		
	}

	public static int size() {
		return map.size();
	}
}
