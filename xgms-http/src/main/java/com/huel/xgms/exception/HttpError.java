package com.huel.xgms.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * 系统异常枚举
 * @author wyf
 *
 */
public enum HttpError {
	
	/**
	 * 缺少必要请求参数
	 */
	MISSING_REQUIRED_PARAMETERS(0X0001, 400, "missing_required_parameters"),
	/**
	 * 非法请求参数
	 */
	ILLEGAL_REQUEST_PARAMETER(0X0002, 400, "illegal_request_parameter"),
	/**
	 * 请求URI不存在
	 */
	URI_NOT_EXIST(0X0003, 404, "uri_not_exist"),
	/**
	 * 请求方法不支持
	 */
	REQUEST_METHOD_NOT_SUPPORTED(0x0004, 415, "request_method_not_supported"),
	/**
	 * 服务器内部异常
	 */
	SERVER_INTERNAL_EXCEPTION(0x0005, 500, "server_internal_exception"),
	/**
	 * 未授权，即没有登录
	 */
	UNAUTHORIZED(0x006, 401, "unauthorized"),
	
	/**
	 * 禁止访问，没有权限;即，已经登录，但是没权限
	 */
	FORBIDDEN(0x007, 403, "forbidden");
	
	/**
	 * 异常消息对象
	 */
	private final ErrorMessage msg;
	
	private HttpError(int errorCode, int statusCode, String msg) {
		this.msg = new ErrorMessage(statusCode, errorCode, msg);
	}
	
	/**
	 * 错误处理
	 * @param resp
	 * @return 错误结果
	 */
	public ErrorMessage handle(HttpServletResponse resp) {
		resp.setStatus(msg.getStatusCode());
		return msg;
	}

}
