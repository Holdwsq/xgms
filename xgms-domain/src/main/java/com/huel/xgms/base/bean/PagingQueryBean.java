package com.huel.xgms.base.bean;

import java.io.Serializable;

/**
 * 信息分页查询bean
 * @author wsq
 * @date 2018/3/15
 */
public class PagingQueryBean implements Serializable{
    private static final long serialVersionUID = -8531096887247004046L;

    public static final int DEFAULT_PAGE_NO = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;


    /**
     * 具体对象id
     */
    private String id;
    /**
     * 起始页码
     */
    private Integer pageNo = DEFAULT_PAGE_NO;
    /**
     * 每页条数
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    /**
     * 关键词
     */
    private String key;
    /**
     * 状态 根据具体业务处理
     */
    private String state;
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        // 如果起始页小于等于0 设为默认起始页
        if (pageNo == null || pageNo <= 0){
            pageNo = DEFAULT_PAGE_NO;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        // 如果每页数据 <= 0 设为默认数值
        if (pageSize == null || pageSize <= 0){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
