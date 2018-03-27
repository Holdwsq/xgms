package com.huel.xgms.login;

import com.huel.xgms.admin.system.bean.User;
import com.huel.xgms.admin.system.service.UserService;
import com.huel.xgms.httpbean.ResponseEntity;
import com.huel.xgms.util.Constants;
import com.huel.xgms.util.DigestUtil;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/admin/public")
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity login(String username, String password) {
		
		User user = userService.queryByAccountAndPwd(username,
				DigestUtil.MD5Digest(password, Constants.MD5_SALT));
		if(user != null) {
			//以下是json web token的操作
			user.setAccount(null);
			user.setCreateTime(0);
			user.setPwd(null);
			user.setStatus(0);
			user.setType(0);
			
			String compactJws = Jwts.builder()
					.setIssuedAt(new Date())
					.setId(user.getId() + "")
					.setSubject(user.getName())
					.claim("perm", user.getPermissions())
					.setExpiration(new Date(System.currentTimeMillis() + Constants.JWT_ADMIN_EXPIRA * 60 * 1000))
					.compressWith(CompressionCodecs.DEFLATE)
					.signWith(SignatureAlgorithm.HS256, Constants.JWT_KEY)
					.compact();
			
			return ResponseEntity.createSuccess(compactJws);
		}
		return ResponseEntity.createError("用户名或密码错误");
	}
}
