package com.huel.xgms.base.service;

import com.huel.xgms.base.bean.QnPutRet;

import java.io.InputStream;

/**
 * 七牛文件服务接口
 * @author wsq
 * @date 2018/3/13
 */
public interface IQiNiuFileService {
    /**
     * 上传本地文件
     * @param filePath 文件路径
     * @return {@link QnPutRet}
     */
    QnPutRet uploadFile(String filePath);

    /**
     * 上传字节数组
     * @param dataBytes 字节数组
     * @return
     */
    QnPutRet uploadBytes(byte[] dataBytes);

    /**
     * 上传文件流
     * @param inputStream 文件流
     * @return {@link QnPutRet}
     */
    QnPutRet uploadInputStream(InputStream inputStream);

    /**
     * 断点续上传
     * @param fielPath 本地文件路径
     * @return
     */
    QnPutRet uploadFileByBp(String filePath);
}
