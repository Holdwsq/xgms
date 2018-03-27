package com.huel.xgms.admin.app.service;

import com.huel.xgms.admin.app.bean.AppClient;
import com.huel.xgms.page.Pagination;

/**
 * app管理
 * @author wsq
 * @date 2018/3/27
 */
public interface AppClientService {

	AppClient queryUpgrade(Integer versionCode);
	
	Pagination list(int pageNum, int pageSize);

	boolean delete(int appId);
	
	void save(AppClient app);
}
