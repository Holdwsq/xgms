package com.huel.xgms.system;

import com.huel.xgms.admin.system.bean.Role;
import com.huel.xgms.admin.system.bean.User;
import com.huel.xgms.admin.system.service.RoleService;
import com.huel.xgms.admin.system.service.UserService;
import com.huel.xgms.httpbean.ResponseEntity;
import com.huel.xgms.page.Pagination;
import com.huel.xgms.util.Constants;
import com.huel.xgms.util.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author wsq
 */
@RestController
@RequestMapping("/admin/sysuser")
public class SysUserController {
	
//	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService service;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("list")
	public Pagination list(int pageNum, int pageSize) {
		return this.service.queryUsers(pageNum, pageSize);
	}
	
	@RequestMapping("reset/{userId}")
	public void resetPwd(@PathVariable int userId) {
		this.service.resetPwd(userId, DigestUtil
				.MD5Digest(Constants.ADMIN_DEFAULT_PWD, Constants.MD5_SALT));
	}
	
	@RequestMapping("disable/{userId}")
	public void disable(@PathVariable int userId) {
		this.service.disable(userId);
	}
	
	@RequestMapping("enable/{userId}")
	public void enable(@PathVariable int userId) {
		this.service.enable(userId);
	}
	
	@RequestMapping("roles")
	public List<Role> roles() {
		return roleService.list();
	}
	
	@RequestMapping("addsave")
	public ResponseEntity addsave(User user) {
		user.setCreateTime(System.currentTimeMillis());
		user.setPwd(DigestUtil.MD5Digest(Constants.ADMIN_DEFAULT_PWD, Constants.MD5_SALT));
		user.setStatus(User.STATUS_ENABLE);
		user.setType(User.TYPE_NORMAL);
		if(service.add(user)) {
			return ResponseEntity.createSuccess(null);
		}
		return ResponseEntity.createError("账户已经被使用，请更换");
	}
	
	@RequestMapping("userrole/{userId}")
	public List<Integer> getUserRole(@PathVariable Integer userId) {
		return this.service.queryUserRoles(userId);
	}
	
	@RequestMapping("editsave")
	public void editsave(User user) {
		service.edit(user);
	}
}
