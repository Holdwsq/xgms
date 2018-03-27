package com.huel.xgms.filter;

import com.huel.xgms.admin.system.bean.User;
import com.huel.xgms.util.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 权限过滤器
 * @author: wsq
 */
public class SecurityFilter implements Filter {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String url_pref = "admin/";
	
	private static final String public_pref = "admin/public";
	
	private static final String anony_pref = "admin/anony";
	
	/**
	 * 初始化过滤器,将公共地址添加到列表中
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		//请求地址
		String uri = request.getRequestURI().substring(request.getContextPath().length() + 1);
		logger.debug(uri);
		//1.验证是否是公共url地址
		if(!uri.startsWith(public_pref)) {
			//非公共地址
			//2.验证是否有token
			String authHeader = request.getHeader("Authorization");
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			// The part after "Bearer "
			String token = authHeader.substring(7);

			// 3.是否登录
			User user;
			try {
				Jws<Claims> claims = Jwts.parser().setSigningKey(Constants.JWT_KEY)
						.parseClaimsJws(token);
				//解析过期时间，已经过期的，按照未登陆处理。
				Date expir = claims.getBody().getExpiration();
				if(expir.getTime() - System.currentTimeMillis() <= 0) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}
				String id = claims.getBody().getId();
				String name = claims.getBody().getSubject();
				List<String> permission = claims.getBody().get("perm", List.class);
				user = new User();
				user.setId(Integer.parseInt(id));
				user.setName(name);
				user.setPermissions(permission);
	        } catch (Exception e) {
	        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			//没有登录用户
			request.setAttribute("loginUser", user);
			//4.验证当前请求是否是登陆后不需要验证的地址
			if(!uri.startsWith(anony_pref)) {
				//不是登录后免检的地址
				//5.验证当前用户的请求是否在自身的权限列表中
				uri = uri.substring(url_pref.length(), uri.length());
				int index = uri.indexOf('/');
				uri = index != -1 ? uri.substring(0, index) : uri;
				logger.debug(uri);
				//验证用户的权限列表中有没有权限
				if(!user.getPermissions().contains(uri)) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					return;
				}
			}
		}
		//公共地址放行
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}
