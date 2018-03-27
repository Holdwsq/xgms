package com.huel.xgms.admin.app.service;


import com.huel.xgms.admin.app.bean.AppClient;
import com.huel.xgms.admin.app.dao.AppClientDao;
import com.huel.xgms.page.Pagination;
import org.jfaster.mango.plugin.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppClientServiceImpl implements AppClientService {

	@Autowired
	private AppClientDao dao;
	
	@Override
	public AppClient queryUpgrade(Integer versionCode) {
		return dao.queryUpgrade(versionCode, Page.create(1, 1));
	}

	@Override
	public Pagination list(int pageNum, int pageSize) {
		Page page = Page.create(pageNum, pageSize);
		Pagination pagination = new Pagination();
		pagination.setList(this.dao.query(page));
		pagination.setTotal(page.getTotal());
		return pagination;
	}

	@Override
	public boolean delete(int appId) {
		return dao.deleteAppClient(appId);
	}

	@Override
	public void save(AppClient app) {
		dao.addAppClient(app);
	}
	
}
