package com.huel.xgms.app.goods.bean;

import java.io.Serializable;

/**
 * 实体基础bean
 * @author wsq
 * @date 2018/3/18
 */
public class BaseBean implements Serializable{
    private static final long serialVersionUID = -2529161966032709660L;
    /**
     * bean id
     */
    private String id;
    /**
     * 实体名称
     */
    private String name;
    /**
     * 文件路径
     */
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
