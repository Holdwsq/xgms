package com.huel.xgms.app.goods.bean;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * 商品
 * @author wsq
 * @date 2018/3/13
 */
public class GoodsInfo implements Serializable{
    private static final long serialVersionUID = -7599973844255659923L;
    /**
     * 主键id
     */
    private String id;
    /**
     * 发布者 用户id
     */
    private String userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 商品
     */
    private String description;
    /**
     * 图片内容 可以传过多个图片
     */
    private List<MultipartFile> files;
    /**
     * 上传到七牛文件服务器后的文件名, 多个文件用“，”隔开
     */
    private String pictureNames;
    /**
     * 商品价格
     */
    private Float price;
    /**
     * 商品类型
     */
    private String type;
    /**
     * 发布时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 商品数量
     */
    private Integer number;
    /**
     * 删除标识
     */
    private String deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getPictureNames() {
        return pictureNames;
    }

    public void setPictureNames(String pictureNames) {
        this.pictureNames = pictureNames;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
