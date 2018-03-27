package com.huel.xgms.httpbean;

import java.io.Serializable;

/**
 * 
 * @Description：客户端请求的响应类
 * @author: zxq
 * @date: 2016-05-31
 *
 */
public class ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = -2990464139391883287L;
	
	public static final int FLAG_SUCCESS = 1;
	
	public static final int FLAG_ERROR = 2;

	private int flag;
	
	private String message;
	
	/**
	 * 可以是单个值、数组或对象。此成员只在成功的回复消息中出现
	 */
	private Object result;
	
	/**
	 * 
	 * @Description：创建成功响应
	 * @author: zxq
	 * @param id 请求标识
	 * @param result 响应结果
	 * @return ResponseEntity 响应
	 */
	public static ResponseEntity createSuccess(Object result) {
		return create(FLAG_SUCCESS, null, result);
	}
	
	public static ResponseEntity createError(String message) {
		return create(FLAG_ERROR, message, null);
	}
	
	public static ResponseEntity create(int flag, String message, Object result) {
		ResponseEntity response = new ResponseEntity();
		response.setFlag(flag);
		response.setResult(result);
		return response;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return 获得 {@linkplain #result}
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * 设定 {@linkplain #result}
	 */
	public void setResult(Object result) {
		this.result = result;
	}
}
