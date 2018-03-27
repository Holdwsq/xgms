package com.huel.xgms.util;


import com.huel.xgms.admin.system.bean.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wsq
 */
public class LoginUtil {

	public static User getLoginUser(HttpServletRequest request) {
		Object obj = request.getAttribute("loginUser");
		if(obj == null) {
			return null;
		}
		return (User)obj; 
	}
}
