package com.huel.xgms.org;


import com.huel.xgms.admin.org.bean.Dept;
import com.huel.xgms.admin.org.service.DeptService;
import com.huel.xgms.httpbean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/orgdept")
public class DeptController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DeptService service;

	@RequestMapping("tree")
	public List<Dept> tree() {
		return service.tree();
	}
	
	@RequestMapping("editsave")
	public void edit(Dept dept) {
		service.editSave(dept);
	}
	
	@RequestMapping("del/{deptId}")
	public ResponseBean del(@PathVariable int deptId) {
		if(service.getPersonSizeOfDept(deptId) > 0) {
			
			return ResponseBean.createError("部门下存在警员，请先移除警员");
		}
		if(service.getChildDeptSize(deptId) > 0) {
			return ResponseBean.createError("部门下存在子部门，请先移除子部门");
		}
		service.delete(deptId);
		return ResponseBean.createSuccess(null);
	}
	
	@RequestMapping("addsave")
	public ResponseBean add(Dept dept) {
		if(this.service.add(dept)) {
			return ResponseBean.createSuccess(null);			
		} else {
			return ResponseBean.createError("部门数量超出限制，无法添加");
		}
	}
}
