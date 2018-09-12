package com.lhh.common.utils;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 * properties文件读取工具类
 * @author cuihp
 *
 */
public class PropertieyUtils {
	
	private static Hashtable<String,Properties>  table = new Hashtable<String,Properties>();
	
	/**
	 * 加载配置实例
	 * @Title: PropertieyUtils.java 
	 * @author cuihp
	 * @version V1.0
	 */
	public static Properties loadPropertyInstance(String fileName){
		if(StringUtils.isEmpty(fileName)){
			throw new RuntimeException("配置文件名称不能为空");
		}
		Properties prop = table.get(fileName);
		if(StringUtils.isEmpty(prop)){
			 prop = new Properties();
			 InputStream in = PropertieyUtils.class.getResourceAsStream("/"+fileName);
		     try {
				prop.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    table.put(fileName, prop);
		}
		return prop;
	}

}
