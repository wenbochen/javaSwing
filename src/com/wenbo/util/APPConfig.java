package com.wenbo.util;

import java.util.ResourceBundle;


public class APPConfig {
	private static final String CONFIG_NAME = "config";
	private static APPConfig appConfig;
	/**
	 * 单例初始化
	 * @return
	 */
	public static APPConfig init()
	{
		if(appConfig == null){
			appConfig = new APPConfig();
		}
		return appConfig;
	}
	
	public ResourceBundle getConfigs(){
		ResourceBundle res = ResourceBundle.getBundle(CONFIG_NAME);
		return res;
	}
	
	public String getKey(String key){
		ResourceBundle res  = getConfigs();
		return (res!=null)?res.getString(key):null;
	}
	
}
