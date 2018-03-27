package com.huel.xgms.listener;


import com.huel.xgms.util.ConfigUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author wsq
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 初始化加入系统配置
		ConfigUtil.init("/system.properties");
	}

}
