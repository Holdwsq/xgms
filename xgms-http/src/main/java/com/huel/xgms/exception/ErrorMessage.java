package com.huel.xgms.exception;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 系统异常通用返回结构
 * @author wyf
 *
 */
public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = 2092911638692333548L;
	
	/**
	 * Http状态码
	 */
	private transient int statusCode;
	/**
	 * 服务标识
	 */
	private String hostId;
	/**
	 * 请求唯一标识
	 */
	private String requestId;
	/**
	 * 错误代码
	 */
	private int errorCode;
	/**
	 * 错误描述
	 */
	private String message;
	
	/**
	 * 无参构造
	 */
	public ErrorMessage() {}
	
	/**
	 * 有参构造器
	 * @param statusCode http状态码
	 * @param errorCode 异常代码
	 * @param message 错误描述
	 */
	public ErrorMessage(int statusCode, int errorCode, String message) {
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.message = message;
	}
	
	/**
	 * 有参构造器
	 * @param statusCode http状态码
	 * @param hostId 服务唯一标识
	 * @param requestId 请求唯一标识
	 * @param errorCode 异常代码
	 * @param message 错误描述
	 */
	public ErrorMessage(int statusCode, String hostId, String requestId, int errorCode, String message) {
		this.statusCode = statusCode;
		this.hostId = hostId;
		this.requestId = requestId;
		this.errorCode = errorCode;
		this.message = message;
	}

	@JSONField(serialize = false)
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorMessage [statusCode=" + statusCode + ", hostId=" + hostId + ", requestId=" + requestId
				+ ", errorCode=" + errorCode + ", message=" + message + "]";
	}

}
