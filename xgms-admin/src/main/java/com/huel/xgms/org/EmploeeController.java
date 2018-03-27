package com.huel.xgms.org;

import com.huel.xgms.admin.org.service.EmploeeService;
import com.huel.xgms.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orgemp")
public class EmploeeController {

	@Autowired
	private EmploeeService service;
	
	@RequestMapping("list")
	public Pagination list(int pageNum, int pageSize) {
		return this.service.list(pageNum, pageSize);
	}
}
