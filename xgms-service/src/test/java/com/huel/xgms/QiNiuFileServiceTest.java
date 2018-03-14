package com.huel.xgms;

import com.huel.xgms.base.bean.QnPutRet;
import com.huel.xgms.base.service.IQiNiuFileService;
import com.huel.xgms.base.service.impl.QiNiuFileServiceImpl;
import com.huel.xgms.util.UUIDMaker;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author admin
 * @description
 * @date 2018/3/14
 */

public class QiNiuFileServiceTest {
    /**
     * 本地文件上传到七牛服务器
     */
    @Test
    public void testUploadFile(){

        IQiNiuFileService qiNiuFileService = new QiNiuFileServiceImpl();
        String fileUrl = "D:\\image\\java_momery.png";
        String uuid = UUIDMaker.generateUUID();
        String fileName = uuid + "." + "jpg";
        QnPutRet qnPutRet = qiNiuFileService.uploadFile(fileUrl, fileName);

        System.out.println(qnPutRet.getKey());
        System.out.println(qnPutRet.getBucket());
    }

    /**
     * 测试上传数据流
     */
    @Test
    public void testUploadInputStream(){

        String filePath = "D:\\image\\java_momery.png";
        File file = new File(filePath);
        IQiNiuFileService qiNiuFileService = new QiNiuFileServiceImpl();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            String uuid = UUIDMaker.generateUUID();
            String fileName = uuid + "." + "png";
            QnPutRet qnPutRet = qiNiuFileService.uploadInputStream(inputStream, fileName);

            Assert.assertEquals(fileName, qnPutRet.getKey());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试上传字节数组
     */
    @Test
    public void testUploadArray(){

    }
}
