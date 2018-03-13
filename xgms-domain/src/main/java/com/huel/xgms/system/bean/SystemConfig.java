package com.huel.xgms.system.bean;

import java.io.Serializable;

/**
 * 系统配置
 * @author wsq
 * @date 2018/3/13
 */
public class SystemConfig implements Serializable{

    private static final long serialVersionUID = 3683888569646443184L;
    /**
     * 配置id
     */
    private String configId;
    /**
     * 配置代码
     */
    private String configCode;
    /**
     * 配置 名称
     */
    private String configName;
    /**
     * 备注
     */
    private String note;
    /**
     * 配置实际值
     */
    private String value;
    /**
     * 配置所属类型
     */
    private String type;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
