package com.huel.xgms.httpbean;

/**
 * @description 请求相应实体
 * @author wsq
 * @date 2018/1/3
 */
public class ResponseBean<T> {
    /**
     * status 响应状态码 0 失败 1 成功
     */
    public static final String STATUS_ERROR = "0";
    public static final String STATUS_SUCCESS = "1";
    /**
     * 响应状态 0 - 失败 1 - 成功
     */
    private String status;
    /**
     * 响应消息 message
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    /**
     * @description 创建失败响应实体
     * @author wsq
     * @date 2018/1/4
     * @param message
     * @return ResponseBean
     */
    public static ResponseBean createError(String message){
        return create(STATUS_ERROR, message, null);
    }

    /**
     * @description 创建成功响应实体
     * @author wsq
     * @date 2018/1/4
     * @param data
     * @return ResponseBean
     */
    public static ResponseBean createSuccess(Object data){
        return create(STATUS_SUCCESS, null, data);
    }
    private static ResponseBean create(String status, String message, Object data){
        ResponseBean bean = new ResponseBean();
        bean.setStatus(status);
        bean.setMessage(message);
        bean.setData(data);
        return bean;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
