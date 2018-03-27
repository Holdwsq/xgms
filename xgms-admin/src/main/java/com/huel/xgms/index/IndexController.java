package com.huel.xgms.index;

import com.huel.xgms.admin.system.bean.User;
import com.huel.xgms.admin.system.service.UserService;
import com.huel.xgms.httpbean.ResponseBean;
import com.huel.xgms.util.Constants;
import com.huel.xgms.util.DigestUtil;
import com.huel.xgms.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wsq
 */
@RestController
@RequestMapping("/admin/anony")
public class IndexController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ResponseBean index(HttpServletRequest request) {
		User user = LoginUtil.getLoginUser(request);
		IndexBean indexBean = new IndexBean(user.getName(), 
				userService.queryFuncs(user.getId()));
		return ResponseBean.createSuccess(indexBean);
	}
	
	@RequestMapping(value="changePwd")
	public ResponseBean changePwd(String oldPwd, String newPwd, 
			HttpServletRequest request) {
		User user = LoginUtil.getLoginUser(request);
		if(!this.userService.isPwdEquals(user.getId(), DigestUtil.MD5Digest(oldPwd, Constants.MD5_SALT))) {
			return ResponseBean.createError("原密码不正确，请确认");
		}
		this.userService.changePwd(user.getId(), DigestUtil.MD5Digest(newPwd, Constants.MD5_SALT));
		return ResponseBean.createSuccess("");
	}
	
}
