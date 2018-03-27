package com.huel.xgms.admin.org.service;


import com.huel.xgms.admin.org.bean.Emploee;
import com.huel.xgms.admin.org.dao.EmploeeDao;
import com.huel.xgms.page.Pagination;
import org.jfaster.mango.plugin.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wsq
 */
@Repository
public class EmploeeServiceImpl implements EmploeeService {

	@Autowired
	private EmploeeDao dao;

	@Override
	public Emploee queryEmploeeByEmplidAndPwd(String emplid, String password) {
		return this.dao.getEmploee(emplid, password);
	}
	
	@Override
	public Pagination list(int pageNum, int pageSize) {
		Page page = Page.create(pageNum, pageSize);
		Pagination pagination = new Pagination();
		pagination.setList(this.dao.query(page));
		pagination.setTotal(page.getTotal());
		return pagination;
	}
	
}
