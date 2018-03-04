package com.huel.xgms.util;

import java.util.UUID;

/**
 * UUID 生成器
 * @date 2018/3/4
 * @author wsq
 */
public class UUIDMaker {
    /**
     * 生成ID
     * @return id
     */
    public static String generateUUID(){
        String id;
        String uId = UUID.randomUUID().toString();
        id = uId.replace("-", "");
        return id;
    }

    /*public static void main(String... args){
        String s = UUIDMaker.generateUUID();
        System.out.println(s);
    }*/
}
