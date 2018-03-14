package com.huel.xgms.base.util;

import com.huel.xgms.util.UUIDMaker;

/**
 * 文件操作工具类
 * @author wsq
 * @date 2018/3/14
 */
public class XgmsFileUtils {
    /**
     * 转换文件名称
     * @param originalFilename
     * @return
     */
    public static String covertFileName(String originalFilename){
        // 生成文件名称
        String fileName = UUIDMaker.generateUUID();
        // 获取源图片后缀
        int dotIndex = originalFilename.lastIndexOf(46);

        String fileExt = originalFilename.substring(dotIndex + 1);

        return fileName + "." + fileExt;

    }
}
