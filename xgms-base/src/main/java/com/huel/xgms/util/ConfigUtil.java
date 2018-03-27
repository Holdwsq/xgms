package com.huel.xgms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件类，读取配置文件
 * @author wsq
 */
public class ConfigUtil {
	private static Properties pps;
	private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	
	public static void init(String filename) {
		logger.debug("system.porperties文件");
		try {
			InputStream is = ConfigUtil.class
					.getResourceAsStream(filename);
			
			if(pps == null) {
				pps = new Properties();
			}
			pps.load(is);
		} catch (Exception e) {
			logger.error("启动加载system.properties文件出错", e);
		}
	}

	/**
	 * 方法描述：获取配置值
	 * @param key
	 */
	public static Object getValue(String key) {
		return pps.get(key);
	}
}
