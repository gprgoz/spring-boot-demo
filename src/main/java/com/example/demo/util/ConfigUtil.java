package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	private static final Properties prop = new Properties();
	
	static {
		InputStream is = null;
		try {
			is = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getValue(String key){
		return prop.getProperty(key);
	}
	
	public static String getValue(String key,String defaultValue){
		return prop.getProperty(key,defaultValue);
	}
	
	public static Integer getInteger(String key){
		return Integer.valueOf(prop.getProperty(key));
	}
	
}
