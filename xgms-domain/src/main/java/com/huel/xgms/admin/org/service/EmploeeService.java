package com.huel.xgms.admin.org.service;

import com.huel.xgms.admin.org.bean.Emploee;
import com.huel.xgms.page.Pagination;

/**
 * 人员服务接口
 * @author wsq
 * @date 2018/3/27
 */
public interface EmploeeService {

	Emploee queryEmploeeByEmplidAndPwd(String emplid, String password);

	Pagination list(int pageNum, int pageSize);
}
