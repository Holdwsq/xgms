package com.huel.xgms.base.bean;

import java.io.Serializable;

/**
 * 七牛返回数据bean
 * @author wsq
 * @date 2018/3/13
 */
public class QnPutRet implements Serializable{
    private static final long serialVersionUID = 4062780311183412792L;
    /**
     * 自定义七牛回复内容
     */
    public static String returnBody = "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}";
    /**
     * 七牛返回文件的关键字
     */
    public String key;
    /**
     * 文件 hash 值
     */
    public String hash;
    /**
     * 文件空间名称
     */
    public String bucket;
    /**
     * 文件大小
     */
    public long fsize;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public long getFsize() {
        return fsize;
    }

    public void setFsize(long fsize) {
        this.fsize = fsize;
    }
}
